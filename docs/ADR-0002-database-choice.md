# ADR-0002: Escolha de Banco de Dados para Persistência de Conteúdo Educativo

## Status

Aceito

## Contexto

O projeto Play Learn Grow precisa armazenar e consultar conteúdos educativos (números, cores, letras, formas) de forma estruturada e escalável.  
Além disso, é necessário suportar consultas rápidas, versionamento de conteúdo e integração futura com relatórios de progresso dos alunos.

## Decisão

Adotar **PostgreSQL** como banco de dados principal para persistência de conteúdo educativo.

- PostgreSQL é robusto, open-source e amplamente utilizado.
- Suporta JSON nativamente, permitindo armazenar conteúdos sem perder flexibilidade.
- Oferece extensões úteis para análise de dados e relatórios.

## Consequências

- **Positivas**:
  - Estrutura relacional clara para jogos e usuários.
  - Suporte a dados semiestruturados (JSON) para flexibilidade.
  - Comunidade ativa e documentação extensa.
- **Negativas**:
  - Necessidade de configurar e manter servidor PostgreSQL.
  - Curva de aprendizado para quem não está familiarizado com SQL.

## Alternativas consideradas

- **SQLite**: simples e leve, mas limitado para escalabilidade e múltiplos usuários simultâneos.
- **MongoDB**: bom para documentos JSON, mas menos integrado ao ecossistema Kotlin/Gradle e requer mais dependências.
- **Armazenamento em arquivos JSON**: fácil no início, mas difícil de escalar e manter consistência.

## Data

2025-12-23

## Autores

Equipe Play Learn Grow
Sekure Tech Didaskalos
