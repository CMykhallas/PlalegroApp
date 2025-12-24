![Coverage](https://codecov.io/gh/<seu-usuario>/<seu-repo>/branch/main/graph/badge.svg)

# 🔄 Fluxo CI/CD + Staging

```mermaid
flowchart TD
    A[Commit/Pull Request] --> B[CI Pipeline]
    B -->|Build + Test| C{Branch?}

    C -->|develop| D[Staging Deployment]
    C -->|main| E[Production Deployment]

    D --> D1[Web → Vercel (Staging)]
    D --> D2[Android → Firebase QA]

    E --> E1[Web → Vercel (Production)]
    E --> E2[Android → Firebase Testers]

    B --> F[Lint + Static Analysis]
    F --> B
```

---

## 🔍 Explicação do fluxo

- **CI Pipeline (ci.yml)**

  - Executa build, testes unitários e análise estática (Detekt/Ktlint).
  - Garante que nenhum código inválido entre no repositório.

- **Branch `develop` → Staging (staging.yml)**

  - Deploy automático para **Vercel (staging)**.
  - Geração de APK **debug** e distribuição para **Firebase QA**.
  - Ambiente usado pelo time de QA para validar novas features.

- **Branch `main` → Produção (cd.yml)**
  - Deploy automático para **Vercel (produção)**.
  - Geração de APK **release**, assinado e distribuído para **Firebase testers**.
  - Ambiente usado por usuários finais.

---

## 🎯 Resultado

- O diagrama mostra claramente o fluxo **CI → Staging → Produção**.
- QA valida primeiro em **staging**, antes de liberar para produção.
- Documentação completa e visual, pronta para ser adicionada ao `README.md`.

---
