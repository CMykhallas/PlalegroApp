# ADR-0004: Escolha de Ferramentas de QA e Análise Estática

## Status

Aceito

## Contexto

O projeto Play Learn Grow precisa garantir qualidade de código, consistência de estilo e métricas de cobertura de testes.  
Com múltiplos colaboradores e crescimento esperado, é essencial adotar ferramentas que automatizem verificações e mantenham o código saudável.

## Decisão

Adotar o conjunto de ferramentas:

- **Detekt** → análise estática para código Kotlin, detectando problemas de estilo e complexidade.
- **Ktlint** → formatação automática e padronização de estilo em Kotlin.
- **Jacoco** → geração de relatórios de cobertura de testes.

Essas ferramentas serão integradas ao Gradle e ao pipeline de CI/CD (GitHub Actions).

## Consequências

- **Positivas**:
  - Código mais limpo e consistente.
  - Detecção precoce de problemas de qualidade.
  - Relatórios claros de cobertura de testes.
  - Integração simples com CI/CD.
- **Negativas**:
  - Pequeno overhead de configuração inicial.
  - Necessidade de manter versões atualizadas das ferramentas.

## Alternativas consideradas

- **SonarQube**: oferece análise avançada, mas exige servidor dedicado e maior complexidade de setup.
- **Checkstyle/PMD**: mais comuns em Java, menos adaptados ao ecossistema Kotlin.
- **Sem ferramentas**: risco de inconsistência e baixa qualidade de código.

## Data

2025-12-23

## Autores

Equipe Play Learn Grow
