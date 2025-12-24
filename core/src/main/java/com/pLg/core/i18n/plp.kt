import com.pLg.core.i18n.*
import java.io.File
import java.time.LocalDate

fun main() {
    // Catálogo em memória
    val catalog = InMemoryMessageCatalog(
        mapOf(
            "pt-MZ" to mapOf(
                "greeting.hello" to "Olá",
                "amount.items" to "{count, plural, =0 {Nenhum item} one {# item} other {# itens}}",
                "basket.total" to "Total: {total}"
            ),
            "pt" to mapOf(
                "greeting.hello" to "Olá",
                "amount.items" to "{count, plural, =0 {Sem itens} one {# item} other {# itens}}",
                "basket.total" to "Total: {total}"
            ),
            "en" to mapOf(
                "greeting.hello" to "Hello",
                "amount.items" to "{count, plural, =0 {No items} one {# item} other {# items}}",
                "basket.total" to "Total: {total}"
            )
        )
    )

    // Opcional: carregar de arquivos
    val fileCatalog = FileMessageCatalog(File("i18n"))
    // fileCatalog.reload()

    val localeProvider = MutableLocaleProvider(LocaleSpec("pt", "MZ"))
    val i18n = I18n(catalog, localeProvider)

    println(i18n.t("greeting.hello"))
    println(i18n.t("amount.items", mapOf("count" to 0)))
    println(i18n.t("amount.items", mapOf("count" to 1)))
    println(i18n.t("amount.items", mapOf("count" to 5)))

    println(i18n.t("basket.total", mapOf("total" to 12345.67)))
    println(i18n.currency(12345.67, "MZN"))
    println(i18n.dateMedium(LocalDate.now()))

    // Fallback transparente
    localeProvider.set(LocaleSpec("pt", "AO"))
    println(i18n.t("greeting.hello")) // cai para "pt"
}
