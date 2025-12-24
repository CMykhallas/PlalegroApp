# ADR-0007: Estratégia de Testes Automatizados e QA Contínuo

## Status

Aceito

## Contexto

O projeto Play Learn Grow precisa garantir que funcionalidades críticas (jogos educativos, carregamento de conteúdo, acessibilidade) funcionem corretamente em diferentes ambientes.  
Com múltiplos colaboradores e evolução contínua, é essencial adotar uma estratégia de testes automatizados e QA contínuo para reduzir riscos e aumentar a confiabilidade.

## Decisão

Adotar uma estratégia de testes baseada em:

- **Testes unitários**: validar lógica de jogos e manipulação de dados em Kotlin.
- **Testes de integração**: garantir comunicação correta entre frontend (Next.js) e backend (Kotlin).
- **Testes de acessibilidade**: rodar ferramentas como axe e Lighthouse em CI.
- **Cobertura de testes com Jacoco**: monitorar métricas de qualidade.
- **Pipeline CI/CD no GitHub Actions**: executar automaticamente build, lint, análise estática e testes a cada push/PR.

## Consequências

- **Positivas**:
  - Maior confiabilidade e estabilidade do sistema.
  - Detecção precoce de regressões.
  - Relatórios claros de qualidade e cobertura.
  - Cultura de qualidade contínua entre os desenvolvedores.
- **Negativas**:
  - Maior esforço inicial para configurar testes e CI/CD.
  - Necessidade de manutenção contínua dos testes conforme evolução do projeto.

## Alternativas consideradas

- **Testes manuais apenas**: mais rápido no início, mas arriscado e difícil de escalar.
- **Ferramentas externas de QA sem integração**: úteis, mas não garantem cobertura completa nem integração com pipeline.
- **Cobertura parcial (apenas unitários)**: reduz esforço, mas deixa lacunas em integração e acessibilidade.

## Data

2025-12-23

## Autores

Equipe Play Learn Grow
