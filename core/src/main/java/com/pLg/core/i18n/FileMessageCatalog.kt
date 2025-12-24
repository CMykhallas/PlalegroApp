package com.pLg.core.i18n

import java.io.File
import java.nio.charset.Charset
import org.json.JSONObject

/**
 * Loader de arquivos JSON (um arquivo por locale), com cache em memória.
 * Ex.: "pt-MZ.json" contendo { "greeting.hello": "Olá" }
 */
class FileMessageCatalog(
    private val dir: File,
    private val charset: Charset = Charsets.UTF_8
) : MessageCatalog {

    private val cache = InMemoryMessageCatalog()

    fun reload() {
        if (!dir.exists() || !dir.isDirectory) {
            throw I18nError.CatalogLoad("Diretório inválido: ${dir.path}")
        }
        val files = dir.listFiles { f -> f.isFile && f.extension.equals("json", ignoreCase = true) }
            ?: throw I18nError.CatalogLoad("Falha ao listar arquivos em ${dir.path}")

        files.forEach { file ->
            val content = try {
                file.readText(charset)
            } catch (e: Exception) {
                throw I18nError.CatalogLoad("Erro lendo ${file.name}", e)
            }
            val json = try {
                JSONObject(content)
            } catch (e: Exception) {
                throw I18nError.CatalogLoad("JSON inválido em ${file.name}", e)
            }

            val messages = mutableMapOf<String, String>()
            json.keys().forEach { k ->
                val v = json.optString(k, "")
                if (v.isBlank()) throw I18nError.CatalogLoad("Mensagem vazia em chave '$k' de ${file.name}")
                messages[k] = v
            }

            val localeTag = file.nameWithoutExtension
            cache.replaceLocale(localeTag, messages)
        }
    }

    override fun get(localeTag: String, key: MessageKey): String? = cache.get(localeTag, key)
    override fun locales(): Set<String> = cache.locales()
}
