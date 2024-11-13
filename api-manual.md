# Manual de API do Gerenciador de Doativos

## Introdução
Este manual descreve todos os aplicativo e funções da API do Gerenciador de Doativos, 
facilitando o uso com clientes REST API como Postman ou Insomnia. 
Siga as instruções e exemplos fornecidos para enviar Requisiçãos e receber Respostas.

## Instruções de Uso
### 1. Usando Postman
1. Abra o Postman e crie uma nova Requisição.
2. Configure a URL e o método conforme descrito nos aplicativo acima.
3. Para Requisiçãos POST, PUT e DELETE, adicione o Corpo de Requisição no formato JSON.
4. Envie a Requisição e observe a resposta.

### 2. Usando Insomnia
1. Abra o Insomnia e crie uma nova Requisição.
2. Configure a URL e o método conforme descrito nos aplicativo acima.
3. Para Requisiçãos POST, PUT e DELETE, adicione o Corpo de Requisição no formato JSON.
4. Envie a Requisição e observe a resposta.

## Exemplos de Requisiçãos e Respostas
### Exemplo 1: Recuperar a lista de todas as doações
- **Requisição**:
  ```
  GET https://n3.acodes.com.br/donations
  GET http://localhost:8080/donations
  ```
- **Resposta**:
  ```json
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

### Exemplo 2: Registrar uma nova doação
- **Requisição**:
  ```
  POST https://n3.acodes.com.br/donations
  POST http://localhost:8080/donations
  Corpo: application/json
  {
    "name": "Feijão Carioca - 50kg",
    "type": "Material",
    "quantity": 100,
    "donor": "João Silva",
    "receivalDate": "2024-01-01",
    "expiryDate": "2024-12-31",
    "validityPeriod": 365
  }
  ```
- **Resposta**:
  ```json
  {
    "id": 2,
    "name": "Feijão Carioca - 50kg",
    "type": "Material",
    "quantity": 100,
    "donor": "João Silva",
    "receivalDate": "2024-01-01",
    "expiryDate": "2024-12-31",
    "validityPeriod": 365
  }
  ```

### Exemplo 3: Atualizar uma doação específica
- **Requisição**:
  ```
  PUT https://n3.acodes.com.br/donations/1
  PUT http://localhost:8080/donations/1
  Corpo: application/json
  {
    "name": "Calça Jeans Infantil",
    "type": "Material",
    "quantity": 150,
    "donor": "Maria Carvalho",
    "receivalDate": "2024-02-01",
    "expiryDate": "2024-01-31",
    "validityPeriod": 365
  }
  ```
- **Resposta**:
  ```json
  {
    "id": 1,
    "name": "Calça Jeans Infantil",
    "type": "Material",
    "quantity": 150,
    "donor": "Maria Carvalho",
    "receivalDate": "2024-02-01",
    "expiryDate": "2024-01-31",
    "validityPeriod": 365
  }
  ```

### Exemplo 4: Deletar uma doação específica
- **Requisição**:
  ```
  DELETE https://n3.acodes.com.br/donations/1
  DELETE http://localhost:8080/donations/1
  ```
- **Resposta**:
  ```
  "Doação excluída com sucesso: {id}"
  ```

### Exemplo 5: Gerar um relatório de doações
- **Requisição**:
  ```
  POST https://n3.acodes.com.br/reports
  POST http://localhost:8080/reports
  Corpo: application/json
  {
    "startDate": "2024-01-01",
    "endDate": "2024-12-31",
    "type": "Material",
    "donor": "João Silva"
  }
  ```
- **Resposta**:
  ```json
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

## aplicativo com front-end

### 1. Recupera a lista de todas as doações
- **URL**: `https://n3.acodes.com.br/donations`
- **Método**: `GET`
- **Resposta**: Array de objetos de doação JSON.
- **Exemplo de Resposta**:
  ```json
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

### 2. Registra uma nova doação
- **URL**: `https://n3.acodes.com.br/donations`
- **Método**: `POST`
- **Corpo de Requisição**: Objeto JSON com detalhes da doação.
- **Exemplo de corpo de Requisição**:
  ```json
  {
    "name": "Feijão Carioca - 50kg",
    "type": "Material",
    "quantity": 100,
    "donor": "João Silva",
    "receivalDate": "2024-01-01",
    "expiryDate": "2024-12-31",
    "validityPeriod": 365
  }
  ```
- **Resposta**: Objeto JSON da doação criada.

### 3. Recupera os detalhes de uma doação específica
- **URL**: `https://n3.acodes.com.br/donations/{id}`
- **Método**: `GET`
- **Resposta**: Objeto JSON da doação.
- **Exemplo de Resposta**:
  ```json
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

### 4. Atualiza os detalhes de uma doação específica
- **URL**: `https://n3.acodes.com.br/donations/{id}`
- **Método**: `PUT`
- **Corpo de Requisição**: Objeto JSON com detalhes atualizados da doação.
- **Exemplo de corpo de Requisição**:
  ```json
  {
    "name": "Calça Jeans Infantil",
    "type": "Material",
    "quantity": 150,
    "donor": "Maria Carvalho",
    "receivalDate": "2024-02-01",
    "expiryDate": "2024-01-31",
    "validityPeriod": 365
  }
  ```
- **Resposta**: Objeto JSON da doação atualizada.

### 5. Deleta uma doação específica
- **URL**: `https://n3.acodes.com.br/donations/{id}`
- **Método**: `DELETE`
- **Resposta**: "Doação excluída com sucesso: {id}"

### 6. Gera um relatório de doações
- **URL**: `https://n3.acodes.com.br/reports`
- **Método**: `POST`
- **Corpo de Requisição**: Objeto JSON com filtros para o relatório.
- **Exemplo de corpo de Requisição**:
  ```json
  {
    "startDate": "2024-01-01",
    "endDate": "2024-12-31",
    "type": "Material",
    "donor": "João Silva"
  }
  ```
- **Resposta**: Array de doações filtradas JSON.

## Este manual fornece uma descrição detalhada e exemplos de uso para todos os aplicativo da API do Gerenciador de Doativos.
### Siga as instruções e exemplos fornecidos para integrar a API em suas aplicações.
