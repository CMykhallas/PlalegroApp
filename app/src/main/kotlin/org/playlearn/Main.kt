package org.playlearn

fun main() {
    println("🎉 Play Learn Grow iniciado!")
    println("Bem-vindo ao projeto educativo em Kotlin 🚀")

    // Exemplo simples de interação
    val jogos = listOf("Números", "Cores", "Letras")
    println("Jogos disponíveis: ${jogos.joinToString(", ")}")

    println("Digite o nome de um jogo para começar:")
    val escolha = readlnOrNull()

    if (escolha != null && jogos.any { it.equals(escolha, ignoreCase = true) }) {
        println("Você escolheu o jogo: $escolha ✅")
        // Aqui você pode carregar o conteúdo JSON correspondente
    } else {
        println("Jogo não encontrado. Tente novamente.")
    }
}
