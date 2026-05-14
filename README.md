# 🔐 Cofre Digital API

API REST desenvolvida com Quarkus para armazenamento seguro de anotações e informações sensíveis.

O sistema permite que usuários realizem cadastro, autenticação e gerenciamento de conteúdos secretos utilizando mecanismos modernos de segurança como JWT, criptografia AES-GCM, hash de senhas com BCrypt e proteção contra excesso de requisições.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Quarkus
- PostgreSQL
- Hibernate ORM + Panache
- JWT Authentication
- BCrypt
- AES-GCM Encryption
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

- Criptografia AES-GCM com IV aleatório para informações sensíveis
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
├── service
└── util
```

---

## ▶️ Executando o projeto

### 🔑 Configuração das Chaves

Antes de executar o projeto, é necessário criar os arquivos de chave utilizados para autenticação JWT e criptografia AES.

### Chaves JWT

Gerar as chaves RSA para autenticação:

```bash
openssl genrsa -out privateKey.pem 2048

openssl rsa -pubout \
-in privateKey.pem \
-out publicKey.pem
```

---

### Chave de Criptografia AES

Criar o arquivo:

```txt
cryptoKey.pem
```

Adicionar uma chave de exatamente **32 caracteres** dentro do arquivo:

```txt
12345678901234567890123456789012
```

Essa chave será utilizada para criptografar informações sensíveis armazenadas no sistema.

---

Rodar aplicação:

```bash
./mvnw quarkus:dev
```

---

## 📮 Postman Collection

Uma collection do Postman foi disponibilizada no projeto para facilitar testes e validações da API.

Arquivo disponível:

```txt
code-swap-api.postman_collection.json
```

Fluxo recomendado:

```txt
Register
↓
Login
↓
Create Secret
↓
Get Secret By ID
```

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
| Dados sensíveis | AES-GCM com IV aleatório |
| IDs | UUID |
| Rate Limit | SmallRye Fault Tolerance |
| Exceptions | Global Exception Handlers |

---

Desenvolvido para o trabalho da disciplina Programação Avançada.
