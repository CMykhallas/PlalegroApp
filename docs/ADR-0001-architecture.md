# ADR-0001: Arquitetura do Projeto Play Learn Grow

## Contexto

O projeto Play Learn Grow é uma plataforma educativa interativa que combina jogos de números, cores e letras para crianças em idade pré-escolar.

## Decisão

- Utilizar **Kotlin** para lógica de backend e processamento de conteúdo.
- Utilizar **Next.js + TailwindCSS** para frontend web responsivo.
- Armazenar conteúdo educativo em **JSON** (numbers.json, colors.json, letters.json).
- Garantir qualidade com **Detekt, Ktlint e Jacoco**.

## Consequências

- Arquitetura modular facilita manutenção e expansão.
- Integração entre frontend e backend via APIs.
- Documentação clara para novos colaboradores.
