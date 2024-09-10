# Desafio da TGID (Transform and Grow in Digital)

O desafio consiste em criar um sistema de transações entre clientes e empresas.

## Tecnologias Usadas
- **Java 21**
- **Maven**
- **H2 Database** (Banco em memória)
- **Spring Data JPA**
- **Hibernate**

## Estrutura Usada
- **Controllers**: Expõem a API.
- **Domain**: Contém entidades.
- **Repository**: Interfaces do repositório.
- **Service**: Lógica de negócio.

## Aplicação / Funcionalidade
- Gestão de saldo na empresa e cliente (Service).
- Transações e Callback na `TransacaoService`.
- Tratamento de erros na lógica de negócio.

## Endpoints

### Clientes
- **POST /api/clientes**: Cadastrar um novo cliente.
- **GET /api/clientes**: Listar todos os clientes.
- **GET /api/clientes/{cpf}**: Buscar um cliente pelo CPF.
- **DELETE /api/clientes/{id}**: Excluir um cliente existente.

### Empresas
- **POST /api/empresas**: Cadastrar uma nova empresa.
- **GET /api/empresas**: Listar todas as empresas.
- **GET /api/empresas/{cnpj}**: Buscar uma empresa pelo CNPJ.
- **DELETE /api/empresas/{id}**: Excluir uma empresa existente.

### Transações
- **POST /api/transacoes/deposito**: Realizar um depósito.
- **POST /api/transacoes/saque**: Realizar um saque.

