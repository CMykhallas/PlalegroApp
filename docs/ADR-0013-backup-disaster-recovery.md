# ADR-0013: Estratégia de Backup e Recuperação de Desastres

## Status

Aceito

## Contexto

O projeto Play Learn Grow lida com dados educativos e informações sensíveis de usuários (pais, professores e crianças).  
Para garantir continuidade do serviço e proteção contra falhas, é essencial definir uma estratégia de backup e recuperação de desastres.  
Isso inclui cenários como perda de dados, falhas de infraestrutura, ataques cibernéticos ou desastres naturais.

## Decisão

Adotar uma estratégia de backup e recuperação baseada em:

- **Backups automáticos diários** do banco de dados PostgreSQL via AWS RDS.
- **Armazenamento redundante** em múltiplas regiões da nuvem (AWS S3 com replicação).
- **Snapshots semanais** dos containers e configurações de Kubernetes.
- **Testes regulares de restauração** para validar integridade dos backups.
- **Plano de recuperação de desastres (DRP)** documentado, com RTO (Recovery Time Objective) de 4 horas e RPO (Recovery Point Objective) de 24 horas.

## Consequências

- **Positivas**:
  - Garantia de continuidade mesmo em cenários críticos.
  - Redução de riscos de perda de dados.
  - Conformidade com boas práticas de segurança e confiabilidade.
- **Negativas**:
  - Custos adicionais de armazenamento e replicação.
  - Necessidade de manutenção contínua e testes periódicos.

## Alternativas consideradas

- **Backups manuais**: simples, mas arriscados e sujeitos a falhas humanas.
- **Armazenamento local apenas**: barato, mas vulnerável a desastres físicos.
- **Sem plano de recuperação**: inviável, pois compromete confiabilidade e confiança dos usuários.

## Data

2025-12-23

## Autores

Equipe Play Learn Grow
