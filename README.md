# API RESTful para Gestão de Usuários

## 💻 Sobre o Projeto

Este projeto consiste no desenvolvimento de uma API RESTful para a gestão de usuários, construída em Java utilizando Spring Boot. O projeto faz uso do Docker para conteinerização e PostgreSQL como banco de dados. A API segue as melhores práticas de desenvolvimento, com geração automática de documentação Swagger.

---

## 🛠 Tecnologias Utilizadas

- **[Java 17+](https://www.oracle.com/java)**
- **[Spring Boot 3.x](https://spring.io/projects/spring-boot)**
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**
- **[PostgreSQL](https://www.postgresql.org)**
- **[Docker](https://www.docker.com)**
- **[Swagger (OpenAPI)](https://swagger.io)**
- **[Maven](https://maven.apache.org)**

---

## 🚀 Funcionalidades

A API oferece as seguintes funcionalidades:

1. **Adicionar um novo usuário**
    - **Endpoint:** `POST /v1/users`
    - **Descrição:** Adiciona um novo usuário ao sistema.
    - **Corpo da requisição:**
        - `name` (String) - Nome do usuário (obrigatório).
        - `email` (String) - Email do usuário (obrigatório e único).
    - **Resposta:** Retorna o ID do usuário criado e status HTTP 201 (Created).

2. **Listar todos os usuários**
    - **Endpoint:** `GET /v1/users`
    - **Descrição:** Lista todos os usuários cadastrados no sistema.
    - **Resposta:** Retorna uma lista de usuários (`id`, `name`, `email`) e status HTTP 200 (OK).

3. **Atualizar dados de um usuário**
    - **Endpoint:** `PATCH /v1/users/{id}`
    - **Descrição:** Atualiza o nome ou email de um usuário específico.
    - **Corpo da requisição:** Pelo menos um dos seguintes campos:
        - `name` (String) - Nome do usuário (opcional).
        - `email` (String) - Email do usuário (opcional, único).
    - **Parâmetros:**
        - `id` (Long) - ID do usuário a ser atualizado.
    - **Resposta:** Retorna o usuário atualizado e status HTTP 200 (OK).

4. **Deletar um usuário (soft delete)**
    - **Endpoint:** `DELETE /v1/users/{id}`
    - **Descrição:** Deleta um usuário específico do sistema (soft delete).
    - **Parâmetros:**
        - `id` (Long) - ID do usuário a ser deletado.
    - **Resposta:** Retorna status HTTP 204 (No Content).

---

## 📦 Configuração do Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Raul-Domingues/CRUD-JAVA.git
