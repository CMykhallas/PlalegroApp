# Guia de Contribuição - Play Learn Grow 🎮

Obrigado por investir seu tempo para contribuir com o **Play Learn Grow**!

Este documento contém um conjunto de diretrizes para ajudar você a contribuir com o projeto. Seguir estas regras ajuda a manter o código limpo, organizado e fácil de manter para todos.

---

## 🤝 Código de Conduta

Ao participar deste projeto, esperamos que você:

- Seja respeitoso e inclusivo nas discussões (Issues e PRs).
- Aceite críticas construtivas.
- Mantenha o foco na melhoria contínua do projeto.

---

## 🛠️ Fluxo de Trabalho (Workflow)

1. **Fork** o repositório para a sua conta.
2. Clone o projeto localmente:
   ```bash
   git clone https://github.com/SUA-CONTA/play-learn-grow.git
   Crie uma Branch para sua tarefa (veja o padrão de nomes abaixo).
   Faça suas alterações e Testes.
   Faça o Commit seguindo o padrão definido.
   Abra um Pull Request (PR) para a branch main do repositório original.
   🌿 Padrão de Branches
   Nunca trabalhe diretamente na branch main ou master. Use prefixos para identificar o tipo de trabalho:
   feature/: Para novas funcionalidades.
   Ex: feature/tela-numeros, feature/audio-player
   bugfix/ ou fix/: Para correção de erros.
   Ex: fix/crash-login, bugfix/layout-tablet
   refactor/: Mudanças de código que não alteram funcionalidade (limpeza).
   docs/: Alterações apenas na documentação.
   💬 Padrão de Commits
   Utilizamos a convenção Conventional Commits. Isso facilita a leitura do histórico e a geração de changelogs.
   Estrutura: <tipo>(<escopo opcional>): <descrição>
   Tipos permitidos:
   feat: Nova funcionalidade.
   fix: Correção de bug.
   docs: Documentação (README, Javadoc).
   style: Formatação, ponto e vírgula, etc (sem mudança de código).
   refactor: Refatoração de código.
   test: Adição ou correção de testes.
   chore: Atualização de build, dependências, ferramentas.
   Exemplos:
   ✅ feat(ui): adiciona componente de botão animado
   ✅ fix(nav): corrige rota da tela de configurações
   ✅ docs: atualiza instruções de setup no README
   🎨 Guia de Estilo de Código (Kotlin & Compose)
   Para manter a consistência, seguimos as diretrizes oficiais do Android e Kotlin.
   ```
3. Kotlin Geral
   Use CamelCase para classes e funções.
   Siga as recomendações do Android Lint.
   Remova importações não utilizadas antes de commitar.
4. Jetpack Compose
   Nomes de Funções: Funções @Composable que retornam UI devem ser substantivos e em PascalCase (Ex: GameScreen, PrimaryButton).
   State Hoisting: Sempre que possível, eleve o estado (state) para o pai, passando eventos (onClick) para baixo.
   Modificadores: O argumento modifier: Modifier deve ser sempre o primeiro parâmetro opcional de um Composable.
   code
   Kotlin
   // ✅ Bom
   @Composable
   fun WelcomeCard(
   title: String,
   modifier: Modifier = Modifier, // Primeiro opcional
   onStartClick: () -> Unit
   ) { ... }
5. Injeção de Dependência (Hilt)
   Use injeção via construtor (@Inject constructor) sempre que possível.
   ViewModels devem ser anotados com @HiltViewModel.
   🚀 Enviando um Pull Request (PR)
   Ao abrir um PR, certifique-se de:
   Título: Claro e objetivo (pode seguir o padrão de commit).
   Descrição:
   O que foi feito?
   Por que foi feito?
   Qual Issue isso resolve? (ex: Resolves #12)
   Screenshots/Vídeos: Se a mudança for visual (UI), anexe capturas de tela ou gravações. Isso acelera muito a revisão.
   Testes: Garanta que o projeto compila e que você rodou os testes locais (./gradlew test).
   🐛 Reportando Bugs
   Se encontrar um bug, abra uma Issue informando:
   Passos para reproduzir.
   Comportamento esperado vs. comportamento real.
   Dispositivo/Emulador utilizado.
   Versão do Android.
   Obrigado por contribuir para a educação infantil com tecnologia!
