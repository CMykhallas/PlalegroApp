# 📑 Resumo Executivo dos ADRs (0001–0010)

Este documento apresenta uma visão consolidada das principais decisões arquiteturais do projeto **Play Learn Grow / Plalegro App**.  
O objetivo é fornecer clareza estratégica para gestores, stakeholders e equipes técnicas.

---

## 🔹 ADR-0001 → Clean Architecture

- **Decisão:** Adotar Clean Architecture como padrão.
- **Impacto:** Separação clara de responsabilidades, maior testabilidade e escalabilidade.

## 🔹 ADR-0002 → Kotlin Multiplatform (KMP)

- **Decisão:** Compartilhar lógica de negócio entre Android e Web via KMP.
- **Impacto:** Redução de duplicação, consistência multiplataforma.

## 🔹 ADR-0003 → Ktor Server (Staging)

- **Decisão:** Usar Ktor Server como backend de staging.
- **Impacto:** Exposição de APIs REST consistentes para Next.js, reuso do módulo `shared`.

## 🔹 ADR-0004 → Firebase App Distribution

- **Decisão:** Distribuir builds Android via Firebase App Distribution.
- **Impacto:** Automação da entrega para QA e testers, feedback rápido.

## 🔹 ADR-0005 → Vercel (Next.js)

- **Decisão:** Usar Vercel como plataforma de deploy contínuo para o Plalegro App.
- **Impacto:** Deploy rápido, ambientes separados (staging/produção), suporte nativo a Next.js.

## 🔹 ADR-0006 → GitHub Actions (CI/CD)

- **Decisão:** Orquestrar pipelines com GitHub Actions.
- **Impacto:** Automação completa de CI/CD, integração nativa com GitHub.

## 🔹 ADR-0007 → Detekt + Ktlint

- **Decisão:** Usar Detekt para análise estática e Ktlint para formatação.
- **Impacto:** Código limpo, consistente e padronizado.

## 🔹 ADR-0008 → Kotlinx Serialization

- **Decisão:** Usar Kotlinx Serialization para serialização de dados.
- **Impacto:** Suporte multiplataforma, integração com Ktor Client/Server.

## 🔹 ADR-0009 → Deploy e Infraestrutura

- **Decisão:** Infra oficial: GitHub Actions + Vercel + Firebase + Ktor Server.
- **Impacto:** Automação, ambientes separados, integração transparente.

## 🔹 ADR-0010 → Versionamento de Conteúdo Educativo

- **Decisão:** Adotar versionamento semântico (`major.minor.patch`) para pacotes de conteúdo.
- **Impacto:** Consistência entre plataformas, rollback seguro, evolução controlada.

---

## 🎯 Conclusão

As decisões arquiteturais (ADR-0001 → ADR-0010) estabelecem uma base sólida para:

- **Qualidade:** Clean Architecture, análise estática, formatação.
- **Escalabilidade:** KMP, deploy contínuo, versionamento de conteúdo.
- **Automação:** CI/CD com GitHub Actions, distribuição com Firebase, deploy com Vercel.
- **Consistência:** Reuso de lógica entre Android, Web e Backend.

Essas escolhas garantem que o projeto evolua de forma sustentável, segura e alinhada às melhores práticas do mercado.
