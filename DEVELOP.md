# Manual de Implantação do Gerenciador de Donativos N3


## 1. Clone o Repositório
1. Abra um terminal ou prompt de comando.
2. Navegue até o diretório onde deseja clonar o repositório.
3. Execute o seguinte comando para clonar o repositório:
```sh
git clone https://github.com/hqnicolas/n3backend.git
```
## 2. Navegue até o repositório clonado:
```sh
cd n3backend
```

## 3. Construa o Projeto
1. **Construa o Backend**:
- Navegue até a raiz do diretório do projeto.
- Execute o seguinte comando para construir o aplicativo:
```sh
docker compose build
```

## 4. Executando o aplicativo
1. **Execute o backend**:
- Navegue até a raiz do diretório do projeto.
- Execute o seguinte comando para iniciar o aplicativo:
```sh
docker compose up
```
---
---
---

# Manual de Desenvolvimento do Gerenciador de Doativos N3

## 1. Pré-requisitos
Antes de começar, certifique-se de ter os seguintes pré-requisitos instalados:
- **Java Development Kit (JDK)**: Certifique-se de ter o Java 23 instalado. Você pode verificar a versão executando:
```sh
java -version
```

- **Docker**: Certifique-se de que o Docker esteja instalado e em execução. Você pode verificar a versão executando:
```sh
docker --version
```

- **Git**: Certifique-se de que o Git esteja instalado. Você pode verificar a versão executando:
```sh
git --version
```

- **PostgreSQL**: Certifique-se de que o PostgreSQL esteja instalado e em execução. Você pode verificar a versão executando:
```sh
psql --version
```

## 2. Clonando o Repositório
1. Abra um terminal ou prompt de comando.
2. Navegue até o diretório onde deseja clonar o repositório.
3. Execute o seguinte comando para clonar o repositório:
```sh
git clone https://github.com/hqnicolas/n3backend.git
```
4. Navegue até o repositório clonado:
```sh
cd n3backend
```

## 3. Construindo o Projeto
1. **Construa o Backend**:
- Navegue até a raiz do diretório do projeto.
- Execute o seguinte comando para construir o aplicativo:
```sh
./gradlew build
```

2. **Construa o Frontend**:
- Navegue até o diretório frontend dentro do projeto.
- Execute o seguinte comando para criar o frontend (se aplicável):
```sh
npm install
npm run build
```

## 4. Configurando o banco de dados
1. **Crie o banco de dados**:
- Execute o seguinte comando para criar o banco de dados:
```sh
psql -U postgres -c "CREATE DATABASE donation_db;"
```
2. **Crie o usuário do banco de dados**:
- Execute o seguinte comando para criar o usuário do banco de dados:
```sh
psql -U postgres -c "CREATE USER postgres PASSWORD 'secret';"
```
3. **Conceda permissões**:
- Execute o seguinte comando para conceder as permissões necessárias:
```sh
psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE donation_db TO postgres;"
```
4. **Verifique a conexão do banco de dados**:
- Execute o seguinte comando para verificar a conexão:
```sh
psql -U postgres -d donation_db
```

## 5. Executando o aplicativo
1. **Execute o backend**:
- Navegue até a raiz do diretório do projeto.
- Execute o seguinte comando para iniciar o aplicativo:
```sh
docker-compose up --build
```

2. **Execute o frontend**:
- Navegue até o diretório do frontend.
- Execute o seguinte comando para iniciar o frontend (se aplicável):
```sh
npm start
```

3. **Acesse o aplicativo**:
- Abra um navegador da web e navegue até `http://localhost:8080` para acessar o aplicativo.

## 6. Configuração
1. **Configuração do backend**:
- Abra o arquivo `application.properties` localizado no diretório `src/main/resources`.
- Certifique-se de que as seguintes propriedades estejam definidas:
```propriedades
spring.datasource.url=jdbc:postgresql://0.0.0.0:5439/donation_db
spring.datasource.username=postgres
spring.datasource.password=secret
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
2. **Configuração do Frontend**:
- Abra os arquivos de configuração do frontend (se aplicável) e certifique-se de que as configurações necessárias estejam definidas.

## 7. Dicas para solução de problemas
1. **Backend**:
- Verifique os logs do aplicativo localizados no diretório `logs` para ver se há erros.
- Use o comando a seguir para verificar o status do aplicativo:
```sh
docker ps
```
2. **Frontend**:
- Verifique o console do navegador para ver se há erros de JavaScript.
- Use as ferramentas de desenvolvedor do navegador para inspecionar solicitações e respostas de rede.


---

### Modelo para adicionar novos recursos
Ao adicionar novos recursos:
1. **Identifique os arquivos**:
- Determine quais arquivos precisam ser modificados ou criados.
2. **Atualize o manual**:
- Adicione as etapas necessárias ao manual de implantação.
3. **Teste as alterações**:
- Garanta que as alterações sejam testadas minuciosamente e não quebrem a funcionalidade existente.
4. **Commit e Push**:
- Commit as alterações no repositório e push para a ramificação remota.

---

### Exemplo de adição de um novo recurso
1. **Identifique os arquivos**:
- `src/main/java/com/gerenciamento/backend/DonationService.java`
- `src/main/resources/application.properties`
2. **Atualize o manual**:
- Adicione as etapas para criar e executar o novo recurso.
3. **Teste as alterações**:
- Execute testes de unidade e testes de integração.
4. **Commit e Push**:
- Commit t
