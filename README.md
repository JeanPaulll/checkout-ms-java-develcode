# Checkout Service (Java - Spring Boot)

Este projeto foi desenvolvido como parte de um teste técnico para uma vaga de desenvolvedor na DevelCode. Ele implementa um microsserviço de Checkout utilizando Java e Spring Boot, responsável por gerenciar o processo de criação e consulta de pedidos de compra, com operações CRUD completas. Além disso, ele se integra com outro microsserviço de Gateway de Pagamento, desenvolvido em Node.js utilizando NestJS, que processa os pagamentos dos pedidos.

Para mais detalhes sobre o serviço de pagamento, visite o repositório relacionado: Payment Gateway (Node.js - NestJS).
- https://github.com/JeanPaulll/payment-gateway-nestjs-develcode

Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL (usando Docker Compose)
- Lombok
- RestTemplate (para comunicação entre microsserviços)
- Docker

Configuração do Banco de Dados (PostgreSQL via Docker Compose)

Este projeto utiliza PostgreSQL como banco de dados relacional, configurado e executado através do Docker Compose. A configuração do banco de dados já está definida no arquivo docker-compose.yml para facilitar a criação e inicialização do banco.

Passos para Configuração e Execução do Banco de Dados:

### Subir o Banco de Dados com Docker Compose:
Para iniciar o banco de dados PostgreSQL, navegue até o diretório onde o arquivo docker-compose.yml está localizado e execute o seguinte comando:

	docker-compose up -d

- O serviço do banco de dados estará disponível na porta 5432.
- A base de dados será criada com o nome checkout_ms_java, e o usuário e senha serão ambos postgres.

### Configuração de Conexão no application.properties:
No arquivo src/main/resources/application.properties, certifique-se de que a configuração do banco de dados esteja apontando corretamente para o serviço PostgreSQL:

Com essa configuração, o banco de dados estará preparado para receber conexões do microsserviço de Checkout.

## Como Executar Localmente

Requisitos

- JDK 17 ou superior
- Maven 3.x
- Git
- Docker e Docker Compose

## Passos para Configurar e Executar

### 1.	Clone o Repositório:

    git clone https://github.com/JeanPaulll/checkout-ms-java-develcode


### 2.	Navegue para o Diretório do Projeto:

    cd checkout-ms-java-develcode


### 3.	Suba o Banco de Dados PostgreSQL com Docker Compose:

    docker-compose up -d


### 4.	Instale as Dependências:
Certifique-se de que as dependências do projeto estão corretamente instaladas com o Maven:

    mvn clean && mvn dependency:purge-local-repository && mvn install && mvn package

### 5.	Execute o Microsserviço:
Utilize o Maven para iniciar a aplicação:

    mvn spring-boot:run

### 6.	Acesse a aplicação em: http://localhost:8080

## Documentação da API

### Endpoints Principais:

  - Criar Pedido (POST /orders)
  - Descrição: Cria um novo pedido de compra.
  - Exemplo de Requisição:

```
  {
        "name": "Tênis confortável para crianças, disponível em várias cores.",
        "value": 120.00
  }
```

### Resposta:
```
    {
        "id": 1,
        "name": "Tênis confortável para crianças, disponível em várias cores.",
        "value": 120.00,
        "status": "PAGO" | "CRIADO"
    }
```

- Buscar Pedido por ID (GET /orders/{id})
- Descrição: Retorna os detalhes de um pedido específico pelo seu ID.
- Resposta:

```
{
    "id": 2,
    "name": "Conjunto de roupas infantis de alta qualidade.",
    "value": 150.00,
    "status": "FALHA_ROLLBACK"
}
```

- Listar Todos os Pedidos (GET /orders)
- Descrição: Retorna uma lista de todos os pedidos cadastrados.
- Resposta:

```
[
    {
        "id": 1,
        "name": "Conjunto de roupas infantis de alta qualidade.",
        "value": 150.00,
        "status": "FALHA_ROLLBACK"
    },
    {
        "id": 2,
        "name": "Tênis confortável para crianças, disponível em várias cores.",
        "value": 120.00,
        "status": "PAGO"
    }
]
```


Integração com o Payment Gateway

Este microsserviço se comunica com o Payment Gateway, desenvolvido em Node.js utilizando o framework NestJS, para processar pagamentos. Ao criar um pedido, este serviço realiza uma requisição ao serviço de pagamento para confirmar se o pagamento foi bem-sucedido. Caso o pagamento falhe, o pedido é automaticamente marcado como FALHA_ROLLBACK.

Link para o Microsserviço de Pagamento (payment-gateway-nodejs-develcode):

 - https://github.com/JeanPaulll/payment-gateway-nestjs-develcode
 - Payment Gateway (Node.js - NestJS)

### Monitoramento com Spring Boot Actuator

Este microsserviço utiliza o Spring Boot Actuator para monitoramento e gerenciamento. Você pode acessar os endpoints de monitoramento em:

- /actuator/health: Verifica o estado da aplicação.
- /actuator/info: Informações gerais sobre a aplicação.

Contato

- Autor: Jean Paul
- LinkedIn: https://www.linkedin.com/in/jeanpaull/
- Email: jeanpaulwebb@gmail.com

Esse README foi atualizado para refletir as configurações de PostgreSQL via Docker Compose, removendo as referências ao H2 e garantindo uma explicação completa sobre como subir o banco e executar o projeto. Se precisar de mais alguma modificação, é só avisar! :) 