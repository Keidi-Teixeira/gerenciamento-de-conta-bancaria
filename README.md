# Gerenciamento de conta báncaria

Desafio de backend de um processo seletivo o objetivo era criar uma API REST com algumas funções essenciais relacionadas ao gerenciamento de conta bancária.

<h2><b>Este é um projeto de Java com Spring Boot, ApiRest e foi utilizado o banco de dados "in memory" H2.</b></h2>

Este projeto foi desenvolvido em Java 17 usando ferramentas Spring stack para criar servidor http e lidar com operações de persistência.

Para testes, são usados Junit 5 (Júpiter), Mockito e spring-boot-starter-test, este último é necessário para escrever testes de camada WEB.

Devido à simplicidade do projeto, foi utilizado um banco de dados H2 “in-memory”. O console H2 deve estar disponível no endereço local http://localhost:8091/, 
basta digitar as seguintes informações na tela do console para acessar o banco:

JDBC URL: jdbc:h2:mem:donus
Password: password

<h2><b>ApiRest</b></h2>

O arquivo desafio-donus.postman_collection localizado no diretório raiz do projeto pode ser importado para o Postman para executar operações da API REST.

/client
Descrição: Cria um novo cliente
Method: POST
Body: { "from": <string>, "to": <string>, "value": <string> }
/client/{id}
Descrição: Buscar um cliente pelo ID
Method: GET
Path variable: id - string
/client/deposit
Descrição: Deposita um valor na conta de um cliente registrado.
Method: POST
Body: { "clientId": <string>, "value": <integer> }
/client/transfer
Descrição: Transfere um valor do cliente from para o to
Method: POST
Body: { "from": <string>, "to": <string>, "value": <integer> }

<h2><b>Organização do projeto</b></h2>

core: Todas as regras de negócio da aplicação estão centralizadas nesta aplicação;
persistence: Implementação das operações de persistência definidas no pacote ports da aplicação core;
spring-rest: Implementação da API REST para manipulação das operações referents ao cliente. Também pode ser considerado como uma entrada para a execução das regras de negócio da aplicação.

A aplicação core possui uma suite de testes unitários para as regras de negócio, assim como a spring-rest para testar a camada WEB. 

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Bank account management

Backend challenge of a selection process the objective was to create a REST API with some essential functions related to bank account management.

<h2><b>This is a Java project with Spring Boot, ApiRest and the H2 "in memory" database was used.</b></h2>

This project was developed in Java 17 using Spring stack tools to create http server and handle persistence operations.

For testing, Junit 5 (Jupiter), Mockito and spring-boot-starter-test are used, the latter is needed to write WEB layer tests.

Due to the simplicity of the project, an “in-memory” H2 database was used. The H2 console must be available at the local address http://localhost:8091/,
just enter the following information on the console screen to access the bank:

JDBC URL: jdbc:h2:mem:donus
password: password

<h2><b>ApiRest</b></h2>

The challenge-donus.postman_collection file located in the project root directory can be imported into Postman to perform REST API operations.

/client
Description: Creates a new customer
Method: POST
Body: { "from": <string>, "to": <string>, "value": <string> }
/client/{id}
Description: Search for a customer by ID
Method: GET
Path variable: id - string
/client/deposit
Description: Deposits an amount into the account of a registered customer.
Method: POST
Body: { "clientId": <string>, "value": <integer> }
/client/transfer
Description: Transfers a value from the client from to to
Method: POST
Body: { "from": <string>, "to": <string>, "value": <integer> }

<h2><b>Project organization</b></h2>

core: All application business rules are centralized in this application;
persistence: Implementation of the persistence operations defined in the ports package of the core application;
spring-rest: Implementation of the REST API for handling operations referring to the client. It can also be considered as an input to the execution of the application's business rules.

The core application has a suite of unit tests for business rules, as well as spring-rest for testing the WEB layer.
