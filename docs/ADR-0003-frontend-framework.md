# ADR-0003: Escolha de Framework Frontend para Interface Educativa

## Status

Aceito

## Contexto

O projeto Play Learn Grow precisa de uma interface web responsiva, acessível e fácil de manter.  
A interface deve suportar animações leves, integração com conteúdo JSON e escalabilidade para novos jogos e módulos educativos.  
Além disso, é necessário garantir compatibilidade com ferramentas modernas de desenvolvimento e uma comunidade ativa.

## Decisão

Adotar **Next.js** como framework frontend principal, em conjunto com **React** e **TailwindCSS**.

- Next.js oferece suporte a renderização híbrida (SSR e SSG), ideal para páginas educativas rápidas e acessíveis.
- React garante modularidade e reuso de componentes.
- TailwindCSS simplifica estilização e garante consistência visual com acessibilidade.

## Consequências

- **Positivas**:
  - Renderização rápida e otimizada para SEO.
  - Estrutura modular e escalável.
  - Comunidade ativa e documentação extensa.
  - Integração fácil com bibliotecas de acessibilidade e internacionalização.
- **Negativas**:
  - Curva de aprendizado inicial para quem não conhece Next.js.
  - Dependência de Node.js e ecossistema JavaScript.

## Alternativas consideradas

- **Angular**: robusto, mas mais complexo e pesado para o escopo do projeto.
- **Vue.js**: simples e flexível, mas menor integração com o ecossistema já adotado (React/Kotlin).
- **React puro**: viável, mas sem as otimizações de roteamento e build que o Next.js oferece.

## Data

2025-12-23

## Autores

Equipe Play Learn Grow
