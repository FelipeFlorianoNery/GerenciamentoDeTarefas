**API de Gerenciamento de Tarefas**

API REST desenvolvida em Java e Spring Boot para um sistema de gerenciamento de tarefas. A solução implementa um CRUD completo com persistência de dados em um banco de dados **PostgreSQL**, utilizando **Spring Data JPA** e Hibernate.

O projeto foi estruturado com foco na separação de responsabilidades (controller, service, repository) e na qualidade do código, incluindo a cobertura de testes unitários para a camada de lógica de negócio.

**Stack Tecnológica**

|**Componente**|**Versão/Tecnologia**|
| - | - |
|**Linguagem**|Java 21|
|**Framework**|Spring Boot 3|
|**Persistência**|Spring Data JPA / Hibernate|
|**Banco de Dados**|PostgreSQL|
|**Build & Dependências**|Apache Maven|
|**Testes**|JUnit 5, Mockito|

**Requisitos para Execução**

* JDK (Java Development Kit) — versão 21 ou superior.
* Git.
* **PostgreSQL** — instalado e rodando localmente (ex: versão 14 ou superior).

*Nota: A instalação do Maven não é necessária, pois o projeto utiliza o Maven Wrapper (mvnw).*

**Setup e Execução Local**

1.  **Clone o repositório:**
    ```sh
    git clone
    https://github.com/felipeflorianonery/GerenciamentoDeTarefas.git
    cd GerenciamentoDeTarefas
    ```

2.  **Configuração do Banco de Dados (PostgreSQL):**
    Antes de iniciar a aplicação, você precisa ter um banco de dados criado.

    a. Conecte-se ao seu PostgreSQL (via `psql`, `pgAdmin`, DBeaver, etc.).
    b. Crie o banco de dados:
    ```sql
    CREATE DATABASE gerenciamento_tarefas;
    ```
    c. **Configure suas credenciais:**
    Abra o arquivo `src/main/resources/application.properties`.
    Altere as seguintes linhas com **seu usuário e senha** do PostgreSQL:
    ```properties
    # Verifique se a URL e a porta estão corretas
    spring.datasource.url=jdbc:postgresql://localhost:5432//gerenciamento_tarefas
    # Altere com suas credenciais locais
    spring.datasource.username=postgres
    spring.datasource.password=sua_senha_aqui
