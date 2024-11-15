# Manual de API do Gerenciador de Doativos

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

## Documentação CRUD Doações

### 1. Registrar uma Nova Doação
- **Requisição**:
  ```http
  POST http://0.0.0.0:8080/donation
  Content-Type: application/json

  {
    "name": "Feijão Carioca - 50kg",
    "type": "Material",
    "quantity": 100,
    "donor": "João Silva",
    "receivalDate": "2024-01-01",
    "validityPeriod": 365
  }
  ```
- **Resposta**:
  ```http
  HTTP/1.1 201 Created
  Content-Type: application/json

  {
    "mensagem": "Doação registrada com sucesso!"
  }
  ```

### 2. Recuperar a Lista de Todas as Doações
- **Requisição**:
  ```http
  GET http://0.0.0.0:8080/donation?page=0&size=20
  ```
- **Resposta**:
  ```http
  HTTP/1.1 200 OK
  Content-Type: application/json

  [
    {
      "id": 1,
      "name": "Feijão Carioca - 50kg",
      "type": "Material",
      "quantity": 100,
      "donor": "João Silva",
      "receivalDate": "2024-01-01",
      "expiryDate": "2024-12-31",
      "validityPeriod": 365
    }
  ]
  ```

### 3. Recuperar Detalhes de uma Doação Específica
- **Requisição**:
  ```http
  GET http://0.0.0.0:8080/donation/1
  ```
- **Resposta**:
  ```http
  HTTP/1.1 200 OK
  Content-Type: application/json

  {
    "id": 1,
    "name": "Feijão Carioca - 50kg",
    "type": "Material",
    "quantity": 100,
    "donor": "João Silva",
    "receivalDate": "2024-01-01",
    "expiryDate": "2024-12-31",
    "validityPeriod": 365
  }
  ```

### 4. Atualizar uma Doação
- **Requisição**:
  ```http
  PUT http://0.0.0.0:8080/donation/1
  Content-Type: application/json

  {
    "name": "Feijão Carioca - 60kg",
    "type": "Material",
    "quantity": 120,
    "donor": "João Silva",
    "receivalDate": "2024-01-01",
    "expiryDate": "2024-12-31",
    "validityPeriod": 365
  }
  ```
- **Resposta**:
  ```http
  HTTP/1.1 200 OK
  Content-Type: application/json

  {
    "id": 1,
    "name": "Feijão Carioca - 60kg",
    "type": "Material",
    "quantity": 120,
    "donor": "João Silva",
    "receivalDate": "2024-01-01",
    "expiryDate": "2024-12-31",
    "validityPeriod": 365
  }
  ```

### 5. Excluir uma Doação
- **Requisição**:
  ```http
  DELETE http://0.0.0.0:8080/donation/1
  ```
- **Resposta**:
  ```http
  HTTP/1.1 204 No Content
  ```

### 6. Gerar um Relatório
- **Requisição**:
  ```http
  POST http://0.0.0.0:8080/donation/reports/generate
  Content-Type: application/json

  {
    "startDate": "2024-01-01",
    "endDate": "2024-12-31",
    "donationType": "Material",
    "donor": "João Silva"
  }
  ```
- **Resposta**:
  ```http
  HTTP/1.1 200 OK
  Content-Type: application/json

  [
    {
      "id": 1,
      "name": "Feijão Carioca - 60kg",
      "type": "Material",
      "quantity": 120,
      "donor": "João Silva",
      "receivalDate": "2024-01-01",
      "expiryDate": "2024-12-31",
      "validityPeriod": 365
    }
  ]
  ```

### 7. Exportar Relatório como CSV
- **Requisição**:
  ```http
  POST http://0.0.0.0:8080/donation/reports/csv
  Content-Type: application/json

  {
    "startDate": "2024-01-01",
    "endDate": "2024-12-31",
    "donationType": "Material",
    "donor": "João Silva"
  }
  ```
- **Resposta**:
  ```http
  HTTP/1.1 200 OK
  Content-Disposition: attachment; filename=donation_report.csv
  Content-Type: text/csv

  "Nome,Tipo,Quantidade,Doador,Data de recebimento,Data de validade,Período de validade Feijão Carioca - 60kg,Material,120,João Silva,2024-01-01,2024-12-31,365"
  ```

### 8. Exportar Relatório como PDF
- **Requisição**:
  ```http
  POST http://0.0.0.0:8080/donation/reports/pdf
  Content-Type: application/json

  {
    "startDate": "2024-01-01",
    "endDate": "2024-12-31",
    "donationType": "Material",
    "donor": "João Silva"
  }
  ```
- **Resposta**:
  ```http
  HTTP/1.1 200 OK
  Content-Disposition: attachment; filename=donation_report.pdf
  Content-Type: application/pdf
  ```

## Conclusão
Este manual fornece uma visão geral detalhada de todos os endpoints e funções da API do Gerenciador de Doativos. Use-o para facilitar o desenvolvimento, testes e integração com outros sistemas.
