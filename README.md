Desafio da TGID (Transform and Grow in Digital)

Desafio consiste em criar um sistema de transações, entre cliente e empresa.

Tecnologias usadas
----------------------------------
Java 21
Maven 
H2 Database (Banco em memória)
Spring Data JPA
Hibernate
----------------------------------
Estrutura Usada
Controllers - Expoem a API
Domain - Contem Entidades
Repository - Interfaces do repositorio
Service - Lógica de negócio
----------------------------------
Aplicação / Funcionalidade
Gestão de Saldo na Empresa e Cliente (Service)
Transações na TransaçãoService
Tratamento de erros na lógica de negócio
------------------------------------
Clientes

Clientes
POST /api/clientes: Cadastrar um novo cliente.
GET /api/clientes: Listar todos os clientes.
GET /api/clientes/{cpf}: Buscar um cliente pelo CPF.
DELETE /api/clientes/{id}: Excluir um cliente existente.

Empresas

POST /api/empresas: Cadastrar uma nova empresa.
GET /api/empresas: Listar todas as empresas.
GET /api/empresas/{cnpj}: Buscar uma empresa pelo CNPJ.
DELETE /api/empresas/{id}: Excluir uma empresa existente.

Transações
POST /api/transacoes/deposito: Realizar um depósito.

POST /api/transacoes/saque: Realizar um saque.
Descrição: Este endpoint permite registrar um saque da conta de um cliente.

