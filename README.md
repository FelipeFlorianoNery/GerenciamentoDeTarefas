**API de Gerenciamento de Tarefas**

API RESTful desenvolvida em Java e Spring Boot para um sistema de gerenciamento de tarefas. A solução implementa um CRUD completo com persistência de dados em um arquivo JSON local, atendendo a todos os requisitos do desafio técnico proposto.

O projeto foi estruturado com foco na separação de responsabilidades (controller, service, repository) e na qualidade do código, incluindo a cobertura de testes unitários para a camada de lógica de negócio.

**Stack Tecnológica**



|**Componente**|**Versão/Tecnologia**|
| - | - |
|**Linguagem**|Java 21|
|**Framework**|Spring Boot 3|
|**Build & Dependências**|Apache Maven|
|**Serialização JSON**|Jackson|
|**Testes**|JUnit 5, Mockito|

**Requisitos para Execução**

- JDK (Java Development Kit) — versão 21 ou superior.
- Git.

*Nota: A instalação do Maven não é necessária, pois o projeto utiliza o Maven Wrapper (mvnw).*

**Setup e Execução Local**

1.  **Clone o repositório:**
    ```sh
    git clone [https://github.com/felipeflorianonery/GerenciamentoDeTarefas.git](https://github.com/felipeflorianonery/GerenciamentoDeTarefas.git)
    cd GerenciamentoDeTarefas
    ```

2.  **Execute a aplicação:**
    O projeto utiliza o Maven Wrapper para garantir a consistência do ambiente de build.

    * **Linux/macOS:**
        ```sh
        ./mvnw spring-boot:run
        ```
    * **Windows (CMD/PowerShell):**
        ```sh
        ./mvnw.cmd spring-boot:run
        ```
A API estará em execução e acessível em http://localhost:8080.


**Documentação dos Endpoints (API)**

Os endpoints podem ser testados com qualquer cliente HTTP (Postman, Insomnia) ou diretamente via curl no terminal.

**Observação:** O {id} de uma tarefa é um UUID gerado no momento da criação. Utilize o ID retornado pela operação POST para testar os endpoints PUT e DELETE.

**POST /tarefas**

Cria uma nova tarefa. Necessário criar o "título", "descrição" e "status".


**Request Body:**
```json
{
  "titulo": "Documentar o projeto",
  "descricao": "Criar o README.md com instruções claras de execução e teste.",
  "status": "Pendente"
}
```

**Exemplo curl:**

```
curl -X POST http://localhost:8080/tarefas \
-H "Content-Type: application/json" \
-d '{
 "titulo": "Documentar o projeto",     
 "descricao": "Criar o README.md com instruções claras de execução e teste."
 "status": "Pendente"
 }
```

**GET /tarefas**

Lista todas as tarefas. Suporta filtragem por status e ordenação.

**Query Parameters (Opcionais):**

- status (string): Filtra tarefas pelo status (Pendente ou Concluído).
- ordenarPor (string): Ordena a lista de resultados (status ou dataDeCriação).

**Exemplos curl:**

- **Listar todas:**
```
  curl -X GET http://localhost:8080/tarefas
```

- **Filtrar por status "Pendente":**

```
  curl -X GET "http://localhost:8080/tarefas?status=Pendente"
```

- **Ordenar por data de criação:**

```
  curl -X GET "http://localhost:8080/tarefas?ordenarPor=dataDeCriação"
```

**PUT /tarefas/{id}**

Atualiza uma tarefa existente.

**Request Body:**
```
{
  "titulo": "Documentar o projeto",
  "descricao": "Criar o README.md com instruções claras de execução e teste.",
  "status": "Concluido"
}
```

**Exemplo curl:**
```
curl -X PUT http://localhost:8080/tarefas/{id-da-tarefa} \
-H "Content-Type: application/json" \
-d '{
  "titulo": "Documentar o projeto",
  "descricao": "Criar o README.md com instruções claras de execução e teste.",
  "status": "Concluido"
}'
```

**DELETE /tarefas/{id}**

Exclui uma tarefa.

**Exemplo curl:**
```
curl -X DELETE http://localhost:8080/tarefas/{id-da-tarefa}
```

- Retorna HTTP 204 No Content em caso de sucesso.
- Retorna HTTP 404 Not Found se a tarefa não for encontrada.


**Testes**

Os testes unitários para a camada de serviço (TarefaService) foram implementados com JUnit 5 e Mockito para validar a lógica de negócio de forma isolada, sem depender da camada de persistência.

Para executar os testes, utilize o seguinte comando:

- Linux/macOS
  ```./mvnw test```
- Windows
  ``` ./mvnw.cmd test```

