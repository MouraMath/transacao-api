Transação API Português || Transaction API  English


Este projeto é uma API REST para gerenciar transações financeiras e calcular estatísticas das transações realizadas nos últimos 60 segundos.
A API foi desenvolvida com Java 21 e Spring Boot 3.4.3.

Requisitos
Para rodar esta aplicação, é necessário:

Java: JDK 21

Gradle: Versão 8.4 ou superior

Git: Para clonar o repositório


Como Configurar o Projeto
Clone o Repositório
bash
git clone https://github.com/MouraMath/transacao-api.git
cd transacao-api
Compile o Projeto
bash
./gradlew build
Execute o Projeto
bash
./gradlew bootRun

Documentação da API
Receber Transações
POST /transacao

Parâmetro	Tipo	Descrição
valor	BigDecimal	Obrigatório. O valor da transação
dataHora	OffsetDateTime	Obrigatório. O horário que a transação ocorreu
Respostas:

201 Created: Transação registrada com sucesso

204 No Content: Transação rejeitada por ser muito antiga (mais de 60 segundos)

Limpar Transações
DELETE /transacao

Calcular Estatísticas
GET /estatistica

Parâmetro	Tipo	Descrição
intervaloSegundos	integer	Não Obrigatório. O padrão default é 60s
Executando os Testes
Para executar os testes unitários do projeto:

bash
./gradlew test
Tecnologias Utilizadas
Java 21

Spring Boot 3.4.3

Lombok

Gradle

JUnit 5 para testes

------------------------

ENGLISH

Transaction API
This project is a REST API for managing financial transactions and calculating statistics of transactions performed in the last 60 seconds.
The API was developed with Java 21 and Spring Boot 3.4.3.

Requirements
To run this application, you need:

Java: JDK 21

Gradle: Version 8.4 or higher

Git: To clone the repository



How to Set Up the Project
Clone the Repository
bash
git clone https://github.com/yourusername/transaction-api.git
cd transaction-api
Compile the Project
bash
./gradlew build
Run the Project
bash
./gradlew bootRun

API Documentation
Receive Transactions
POST /transacao

Parameter	Type	Description
valor	BigDecimal	Required. The transaction amount
dataHora	OffsetDateTime	Required. The time the transaction occurred
Responses:

201 Created: Transaction successfully registered

204 No Content: Transaction rejected for being too old (more than 60 seconds)

Clear Transactions
DELETE /transacao

Calculate Statistics
GET /estatistica

Parameter	Type	Description
intervaloSegundos	integer	Not required. The default is 60s
Running the Tests
To run the unit tests:

bash
./gradlew test
Technologies Used
Java 21

Spring Boot 3.4.3

Lombok

Gradle

JUnit 5 for testing
