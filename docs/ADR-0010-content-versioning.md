# ADR-0010: Estratégia de Versionamento e Gestão de Conteúdo Educativo

## Status

Aceito

## Contexto

O projeto Play Learn Grow utiliza arquivos JSON para armazenar conteúdos educativos (números, cores, letras, etc.).  
Com a evolução do projeto, novos jogos e atualizações de conteúdo serão adicionados.  
É necessário garantir consistência, rastreabilidade e compatibilidade entre versões de conteúdo, evitando quebra de funcionalidades ou perda de dados.

## Decisão

Adotar uma estratégia de versionamento e gestão de conteúdo baseada em:

- **Versionamento semântico** para arquivos de conteúdo (`v1.0.0`, `v1.1.0`, etc.).
- **Metadados de versão** incluídos em cada arquivo JSON.
- **Catálogo central** (`content-catalog.md`) para documentar versões e mudanças.
- **Validação automática** via scripts de QA (Detekt/Ktlint + testes de schema).
- **Fallback de versão**: se um jogo não encontrar a versão mais recente, carrega a última versão compatível.

## Consequências

- **Positivas**:
  - Rastreabilidade clara de mudanças no conteúdo.
  - Facilidade de manutenção e atualização incremental.
  - Compatibilidade garantida entre frontend e backend.
- **Negativas**:
  - Necessidade de disciplina na atualização de versões.
  - Maior esforço inicial para configurar validação automática.

## Alternativas consideradas

- **Sem versionamento**: mais simples, mas arriscado para manutenção e evolução.
- **Versionamento manual em nomes de arquivos**: viável, mas menos padronizado e difícil de automatizar.
- **Uso de banco de dados sem versionamento explícito**: rápido, mas sem rastreabilidade clara.

## Data

2025-12-23

## Autores

Equipe Play Learn Grow
