mithindiro
/
Drafts
/

Plalegro App

🏗️ Arquitetura do Projeto Play Learn Grow / Plalegro App
Este documento descreve a arquitetura geral do projeto, organizada em camadas independentes e integradas.
O objetivo é garantir modularidade, escalabilidade e clareza para desenvolvedores e equipes de QA/DevOps.

📂 Estrutura de Camadas
🏗️ Arquitetura do Projeto Play Learn Grow / Plalegro App
Este documento descreve a arquitetura geral do projeto, organizada em camadas independentes e integradas.
O objetivo é garantir modularidade, escalabilidade e clareza para desenvolvedores e equipes de QA/DevOps.

📂 Estrutura de Camadas
🔹 1. Core (Domínio)
Local: core/src/main/java/com/pLg/core/
Responsabilidade:
Entidades e Value Objects (ex.: User, ChildProfile, ContentPack).
Casos de uso (interactors) seguindo Clean Architecture.
Utilitários comuns (DateUtils, ValidationUtils, etc.).
Características:
Puro Kotlin, sem dependência de Android ou frameworks.
Testável isoladamente (core/src/test).
Retorna sempre Result<Success, Failure> para evitar estados inválidos.
🔹 2. App (Android)
Local: app/
Responsabilidade:
Interface de usuário com Jetpack Compose.
Navegação com Navigation Compose.
Injeção de dependência com Hilt.
Integração com core para lógica de negócio.
Recursos:
res/ para strings, temas e ícones.
assets/ para pacotes de conteúdo educativo.
Segurança:
proguard-rules.pro para otimização e ofuscação.
🔹 3. Web (Next.js)
Local: web/
Responsabilidade:
Aplicativo web (Plalegro App) construído com Next.js.
UI moderna com suporte a internacionalização.
Deploy contínuo em Vercel.
Recursos:
app/page.tsx
como entrypoint.
next/font para otimização de fontes (Geist).
Build:
Gerenciado com pnpm.
Deploy automático via GitHub Actions → Vercel.
🔹 4. Infra (CI/CD + Staging + Produção)
Local: .github/workflows/
Responsabilidade:
CI (ci.yml): build, testes e lint.
Staging (staging.yml): deploy automático para QA.
Web → Vercel (staging).
Android → Firebase QA.
Produção (cd.yml): deploy automático para usuários finais.
Web → Vercel (prod).
Android → Firebase testers.
Fluxo:
Branch develop → Staging.
Branch main → Produção.
🔄 Fluxo de Integração
flowchart TD
A[Core: Domínio] --> B[App: Android]
A --> C[Web: Next.js]
B --> D[Infra: CI/CD]
C --> D
D -->|Staging| QA[Equipe QA]
D -->|Produção| Users[Usuários finais]
🎯 Benefícios da Arquitetura Separação de responsabilidades: cada camada tem foco claro.

Testabilidade: core é totalmente testável sem dependências externas.

Escalabilidade: fácil adicionar novos módulos (ex.: backend).

Consistência: CI/CD garante qualidade contínua.

Flexibilidade: web e mobile evoluem em paralelo, mas compartilham lógica de negócio.

Code

🎯 Resultado
O
docs/architecture.md
fornece uma visão clara, modular e visual da arquitetura.
Facilita onboarding de novos devs e comunicação entre equipes.
Complementa o
README.md
com detalhes técnicos e diagramas.

# 🔗 Integração entre Android, Shared, Ktor e Next.js

```mermaid
flowchart TD
    subgraph Shared[Kotlin Multiplatform Shared Module]
        A1[Domínio: Entidades, Value Objects]
        A2[Use Cases]
        A3[Repos em memória / contratos]
    end

    subgraph Android[Android App (Compose)]
        B1[UI Compose]
        B2[Hilt DI]
        B3[Navigation Compose]
    end

    subgraph Server[Ktor Server API]
        C1[Endpoints REST]
        C2[ContentNegotiation + JSON]
        C3[Use Cases Shared]
    end

    subgraph Web[Next.js App]
        D1[React Components]
        D2[Fetch API → Ktor]
        D3[Vercel Deploy]
    end

    Shared --> Android
    Shared --> Server
    Server --> Web

---

## 🔍 Explicação

- **Shared (KMP)**
  - Contém toda a lógica de negócio (domínio, use cases, validações).
  - É consumido diretamente pelo Android.
  - Também é usado pelo Ktor Server para expor APIs REST.

- **Android (Compose)**
  - UI moderna com Compose.
  - Consome diretamente os casos de uso do `shared`.
  - Integração com DI (Hilt) e navegação.

- **Ktor Server**
  - Usa os mesmos casos de uso do `shared`.
  - Expõe endpoints REST (`/registerUser`, `/createChild`, etc.).
  - Serve como backend para staging.

- **Next.js (Web)**
  - Consome os endpoints REST do Ktor Server.
  - Deploy contínuo em Vercel.
  - UI moderna com React/Next.

---

## 🎯 Resultado
- O diagrama mostra claramente como **Android consome diretamente o `shared`**, enquanto **Next.js consome via Ktor Server**.
- Garante **consistência de regras de negócio** em todas as plataformas.
- Documentação visual e técnica, pronta para ser adicionada ao `docs/architecture.md`.

---
```
