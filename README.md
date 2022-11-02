# gerenciamento-de-conta-bancaria

Desafio de Backend
Seu objetivo é criar uma API REST com algumas funções essenciais relacionadas ao gerenciamento de contas bancárias em uma das linguagem: Java, Kotlin, Python, Node.js, .NET

Para abrir uma conta é necessário apenas o nome completo e CPF da pessoa, mas só é permitido uma conta por pessoa;
Com essa conta é possível realizar transferências para outras contas e depositar;
Não aceitamos valores negativos nas contas;
Por questão de segurança cada transação de depósito não pode ser maior do que R$2.000;
As transferências entre contas são gratuitas e ilimitadas;

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

A aplicação core possui uma suite de testes unitários para as regras de negócio, assim como a spring-rest para testar a camada WEB. Uma rotina foi configurada no Github Actions para verificar se a aplicação está sendo construidade adequadamente e se os testes estão sendo aceitos.
