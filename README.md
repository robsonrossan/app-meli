# API Detalhes de Produto (Marketplace)

## 1. Visão Geral

O projeto **app-meli** é uma API backend desenvolvida com **Java 21** e **Spring Boot 3**.

A aplicação fornece endpoints REST para:

- Cadastro de produtos
- Consulta detalhada
- Atualização
- Remoção
- Listagem geral

A persistência ocorre em um banco de dados em memória (**H2**).

---

## 2. Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **H2 Database**
- **Jakarta Validation**
- **MapStruct**
- **Lombok**
- **JUnit 5 + Mockito**
- **Jacoco (cobertura de testes)**

---

## 3. Estrutura do Projeto

```
com.br.meli.apphackerrank
│
├── controller
│   └── ProdutoController
│
├── service
│   ├── ProdutoService
│   └── impl
│       └── ProdutoServiceImpl
│
├── repository
│   └── ProdutoRepository
│
├── entity
│   └── ProdutoEntity
│
├── dto
│   ├── ProdutoRequestDTO
│   └── ProdutoResponseDTO
│
├── mapper
│   └── ProdutoMapper
│
├── exception
│   ├── RecursoNaoEncontradoException
│   └── GlobalExceptionHandler
│
└── DesafioMeliApplication
```

---

## 4. Fluxo da Requisição

```
Cliente (Postman / Front-end / Terminal)
       ↓
Envia Requisição HTTP (POST | GET | PUT | DELETE)
       ↓
Controller
       ↓
Service
       ↓
Repository
       ↓
Banco H2 (memória)
```

---

## 5. Como Compilar
No diretório raiz **/app-meli** executar os comandos abaixo:
```bash
mvn clean package
```

---

## 6. Executar Testes

```bash
mvn clean test
```

---

## 7. Como Executar a Aplicação

```bash
mvn spring-boot:run
```

---

# 8. Endpoints da API + Exemplos de cURL

> Todos os endpoints podem ser testados no **Postman**  
> 🔗 Download: https://www.postman.com/downloads

---

## 📌 Cadastrar Produto — POST /produtos

```bash
curl --location 'http://localhost:8080/produtos' \
--header 'Content-Type: application/json' \
--data '{
  "titulo": "Notebook Dell Inspiron",
  "descricao": "Notebook Dell Intel i7 16GB RAM SSD 512GB",
  "preco": 4899.90,
  "quantidadeEstoque": 25,
  "vendedor": "TechStore LTDA",
  "urlImagem": "https://site.com/imagens/notebook.png"
}'
```

---

## 📌 Buscar Produto por ID — GET /produtos/{id}

```bash
curl --location 'http://localhost:8080/produtos/1'
```

---

## 📌 Listar Todos os Produtos — GET /produtos

```bash
curl --location 'http://localhost:8080/produtos'
```

---

## 📌 Atualizar Produto — PUT /produtos/{id}

```bash
curl --location --request PUT 'http://localhost:8080/produtos/1' \
--header 'Content-Type: application/json' \
--data '{
    "titulo": "Celular Atualizado",
    "descricao": "Modelo atualizado 2025",
    "preco": 2999.99,
    "quantidadeEstoque": 15,
    "vendedor": "Tech Store",
    "urlImagem": "http://imagem.com/celular-atualizado.png"
  }'
```

---

## 📌 Excluir Produto — DELETE /produtos/{id}

```bash
curl --location --request DELETE 'http://localhost:8080/produtos/1'
```

---

# 9. Exemplos de Responses JSON

## ✅ 200 — Cadastro com Sucesso
```json
{
  "id": 1,
  "titulo": "Notebook Dell Inspiron",
  "descricao": "Notebook Dell Intel i7 16GB RAM SSD 512GB",
  "preco": 4899.90,
  "quantidadeEstoque": 25,
  "vendedor": "TechStore LTDA",
  "urlImagem": "https://site.com/imagens/notebook.png"
}
```

## ❌ 400 — Erro de Validação
```json
{
    "errors": {
        "titulo": "O título é obrigatório"
    },
    "timestamp": "2025-12-09T16:33:13.9982636",
    "status": 400
}
```

## ❌ 404 — Recurso Não Encontrado
```json
{
    "erro": "Produto não encontrado",
    "timestamp": "2025-12-09T16:31:59.1285341",
    "status": 404
}
```

---

# 10. Considerações Finais

Projeto desenvolvido seguindo boas práticas, arquitetura em camadas, DTOs imutáveis, MapStruct e testes unitários.
