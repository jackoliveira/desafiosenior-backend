# Desafio Back-End - Senior

Tarefa: Construir uma aplicação web API para realizar o cadastro de pessoas, endereço e seus telefones.

A API deve disponibilizar as seguintes funcionalidades:
- Salvar uma pessoa e todos os seus dados pessoais de forma atômica;
- Atualizar uma pessoa e seus dados pessoas de forma atômica;
- Listar todos as pessoas cadastradas;
- Listar todas as pessoas cadastradas utilizando o nome como parâmetro;
- Excluir um cadastro de pessoa;

## Funcionalidades

  - Cadastro de pessoas.
  - Listagem de pessoas com paginação.
  - Edição de pessoas.
  - Exclusão de pessoas.

Tecnologias utilizadas
  - Java;
  - Hibernate;
  - PostgreSQL;
  - Spring Boot;
  - Spring Data;
  - Lombok;

### Installation

Requisitos
 - Ambiente de Desenvolvimento Java

Porta API [localhost:8080](http://localhost:8080)

Rotas
- GET `/people`
- GET `/people/{id}`
- POST `/people`
- PUT `/people/{id}`
- DELETE `/people/{id}`