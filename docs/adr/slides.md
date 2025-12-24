# 📊 Slide Deck – ADRs 0001–0010

---

## 🚀 Visão Geral

- Projeto: **Play Learn Grow / Plalegro App**
- Objetivo: Documentar decisões arquiteturais críticas
- Escopo: ADR-0001 → ADR-0010

---

## ADR-0001 – Clean Architecture

- Separação clara de responsabilidades
- Testabilidade e escalabilidade
- Base para todas as camadas

---

## ADR-0002 – Kotlin Multiplatform (KMP)

- Compartilhamento de lógica entre Android e Web
- Redução de duplicação
- Consistência multiplataforma

---

## ADR-0003 – Ktor Server (Staging)

- Backend leve em Kotlin
- Exposição de APIs REST
- Reuso direto do módulo `shared`

---

## ADR-0004 – Firebase App Distribution

- Entrega automatizada de builds Android
- Notificações para QA e testers
- Feedback centralizado

---

## ADR-0005 – Vercel (Next.js)

- Deploy contínuo otimizado para Next.js
- Ambientes separados (staging/produção)
- Escalabilidade global

---

## ADR-0006 – GitHub Actions (CI/CD)

- Orquestrador oficial de pipelines
- Integração nativa com GitHub
- Automação completa de CI/CD

---

## ADR-0007 – Detekt + Ktlint

- Análise estática (Detekt)
- Formatação automática (Ktlint)
- Código limpo e padronizado

---

## ADR-0008 – Kotlinx Serialization

- Serialização multiplataforma
- Integração com Ktor Client/Server
- Suporte nativo a JSON

---

## ADR-0009 – Deploy e Infraestrutura

- GitHub Actions + Vercel + Firebase + Ktor
- Automação e ambientes separados
- Integração transparente

---

## ADR-0010 – Versionamento de Conteúdo

- Versionamento semântico (`major.minor.patch`)
- Consistência entre plataformas
- Rollback seguro

---

## 🎯 Conclusão

- Base sólida para qualidade, escalabilidade e automação
- ADRs 0001–0010 = fundação arquitetural do projeto
- Próximos passos: ADR-0011+ (observabilidade, segurança, escalabilidade)
