# ADR-0005: Estratégia de Internacionalização e Suporte Multilíngue

## Status

Aceito

## Contexto

O projeto Play Learn Grow tem como objetivo alcançar crianças em diferentes países e culturas.  
Para garantir inclusão e acessibilidade, é necessário oferecer suporte multilíngue, permitindo que os jogos educativos sejam traduzidos e adaptados para diversos idiomas.  
Além disso, o conteúdo deve ser fácil de manter e expandir sem duplicar lógica de código.

## Decisão

Adotar uma estratégia de internacionalização baseada em:

- **Arquivos de tradução JSON** separados por idioma (ex.: `pt.json`, `en.json`, `es.json`).
- Uso de bibliotecas de i18n no frontend (Next.js) para carregar traduções dinamicamente.
- Definição de um idioma padrão (`pt-BR`) e fallback para inglês (`en`).
- Estrutura modular que permite adicionar novos idiomas sem alterar a lógica principal dos jogos.

## Consequências

- **Positivas**:
  - Inclusão de usuários de diferentes regiões e culturas.
  - Facilidade de manutenção e expansão de idiomas.
  - Suporte a acessibilidade linguística em ambientes escolares diversos.
- **Negativas**:
  - Necessidade de manter arquivos de tradução atualizados.
  - Maior esforço inicial para configurar i18n no frontend e backend.

## Alternativas consideradas

- **Tradução manual embutida no código**: simples, mas difícil de manter e escalar.
- **Serviços externos de tradução automática**: rápido, mas pode gerar inconsistências e erros pedagógicos.
- **Suporte apenas a um idioma**: reduz esforço, mas limita alcance global do projeto.

## Data

2025-12-23

## Autores

Equipe Play Learn Grow
