# Engajamento Comunitário: Gerenciamento de Donativos

Este documento descreve um sistema para facilitar o engajamento comunitário, conectando voluntários com necessidades da comunidade, gerenciando doações e mapeando abrigos.

**Executando**

Para executar o projeto:

1. **Modifique o arquivo `.env.template` para `.env`** e preencha as variáveis de ambiente, ou defina diretamente pelo terminal.
2. **Rode o comando `docker compose up -d` na raiz do projeto**.

**Tecnologias Utilizadas**

- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker**
- **Lombok**

**Gerenciamento de Donativos**

O módulo de gerenciamento de doações permitirá:

### Cadastro de Doações

- **Registro detalhado de doações recebidas**: Incluindo tipo de doação (material, financeira, etc.), quantidade, doador e data.
- **Controle de Estoque**: Para doações materiais, o sistema manterá um controle de estoque, permitindo o acompanhamento da quantidade disponível e a alocação para diferentes necessidades.
- **Relatórios**: Geração de relatórios para monitorar o volume de doações recebidas, a alocação de recursos e a transparência na gestão.

### Integração com o Match-System

O sistema poderá integrar-se com o match-system, permitindo que as doações sejam direcionadas para as organizações e iniciativas com necessidades específicas.

### Regras de Negócio

- **Doações com data de validade expirada não podem ser registradas em novos estoques**.
- **O tempo de validade de uma doação deve ser entre 1 e 999 dias**.
- **A data de expiração da doação não pode estar no passado no momento do cadastro**.
- **Os tipos de doação devem ser: Material, Financeira, etc.**.

### Rotas

#### Doações

**GET /doacoes[?tipo=:tipo]**

- **Lista todas as doações disponíveis**. Filtra pela tipo se for informado.

**Resposta:** JSON [ { "id": 1, "nome": "Doação 1", "tipo": "Doação 1", "quantidade": 10, "doador": "Doador 1", "dataRecebimento": "2021-01-01", } ]

**Respostas de erro:**
* 400 - tipo inválido;
* 204 - nenhuma doação encontrada;

**GET /doacoes/{id}**

- **Lista uma doação específica pelo id**.

**Resposta:** JSON { "id": 1, "nome": "Doação 1", "tipo": "Doação 1", "quantidade": 10, "doador": "Doador 1", "dataRecebimento": "2021-01-01", }

**Respostas de erro:**
* 400 - id inválido;
* 404 - doação não encontrada;

**POST /doacoes**

- **Cadastra uma nova doação**.

**Corpo da Requisição:** JSON { "nome": "Doação de Alimentos", "tipo": "Material", "quantidade": 50, "doador": "Doador X", "dataRecebimento": "2023-03-15", }

**Corpo da Resposta:** JSON [ { "id": 1, "nome": "Doação de Alimentos", "tipo": "Material", "quantidade": 50, "doador": "Doador X", "dataRecebimento": "2023-03-15", } ]

**Respostas de erro:**
* 400 - data de expiração inválida;
* 400 - tempo de validade inválido;
* 400 - tipo inválido;
* 404 - doação não encontrada;
* 409 - doação já cadastrada;

**PATCH /doacoes/{id}**

- **Atualiza as informações de uma doação específica pelo id**.

**Corpo da Requisição:** JSON { "quantidade": 60, }

**Corpo da Resposta:** JSON [ { "id": 1, "nome": "Doação de Alimentos", "tipo": "Material", "quantidade": 60, "doador": "Doador X", "dataRecebimento": "2023-03-15", } ]

**Respostas de erro:**
* 400 - id inválido;
* 400 - data de expiração inválida;
* 400 - tempo de validade inválido;
* 400 - tipo inválido;
* 404 - doação não encontrada;

**DELETE /doacoes/{id}**

- **Deleta uma doação específica pelo id**.

**DELETE /doacoes/{id}**

**Corpo da Resposta:** JSON [ { "id": 1, "nome": "Doação de Alimentos", "tipo": "Material", "quantidade": 60, "doador": "Doador X", "dataRecebimento": "2023-03-15", } ]

**Respostas de erro:**
* 400 - id inválido;
* 404 - doação não encontrada;
* 409 - doação em uso;