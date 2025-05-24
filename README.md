# 💎 Avaliação de Admissão | Neurotech

Esta aplicação foi desenvolvida como parte do processo seletivo para a Neurotech.
O repositório oficial com todos os requisitos, especificações e instruções pode ser acessado em:
[Neurotech Challenge - GitHub](https://github.com/Neurolake/challenge-java-developer)

---

## 🚀 Tecnologias utilizadas

- 🐳 **Docker e Docker Compose**
- 🛢️ **PostgreSQL 17**
- ☕ **Java 21**
- 🌱 **Spring Boot 3.4.5**
- 📄 **Swagger (OpenAPI)**

---

## 🛠️ Como rodar o projeto localmente

1. **Clone o repositório**

```bash
git clone https://github.com/Neurolake/challenge-java-developer.git
```

2. **Entre na pasta do projeto**

```bash
cd challenge-java-developer
```

3. **Suba os containers com Docker Compose**

Certifique-se de ter o Docker e Docker Compose instalados em sua máquina.
Caso não tenha, acesse: [Docker Install](https://docs.docker.com/engine/install/), [Compose Install](https://docs.docker.com/compose/install/).

```bash
docker compose up
```

---

## 🌐 Acessando a API

- [API](http://localhost:8080)
- [Health Check](http://localhost:8080/api/health)
- [Swagger](http://localhost:8080/swagger-ui/index.html)

---

## 📂 Estrutura do Projeto

- `src/main/java` — Código-fonte da aplicação
- `src/test/java` — Testes automatizados
- `docker-compose.yml` — Configuração dos containers
- `Dockerfile` — Build da aplicação

