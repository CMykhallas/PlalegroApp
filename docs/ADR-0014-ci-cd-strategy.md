# ADR-0014: Estratégia de Integração Contínua e Entrega Contínua (CI/CD)

## Status

Aceito

## Contexto

O projeto Play Learn Grow possui múltiplos módulos (frontend, backend, conteúdo educativo) e depende de qualidade consistente para evoluir com segurança.  
É essencial automatizar processos de build, testes, análise estática e deploy para reduzir erros humanos e acelerar entregas.

## Decisão

Adotar uma estratégia de CI/CD baseada em:

- **GitHub Actions** como plataforma principal de automação.
- **Pipeline de integração contínua (CI)**:
  - Build automático a cada push/PR.
  - Execução de testes unitários e de integração.
  - Análise estática com Detekt e formatação com Ktlint.
  - Relatórios de cobertura com Jacoco.
- **Pipeline de entrega contínua (CD)**:
  - Deploy automático do frontend (Next.js) em Vercel.
  - Deploy automatizado do backend (Kotlin) em containers Docker via Kubernetes (AWS EKS).
  - Versionamento semântico para releases.
- **Gate de qualidade**: PRs só podem ser aprovados se todos os checks passarem.

## Consequências

- **Positivas**:
  - Maior confiabilidade e velocidade nas entregas.
  - Redução de erros humanos.
  - Transparência no processo de desenvolvimento.
  - Cultura de qualidade contínua.
- **Negativas**:
  - Maior esforço inicial de configuração.
  - Necessidade de manutenção contínua dos pipelines.

## Alternativas consideradas

- **Deploy manual**: simples, mas arriscado e sujeito a falhas humanas.
- **Ferramentas externas (Jenkins, GitLab CI)**: poderosas, mas exigem infraestrutura dedicada.
- **CI sem CD**: garante qualidade, mas não automatiza entregas.

## Data

2025-12-23

## Autores

Equipe Play Learn Grow
