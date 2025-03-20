Transação API Português || Transaction API  English


Transação API
Este projeto é uma API REST para gerenciar transações financeiras e calcular estatísticas das transações realizadas nos últimos 60 segundos.
A API foi desenvolvida com Java 21 e Spring Boot 3.4.3.

1. Clone o Repositório
bash
git clone https://github.com/MouraMath/transacao-api.git
cd transacao-api

2. Compile o Projeto
bash
./gradlew build

3. Execute o Projeto
bash
./gradlew bootRun
Documentação da API
Receber Transações
POST /transacao

Parâmetro	Tipo	Descrição
valor	BigDecimal	Obrigatório. O valor da transação
dataHora	OffsetDateTime	Obrigatório. O horário que a transação ocorreu
Respostas:

201 Created: Transação registrada com sucesso.

204 No Content: Transação rejeitada por ser muito antiga (mais de 60 segundos).

Limpar Transações
DELETE /transacao

Respostas:

200 OK: Todas as transações foram removidas com sucesso.

Calcular Estatísticas
GET /estatistica

Parâmetro	Tipo	Descrição
intervaloSegundos	integer	Não Obrigatório. O padrão default é 60s.
Resposta:

json
{
  "soma": 600.00,
  "min": 100.00,
  "max": 300.00,
  "media": 200.00,
  "count": 3
}
Executando os Testes
Para executar os testes unitários do projeto:

bash
./gradlew test
------------------------

Transaction API
This project is a REST API for managing financial transactions and calculating statistics of transactions performed in the last 60 seconds.
The API was developed with Java 21 and Spring Boot 3.4.3.

1. Clone the Repository
bash
git clone https://github.com/MouraMath/transaction-api.git
cd transaction-api

2. Compile the Project
bash
./gradlew build

3. Run the Project
bash
./gradlew bootRun
API Documentation
Receive Transactions
POST /transacao

Parameter	Type	Description
valor	BigDecimal	Required. The transaction amount
dataHora	OffsetDateTime	Required. The timestamp of the transaction
Responses:

201 Created: Transaction successfully registered.

204 No Content: Transaction rejected for being too old (more than 60 seconds).

Clear Transactions
DELETE /transacao

Responses:

200 OK: All transactions were successfully removed.

Calculate Statistics
GET /estatistica

Parameter	Type	Description
intervaloSegundos	integer	Not required. The default value is 60s.
Response:

json
{
  "soma": 600.00,
  "min": 100.00,
  "max": 300.00,
  "media": 200.00,
  "count": 3
}
Running Tests
To execute the project's unit tests:

bash
./gradlew test
