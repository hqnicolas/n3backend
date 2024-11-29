# Manual da API - Sistema de Gerenciamento de Doações

## Introdução
Este manual descreve todos os endpoints e funções da API do Gerenciador de Doativos, facilitando o uso com clientes REST API como Postman ou Insomnia. Siga as instruções e exemplos fornecidos para enviar Requisições e receber Respostas.

## Uso do Postman
1. Abra o Postman e crie uma nova Requisição.
2. Configure a URL e o método conforme descrito nos exemplos abaixo.
3. Para Requisições POST, PUT e DELETE, adicione o Corpo de Requisição no formato JSON.
4. Envie a Requisição e observe a resposta.

## Uso do Insomnia
1. Abra o Insomnia e crie uma nova Requisição.
2. Configure a URL e o método conforme descrito nos exemplos abaixo.
3. Para Requisições POST, PUT e DELETE, adicione o Corpo de Requisição no formato JSON.
4. Envie a Requisição e observe a resposta.

**API Endpoints**

* **doação Endpoints**
`POST /donation`: Registre uma nova doação
`GET /donation`: Obtenha uma lista de todas as doações
`GET /donation/{id}`: Obtenha uma doação específica por ID
`PUT /donation/{id}`: Atualize uma doação específica
`DELETE /donation/{id}`: Exclua uma doação específica
**relatório Endpoints**
`POST /reports/generate`: Gere um relatório com base em filtros específicos
`POST /reports/csv`: Exporte um relatório como um arquivo CSV
`GET /donation/reports/pdf/{startDate}/{endDate}/{donationType}/{donor}`: Exporte um relatório como um arquivo PDF
**usuário Endpoints**
`POST /users`: Crie um novo usuário
`GET /users`: Obtenha uma lista de todos os usuários
`GET /users/{id}`: Obtenha um usuário específico por ID
`PUT /users/{id}`: Atualiza um usuário específico
`DELETE /users/{id}`: Exclui um usuário específico
**mensagem Endpoints**
`POST /messages`: Cria uma nova mensagem
`GET /messages`: Obtém uma lista de todas as mensagens
`GET /messages/{id}`: Obtém uma mensagem específica por ID
`PUT /messages/{id}`: Atualiza uma mensagem específica
`DELETE /messages/{id}`: Exclui uma mensagem específica

---

# Documentação CRUD Doações

## 1. Criar uma Doação
*Endpoint:* POST /doacao

*Descrição:* Registra uma nova doação.

*Request Body:*
```json
{
    "name": "Nome da Doação",
    "type": "Material", // Ou "Financeiro"
    "quantity": 100,
    "donor": "João Silva",
    "receiverDate": "2024-11-14",
    "expiryDate": "2025-11-14",
    "validityPeriod": 12
}
```

*Response:*
- Status 201 Created
```json
{
    "id": 1,
    "name": "Nome da Doação",
    "type": "Material",
    "quantity": 100,
    "donor": "Maria Caetana",
    "receiverDate": "2024-11-14",
    "expiryDate": "2025-11-14",
    "validityPeriod": 12
}
```

## 2. Obter Todas as Doações
*Endpoint:* GET /doacao

*Descrição:* Retorna uma lista de todas as doações registradas.

*Response:*
- Status 200 OK
```json
[
    {
        "id": 1,
        "name": "Nome da Doação",
        "type": "Material",
        "quantity": 100,
        "donor": "João Silva",
        "receiverDate": "2024-11-14",
        "expiryDate": "2025-11-14",
        "validityPeriod": 12
    }
]
```

## 3. Gerar Relatório de Doações
*Endpoint:* POST /relatorio

*Descrição:* Gera um relatório com base nos parâmetros fornecidos.

*Request Body:*
```json
{
    "startDate": "2024-01-01",
    "endDate": "2024-12-31",
    "type": "Material", // ou "Financeiro"
    "donor": "João Silva"
}
```

*Response:*
- Status 200 OK
```json
[
    {
        "id": 1,
        "name": "Nome da Doação",
        "type": "Material",
        "quantity": 100,
        "donor": "João Silva",
        "receiverDate": "2024-11-14",
        "expiryDate": "2025-11-14",
        "validityPeriod": 12
    }
]
```

## 4. Exportar Relatório
*Endpoint:* GET /donation/reports/pdf/{startDate}/{endDate}/{donationType}/{donor}
*Example:* GET `http://0.0.0.0:8080/donation/reports/pdf/2024-01-01/2025-01-31/Material/João Silva`

*Descrição:* Exporta o relatório gerado em formatos CSV ou PDF.

*Query Parameters:*
- format: "csv" ou "pdf"

*Response:*
- Status 200 OK (com download do arquivo)
```json
{
    "message": "Relatório exportado com sucesso"
}
```


---

# Documentação CRUD Mensagens

### 1. Criar uma Nova Mensagem
*Endpoint:* POST /mensagens

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
*Endpoint:* GET /mensagens

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
*Endpoint:* GET /mensagens/{id}

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
*Endpoint:* PUT /mensagens/{id}

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
*Endpoint:* DELETE /mensagens/{id}

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

---

# Documentação CRUD Usuários


### 1. Crie um novo usuário


*Endpoint:* POST /users


* Exemplo de requisição:
```
{
  "name": "João Silva",
  "email": "joao.silva@gmail.com",
  "password": "senha123",
  "dateOfBirth": "1990-01-01",
  "gender": "Masculino",
  "location": "São Paulo",
  "preferences": "Esportes",
  "biography": "Sou um desenvolvedor de software",
  "photos": "https://facebook.com/foto.jpg",
  "interests": "Música"
}
```
* Exemplo de resposta:
```json
{
	"mensagem": "Usuário criado com sucesso!"
}
```

### Obtenha uma lista de todos os usuários

*Endpoint:* GET /users


* Exemplo de requisição:
```bash
GET /users
```
* Exemplo de resposta:
```json
[
  {
    "id": 1,
    "name": "João Silva",
    "email": "joao.silva@gmail.com",
    "password": "senha123",
    "dateOfBirth": "1990-01-01",
    "gender": "Masculino",
    "location": "São Paulo",
    "preferences": "Esportes",
    "biography": "Sou um desenvolvedor de software",
    "photos": "https://facebook.com/foto.jpg",
    "interests": "Música"
  },
  {
    "id": 2,
    "name": "Maria Silva",
    "email": "maria.silva@gmail.com",
    "password": "senha123",
    "dateOfBirth": "1995-01-01",
    "gender": "Feminino",
    "location": "Rio de Janeiro",
    "preferences": "Cinema",
    "biography": "Sou uma desenvolvedora de software",
    "photos": "https://facebook.com/foto2.jpg",
    "interests": "Leitura"
  }
]
```

### Obtenha um usuário específico por ID

 *Endpoint:* GET /users/{id}

* Exemplo de requisição:
```bash
GET /users/1
```
* Exemplo de resposta:
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao.silva@gmail.com",
  "password": "senha123",
  "dateOfBirth": "1990-01-01",
  "gender": "Masculino",
  "location": "São Paulo",
  "preferences": "Esportes",
  "biography": "Sou um desenvolvedor de software",
  "photos": "https://facebook.com/foto.jpg",
  "interests": "Música"
}
```

### Atualize um usuário específico

*Endpoint:* PUT /users/{id}



* Exemplo de requisição:
```json
{
  "name": "João Silva",
  "email": "joao.silva@gmail.com",
  "password": "senha123",
  "dateOfBirth": "1990-01-01",
  "gender": "Masculino",
  "location": "São Paulo",
  "preferences": "Esportes",
  "biography": "Sou um desenvolvedor de software",
  "photos": "https://facebook.com/foto.jpg",
  "interests": "Música"
}
```
* Exemplo de resposta:
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao.silva@gmail.com",
  "password": "senha123",
  "dateOfBirth": "1990-01-01",
  "gender": "Masculino",
  "location": "São Paulo",
  "preferences": "Esportes",
  "biography": "Sou um desenvolvedor de software",
  "photos": "https://facebook.com/foto.jpg",
  "interests": "Música"
}
```

### Exclua um usuário específico

*Endpoint:* DELETE /users/{id}


* Exemplo de requisição:
```bash
DELETE /users/1
```
* Exemplo de resposta:

- Status 204 No Content
- Status 404 Not Found

````json
{
    "message": "Mensagem não encontrada"
}
````


## Conclusão
Este manual fornece uma visão geral detalhada de todos os endpoints e funções da API do Gerenciador de Doativos. Use-o para facilitar o desenvolvimento, testes e integração com outros sistemas.
