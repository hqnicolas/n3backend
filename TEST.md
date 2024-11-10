### Etapa 1
Inicie o servidor como sudo usando `./gradlew bootRun`
O servidor inicia com sucesso, e você deve ver logs indicando que o aplicativo está em execução.

### Etapa 2
Abra seu navegador da web e visite http://localhost:8080/donation
O endpoint deve retornar uma lista de doações (se houver) ou uma lista vazia no formato JSON.

### Etapa 3
Use uma ferramenta como cURL para enviar uma solicitação POST para registrar uma nova doação com dados inválidos. Por exemplo, execute o seguinte comando no terminal:
```sh
curl -X POST http://localhost:8080/donation -H "Content-Type: application/json" -d '{"name": "", "type": "Material", "quantity": 5, "donor": "John Doe", "receivalDate": "2023-10-01", "validityPeriod": 30}'
```
O servidor deve retornar uma resposta 400 Bad Request com uma mensagem de erro indicando que o campo "Name" é obrigatório.

### Etapa 4
Use uma ferramenta como o cURL para enviar uma solicitação POST para registrar uma nova doação com dados válidos. Por exemplo, execute o seguinte comando no terminal:
```sh
curl -X POST http://localhost:8080/donation -H "Content-Type: application/json" -d '{"name": "Clothes", "type": "Material", "quantity": 5, "donor": "John Doe", "receivalDate": "2023-10-01", "validityPeriod": 30}'
```
O servidor deve retornar uma resposta 201 Created com os dados de doação registrados.

### Etapa 5
Use uma ferramenta como o cURL para enviar uma solicitação POST para gerar um relatório com dados inválidos. Por exemplo, execute o seguinte comando no terminal:
```sh
curl -X POST http://localhost:8080/donation/reports -H "Content-Type: application/json" -d '{"startDate": "2023-10-01", "endDate": "2023-10-05", "type": "", "donor": "John Doe"}'
```
O servidor deve retornar uma resposta 400 Bad Request com uma mensagem de erro indicando que o campo "Type" é obrigatório.

### Etapa 6
Use uma ferramenta como o cURL para enviar uma solicitação POST para gerar um relatório com dados válidos. Por exemplo, execute o seguinte comando no terminal:
```sh
curl -X POST http://localhost:8080/donation/reports -H "Content-Type: application/json" -d '{"startDate": "2023-10-01", "endDate": "2023-10-05", "type": "Material", "donor": "John Doe"}'
```
O servidor deve retornar uma resposta 200 OK com uma lista de doações que correspondem aos critérios do filtro.

### Etapa 7
Use uma ferramenta como o cURL para enviar uma solicitação POST para exportar um relatório como CSV com dados inválidos. Por exemplo, execute o seguinte comando no terminal:
```sh
curl -X POST http://localhost:8080/donation/reports/csv -H "Content-Type: application/json" -d '{"startDate": "2023-10-01", "endDate": "2023-10-05", "type": "", "donor": "John Doe"}'
```
O servidor deve retornar uma resposta 400 Bad Request com uma mensagem de erro indicando que o campo "Type" é obrigatório.

### Etapa 8
Use uma ferramenta como o cURL para enviar uma solicitação POST para exportar um relatório como CSV com dados válidos. Por exemplo, execute o seguinte comando no terminal:
```sh
curl -X POST http://localhost:8080/donation/reports/csv -H "Content-Type: application/json" -d '{"startDate": "2023-10-01", "endDate": "2023-10-05", "type": "Material", "donor": "John Doe"}'
```
O servidor deve retornar uma resposta 200 OK e o arquivo CSV deve ser baixado.

### Etapa 9
Use uma ferramenta como o cURL para enviar uma solicitação POST para exportar um relatório como PDF com dados inválidos. Por exemplo, execute o seguinte comando no terminal:
```sh
curl -X POST http://localhost:8080/donation/reports/pdf -H "Content-Type: application/json" -d '{"startDate": "2023-10-01", "endDate": "2023-10-05", "type": "", "donor": "John Doe"}'
```
O servidor deve retornar uma resposta 400 Bad Request com uma mensagem de erro indicando que o campo "Type" é obrigatório.

### Etapa 10
Use uma ferramenta como o cURL para enviar uma solicitação POST para exportar um relatório como PDF com dados válidos. Por exemplo, execute o seguinte comando no terminal:
```sh
curl -X POST http://localhost:8080/donation/reports/pdf -H "Content-Type: application/json" -d '{"startDate": "2023-10-01", "endDate": "2023-10-05", "type": "Material", "donor": "John Doe"}'
```
o servidor deve retornar uma resposta 200 OK e o arquivo PDF deve ser baixado.