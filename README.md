# 🔐 Cofre Digital API

API REST desenvolvida com Quarkus para armazenamento seguro de anotações e informações sensíveis.

O sistema permite que usuários realizem cadastro, autenticação e gerenciamento de conteúdos secretos utilizando mecanismos modernos de segurança como JWT, criptografia AES, hash de senhas com BCrypt e proteção contra excesso de requisições.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Quarkus
- PostgreSQL
- Hibernate ORM + Panache
- JWT Authentication
- BCrypt
- AES Encryption
- Hibernate Validator
- SmallRye Fault Tolerance
- OpenAPI / Swagger

---

## 📌 Funcionalidades

### Autenticação

- Registro de usuários
- Login com autenticação JWT
- Senhas protegidas com BCrypt

### Segurança

- Criptografia AES para informações sensíveis
- UUID como identificador
- Rate Limit para prevenção de spam e brute force
- Tratamento global de exceções
- Endpoints protegidos via JWT

### Secrets

- Criar segredo vinculado ao usuário autenticado
- Buscar segredo por ID
- Associação automática entre segredo e usuário

---

## 🔗 Endpoints

| Método | Endpoint | Descrição |
|----------|----------|------------|
| POST | `/api/register` | Registrar usuário |
| POST | `/api/login` | Autenticar usuário |
| POST | `/api/secrets` | Criar segredo |
| GET | `/api/secrets/{id}` | Buscar segredo por ID |

---

## 📂 Estrutura do Projeto

```txt
src/main/java/org/acme

├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── security
└── service
```

---

## ▶️ Executando o projeto

Rodar aplicação:

```bash
./mvnw quarkus:dev
```

---

## 📖 Swagger

Disponível em:

```txt
http://localhost:8080/q/swagger-ui
```

---

## 🔒 Segurança implementada

| Recurso | Implementação |
|----------|----------------|
| Senhas | BCrypt |
| Autenticação | JWT |
| Dados sensíveis | AES |
| IDs | UUID |
| Rate Limit | SmallRye Fault Tolerance |
| Exceptions | Global Handler |

---

Desenvolvido para o trabalho da disciplina Programação Avançada.
