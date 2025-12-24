# ADR-0009: Estratégia de Deploy e Infraestrutura

## Status

Aceito

## Contexto

O projeto Play Learn Grow precisa ser disponibilizado de forma confiável e escalável para escolas, pais e crianças.  
A infraestrutura deve suportar crescimento futuro, integração com APIs externas e garantir segurança e performance.  
Além disso, é necessário facilitar o processo de deploy contínuo e reduzir a complexidade operacional.

## Decisão

Adotar uma estratégia de deploy e infraestrutura baseada em:

- **Frontend (Next.js)** hospedado em **Vercel**, aproveitando otimizações automáticas de build e CDN global.
- **Backend (Kotlin)** hospedado em **Docker containers** gerenciados via **Kubernetes** em **AWS EKS**.
- **Banco de dados PostgreSQL** provisionado via **AWS RDS** para alta disponibilidade.
- **CI/CD com GitHub Actions** integrado ao pipeline de build, testes e deploy.
- **Monitoramento e observabilidade** com Prometheus + Grafana.

## Consequências

- **Positivas**:
  - Deploy automatizado e confiável.
  - Escalabilidade horizontal com Kubernetes.
  - CDN global para baixa latência no frontend.
  - Monitoramento contínuo de performance e erros.
- **Negativas**:
  - Maior complexidade inicial de configuração.
  - Custos de infraestrutura em nuvem mais elevados.

## Alternativas consideradas

- **Heroku**: simples, mas limitado para escalabilidade e controle avançado.
- **DigitalOcean**: mais barato, mas menos robusto para grandes cargas.
- **Deploy manual em servidores locais**: inviável para manutenção e escalabilidade.

## Data

2025-12-23

## Autores

Equipe Play Learn Grow
