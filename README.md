# Projeto-API-Futebol

### Apresentação
Este projeto consiste na criação de uma API para gerenciar informações relacionadas a clubes de futebol, partidas e estádios. A API permite operações como cadastro, edição, inativação, busca e listagem de clubes, com validações específicas para garantir a integridade dos dados.

### Cadastro de clube
- **Método:** POST
- **Retorno esperado:** 201 CREATED
- **Validações:**
  - Dados mínimos obrigatórios: nome, sigla do estado, data de criação e status de atividade.
  - Impedir cadastros com dados inválidos (ex.: nome menor que duas letras, estado inexistente no Brasil, data de criação no futuro).
  - Evitar conflito de dados (clube com nome igual já cadastrado no mesmo estado).

### Editar um clube
- **Método:** PUT
- **Retorno esperado:** 200 OK
- **Validações:**
  - Dados inválidos (ex.: nome menor que duas letras, estado inexistente no Brasil, data de criação no futuro).
  - Data inválida (edição da data de criação para data posterior a alguma partida registrada).
  - Conflito de dados (tentativa de atualização para um nome igual já cadastrado no mesmo estado).
  - Clube não existente na base de dados.

### Inativar um clube
- **Método:** DELETE
- **Retorno esperado:** 204 NO CONTENT
- **Validação:** Validar existência do clube na base de dados.

### Buscar um clube pelo ID
- **Método:** GET
- **Retorno esperado:** 200 OK ou 404 NOT FOUND (caso não encontrado)

### Listar clubes
- **Método:** GET
- **Retorno esperado:** 200 OK (lista paginada) ou lista vazia
- **Funcionalidades:** Suporta filtros por nome, estado e situação (ativa ou inativa), além de paginação e ordenação ascendente ou descendente.

### Tecnologias utilizadas
- Java 17: Linguagem principal de desenvolvimento.
- Spring Boot: Facilita a configuração e criação de APIs RESTful.
- Spring Data: Simplifica o acesso e manipulação de dados com o banco de dados MySQL.
- MySQL: Banco de dados relacional utilizado para armazenar informações dos clubes, partidas e estádios.
