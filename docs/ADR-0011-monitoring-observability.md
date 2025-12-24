# ADR-0011: Estratégia de Monitoramento e Observabilidade

## Status

Aceito

## Contexto

O projeto Play Learn Grow precisa garantir confiabilidade em produção, detectar falhas rapidamente e acompanhar métricas de uso.  
Com múltiplos serviços (frontend, backend, banco de dados), é essencial adotar uma estratégia de monitoramento e observabilidade que ofereça visibilidade completa do sistema.

## Decisão

Adotar uma estratégia baseada em:

- **Logs centralizados**: uso de ELK Stack (Elasticsearch, Logstash, Kibana) para coleta e análise.
- **Métricas de sistema e aplicação**: Prometheus para coleta e Grafana para visualização.
- **Alertas proativos**: integração com Slack/Email para notificar falhas críticas.
- **Tracing distribuído**: OpenTelemetry para rastrear requisições entre serviços.
- **Dashboards de saúde**: relatórios em tempo real sobre disponibilidade, latência e erros.

## Consequências

- **Positivas**:
  - Visibilidade completa do sistema em produção.
  - Detecção rápida de falhas e gargalos.
  - Melhor tomada de decisão baseada em métricas reais.
- **Negativas**:
  - Maior esforço inicial de configuração.
  - Necessidade de manutenção contínua das ferramentas.

## Alternativas consideradas

- **Logs locais apenas**: simples, mas sem visibilidade centralizada.
- **Ferramentas proprietárias (Datadog, New Relic)**: poderosas, mas com custo elevado.
- **Monitoramento parcial (apenas métricas de servidor)**: insuficiente para aplicações distribuídas.

## Data

2025-12-23

## Autores

Equipe Play Learn Grow
