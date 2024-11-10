:

- `donation-form.js`
- `donation-list.js`
- `donation-reports.js`
- `index.html`
- `DonativosApplication.java`
- `DonationController.java`
- `ReportController.java`
- `Donation.java`
- `ReportFilter.java`
- `DonationRepository.java`
- `DonationService.java`
- `ReportService.java`

### Objetivo

Este arquivo fornece uma visão geral do projeto, incluindo seu objetivo, recursos e configuração técnica. Ele também inclui instruções para configurar e executar o aplicativo.

### Recursos

1. **Registrar doação**:
- **Campos do formulário**: O formulário de doação inclui campos para o nome, tipo, quantidade, doador, data de recebimento, data de validade e período de validade da doação.
- **Validação de formulário**: Cada campo tem validação para garantir que os dados necessários sejam fornecidos e que os dados estejam no formato correto.
- **Data de validade** não pode estar no passado.
- **Período de validade** deve estar entre 1 e 999 dias.
- **Tipo de doação** deve ser um dos tipos permitidos (Material, Financeiro).
- **Quantidade** deve ser um número positivo.
- **Envio de formulário**: Quando o formulário é enviado, os dados são enviados ao servidor usando uma solicitação POST para o endpoint `/donation`. O servidor retorna uma resposta e o aplicativo exibe uma mensagem de sucesso ou erro com base na resposta.
- **Redefinir formulário**: Após um envio bem-sucedido, o formulário é redefinido para permitir o registro de uma nova doação.

2. **Lista de doações**:
- **Exibição de tabela**: O aplicativo exibe uma lista paginada de doações em uma tabela HTML. A tabela inclui colunas para o nome, tipo, quantidade, doador, data de recebimento, data de validade e período de validade de cada doação.
- **Paginação**: A lista é paginada para garantir que o usuário possa navegar por várias páginas de doações.
- **Buscando dados**: O aplicativo busca dados de doações do servidor usando o endpoint `/donation` com parâmetros de consulta para paginação.

3. **Relatórios de doações**:
- **Filtros de formulário**: O formulário de relatório permite que os usuários filtrem doações com base em um intervalo de datas, tipo de doação e doador.
- **Gerar relatório**: Quando o formulário é enviado, os dados são enviados ao servidor usando uma solicitação POST para o endpoint `/donation/reports`. O servidor retorna as doações filtradas, que são exibidas em uma tabela.
- **Exportar para CSV**: O aplicativo fornece um botão para exportar as doações filtradas como um arquivo CSV.
- **Exportar para PDF**: O aplicativo fornece um botão para exportar as doações filtradas como um arquivo PDF.

4. **Visualização detalhada de doações**:
- **URL**: `/donation/view/{id}`
- **Funcionalidade**: Exibe informações detalhadas sobre uma doação específica. Inclui um link para editar ou excluir a doação.

5. **Editar doação**:
- **URL**: `/donation/edit/{id}`
- **Funcionalidade**: Os usuários devem poder editar os detalhes de uma doação registrada. O formulário deve manter os valores atuais e aplicar as mesmas regras de validação. Após a atualização bem-sucedida, o usuário deve ver uma mensagem de confirmação.

6. **Excluir doação**:
- **URL**: `/donation/delete/{id}`
- **Funcionalidade**: Os usuários devem poder excluir uma doação registrada. Uma mensagem de confirmação deve ser exibida antes de excluir a doação.

### Especificação técnica

#### Plataforma/tecnologias

- **Frontend**:
- **JavaScript**: Usado para scripts do lado do cliente e manipulação de interações do usuário.
- **HTML**: Usado para estruturar a interface do usuário.
- **CSS**: Usado para estilizar o aplicativo.
- **Bootstrap 5**: Usado para um layout responsivo e amigável. O Bootstrap é carregado do CDN.
- **Fetch API**: Usado para fazer solicitações HTTP ao servidor.

- **Backend**:
- **Spring Boot**: Usado para criar a API RESTful e gerenciar a lógica do lado do servidor do aplicativo.
- **Java**: Usado para escrever o código de backend.
- **JPA (Java Persistence API)**: Usado para interações com o banco de dados.
- **PostgreSQL**: Usado como banco de dados para armazenar e recuperar dados de doações.
- **Lombok**: Usado para reduzir o código boilerplate gerando automaticamente getters, setters e outros métodos utilitários.
- **Anotações de validação**: Usadas do pacote `javax.validation.constraints` para garantir a integridade dos dados.
- **Geração de CSV e PDF**: bibliotecas usadas como `Apache Commons CSV` e `iTextPDF` para gerar arquivos CSV e PDF.

#### Arquitetura

- **Frontend**:
- **index.html**: o principal ponto de entrada do aplicativo, que inclui a estrutura HTML e carrega os arquivos JavaScript necessários.
- **donation-form.js**: lida com o envio do formulário para registrar novas doações.
- **donation-list.js**: lida com a exibição e paginação da lista de doações.
- **donation-reports.js**: lida com o envio do formulário para gerar e exportar relatórios de doações.
- **donation-view.js**: lida com a exibição de informações detalhadas sobre doações.
- **donation-edit.js**: lida com o envio do formulário para editar doações.
- **donation-delete.js**: lida com a exclusão de doações.

- **Backend**