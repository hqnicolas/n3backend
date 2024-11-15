# N3 Gerenciador de Donativos

Manual de API: [Link](https://github.com/hqnicolas/n3backend/blob/main/MANUAL.md)

Manual de Instalação: [Link](https://github.com/hqnicolas/n3backend/blob/main/DEVELOP.md#manual-de-implanta%C3%A7%C3%A3o-do-gerenciador-de-doativos-n3)

Templates Rest API: [Insomnia.har](https://github.com/hqnicolas/n3backend/blob/main/Insomnia.har) [Insomnia.json](https://github.com/hqnicolas/n3backend/blob/main/Insomnia.json) [Insomnia.yaml](https://github.com/hqnicolas/n3backend/blob/main/Insomnia.yaml)

Manual do Desenvolvedor:  [Link](https://github.com/hqnicolas/n3backend/blob/main/DEVELOP.md#manual-de-desenvolvimento-do-gerenciador-de-doativos-n3)

## Resumo

A `N3` consiste no design e implementação de uma API RESTful para processamento de requisições de um cliente HTTP.

A aplicação deverá processar operações de **leitura**, **criação**, **atualização** e **exclusão** de dados para pelo menos **três recursos diferentes**, persistindo os dados em uma base de dados.

## Tema

* `Engajamento Comunitário`: gerenciamento de donativos;

## Requisitos Obrigatórios

* A aplicação implementa um serviço para processamento de requisições de um cliente HTTP;

## Requisitos Básicos

* **CRUD**: A aplicação implementa rotas para pelo menos três recursos distintos e relacionados entre si, cada um contendo operações de leitura, escrita, atualização e exclusão;
* **Arquitetura de Camadas**: A arquitetura da aplicação é baseada em camadas, possuindo clara distinção e preservando as responsabilidades entre *controllers*, *services*, *repositories* e *models*/*entities*;
* **Repository Pattern**: A aplicação utiliza o padrão Repository para abstrair o acesso e operações no banco de dados de maneira persistente;
* **DTO**: Todos os dados enviados no corpo de requisições devem ser validados conforme as regras de negócio, usando um DTO (Data Transfer Object) separado do modelo de domínio;
* **Status Code e JSON**: Todas as respostas e erros esperados possuem um código de status HTTP apropriado e um corpo de resposta formatado em JSON;
* **Tratamento de Erros**: Todas as rotas da aplicação retornam erros de maneira clara e amigável, com o código de status HTTP apropriado e um objeto de erro formatado em JSON;
* **Documentação**: O projeto possui um arquivo `README.md` com instruções claras para a execução do projeto, incluindo exemplos de requisições, respostas e erros esperados para todas as rotas da aplicação (pode ser utilizado o [Swagger](https://swagger.io/), desde que esteja especificado seu uso no `README.md`);
* **Variáveis de Ambiente**: Todas as variáveis necessárias para a execução do projeto podem ser configuradas por meio de variáveis de ambiente e/ou arquivos de configuração (**.env**, **.json**, **.yaml**, etc.);
* **Efetividade**: O projeto propõe uma solução viável e efetiva, apresentando regras de negócio condizentes com o tema proposto;

## Requisitos Opcionais

* **Autenticação**: A maioria das rotas da aplicação é protegida por um mecanismo de autenticação como **JWT** ou **OAuth2**, exigindo um token de acesso válido;
* A aplicação foi implantada em um servidor de produção e está disponível em um servidor de público na Internet;
* **Testes Unitários**: Os métodos implementados na camada de serviço possuem cobertura de pelo menos 80% em testes unitários dos métodos públicos na camada de serviço da aplicação;
* **Integração Externa**: A aplicação está integrado a uma API de terceiros, sendo capaz de reconhecer exceções (quando a API não estiver disponível);
* **CICD**: O projeto possui CICD configurado para a execução de testes, compilação e geração da imagem Docker;
* **Docker**: O projeto pode ser executado de maneira simples usando o comando `docker compose up --build`, incluindo as variáveis de ambiente e todos os demais serviços necessários para a execução do projeto;

## Organização do Repositório

O código-fonte do projeto deve estar organizado da seguinte forma:

* `src`: pasta com o código-fonte da aplicação;
* `Dockerfile`: arquivo de configuração da imagem Docker;
* `docker-compose.yml`: arquivo de configuração do Docker Compose;
* `README.md`: arquivo de instruções de execução do projeto;
* `AUTHORS.md`: arquivo com os nomes completos dos autores do projeto, um por linha;

---
## Documentação CRUD Mensagens

### 1. Criar uma Nova Mensagem
*Endpoint:* POST /api/mensagens

*Descrição:* Cria uma nova mensagem.

*Request Body:*
``` json
{
    "remetenteId": 1,
    "conteudo": "Conteúdo da mensagem",
    "dataEnvio": "2024-11-14T21:38:00Z"
}
```

*Response:*
- Status 201 Created
```` json
{
    "id": 1,
    "remetenteId": 1,
    "conteudo": "Conteúdo da mensagem",
    "dataEnvio": "2024-11-14T21:38:00Z"
}
````

### 2. Obter Todas as Mensagens
*Endpoint:* GET /api/mensagens

*Descrição:* Retorna todas as mensagens com o conteúdo e a data de envio.

*Response:*
- Status 200 OK
````json
[
    {
        "conteudo": "Conteúdo da mensagem 1",
        "dataEnvio": "2024-11-14T21:38:00Z"
    },
    {
        "conteudo": "Conteúdo da mensagem 2",
        "dataEnvio": "2024-11-14T21:39:00Z"
    }
]
````

### 3. Obter uma Mensagem por ID
*Endpoint:* GET /api/mensagens/{id}

*Descrição:* Retorna uma mensagem específica pelo ID com o conteúdo e a data de envio.

*Path Parameter:*
- id (integer): ID da mensagem.

*Response:*
- Status 200 OK
````json
{
    "conteudo": "Conteúdo da mensagem",
    "dataEnvio": "2024-11-14T21:38:00Z"
}
````

- Status 404 Not Found
```json
{
  "message": "Mensagem não encontrada"
}
```

### 4. Atualizar uma Mensagem
*Endpoint:* PUT /api/mensagens/{id}

*Descrição:* Atualiza uma mensagem existente.

*Path Parameter:*
- id (integer): ID da mensagem.

*Request Body:*
````json
{
    "remetenteId": 1,
    "conteudo": "Conteúdo atualizado da mensagem",
    "dataEnvio": "2024-11-14T21:38:00Z"
}
````

*Response:*
- Status 200 OK
````json
{
    "id": 1,
    "remetenteId": 1,
    "conteudo": "Conteúdo atualizado da mensagem",
    "dataEnvio": "2024-11-14T21:38:00Z"
}
````

- Status 404 Not Found
````json
{
  "message": "Mensagem não encontrada"
}
````

### 5. Deletar uma Mensagem
*Endpoint:* DELETE /api/mensagens/{id}

*Descrição:* Deleta uma mensagem existente.

*Path Parameter:*
- id (integer): ID da mensagem.

*Response:*
- Status 204 No Content
- Status 404 Not Found

````json
{
    "message": "Mensagem não encontrada"
}
````