package com.pLg.core.util

import java.util.UUID

/**
 * Interface para geração de IDs únicos.
 * Mantém o domínio independente de implementações específicas.
 */
interface IdGenerator {
    fun newId(): String
}

/**
 * Implementação padrão usando UUID.
 */
object UuidGenerator : IdGenerator {
    override fun newId(): String = UUID.randomUUID().toString()
}

/**
 * Implementação customizada para testes (IDs previsíveis).
 */
class IncrementalIdGenerator : IdGenerator {
    private var counter = 0
    override fun newId(): String {
        counter++
        return "id_$counter"
    }
}
