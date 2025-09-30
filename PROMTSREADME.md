# **Registro da Mentoria: Construção da API de Gerenciamento de Tarefas** 
Este documento registra a conversa e o processo de desenvolvimento de uma API REST para gerenciamento de tarefas, construída como parte de um teste técnico. A mentoria guiou o desenvolvimento passo a passo, desde a arquitetura inicial até a implementação final das funcionalidades. 
### **Início: Análise dos Requisitos** 
**Usuário:** ola, fiz a entrevista na atak, e pediram para fazer um teste. Eu nao quero que voce resolva, irei fazer sozinho, apenas gostaria que me orietnasse se o processo esta correto. 

irei criar usar java como linguagem. Criar a classe tarefa e criar os 4 metodos (criar, visualizar, atualizar e excluir), precisarei fazer uma ligação com o CRUD (novo, edição, leitura e exclusao), tudo isso gerando e gravando em json. E criando os 3 atributos Titulo, descrição e status. Porem para a ordenação por data de crição precisarei criar essa atributo tambem, correto? 

Minhas duvidas sao com respeito ao seguinte, desde o inicio ja crio no git e subo no github? ele nao fala do banco de dados, mas precisara devolver em json, correto? uso o mongoDb? e para o deply faço no jenkis? alem do que a documentação sera uma que detalha as configurações, coom exacutar e como aplicar? 

No final como faço para a pessoa conseguir abrir ? **[Imagem enviada: image\_314fff.png]** 

**Mentor Tech Sênior:** Olá! Excelente iniciativa de buscar orientação antes de mergulhar no código. Essa atitude de planejar e validar o processo é uma das características mais importantes de um bom profissional. Vamos analisar seu plano e tirar suas dúvidas. 

Seu raciocínio inicial está corretíssimo e muito bem estruturado. Vejo que você entendeu perfeitamente o núcleo do desafio. 

... (orientações sobre Git, Banco de Dados vs. Arquivo JSON, Deploy e Documentação) ... 
### **Configuração do Projeto e Dependências** 
**Usuário:** qual dependencias uso no spring.io para o projeto pensando em gravar os dados em arquivo Json? 

**Mentor Tech Sênior:** Ótima pergunta! A escolha das dependências no start.spring.io é o que 

define a "caixa de ferramentas" que você terá. 

**Dependências Essenciais:** 

1. **Spring Web:** Inclui o servidor web e a biblioteca **Jackson** para manipulação de JSON. 

Dependências de Qualidade de Vida (Altamente Recomendadas): 

2. Spring Boot DevTools: Para reinício automático da aplicação. 
2. Lombok: Para reduzir código repetitivo (getters, setters, etc.). 
### **Construção da Camada de Repository** 
**Usuário:** a classe responsavel por ler e escrever a lista de tarefas no arquivo "tarefas.jason", crio no mesmo pacote principal onde esta a classe Tarefa e a classe GErenciamnetoTarefasApplication? Como eu faço a relação e os métodos para cumprir com a exigencia do CRUD? 

**Mentor Tech Sênior:** Essa é uma excelente pergunta sobre arquitetura de software! (...) a classe Tarefa sera no model, e a nova classe no repository? 

**Usuário:** a classe Tarefa sera no model, e a nova classe no repository? 

**Mentor Tech Sênior:** Exatamente! Você entendeu a lógica perfeitamente. 

... (diálogo sobre a implementação do método de leitura encontrarTodas()) ... 

**Usuário:** dessa maneira? 

**[Imagem enviada: image\_3f72fe.png]** 

... (diálogo sobre a lógica de leitura do arquivo JSON com ObjectMapper e TypeReference) ... 
### **Construção da Camada de Service** 
... (diálogo sobre a criação da classe TarefaService e a lógica do método criar) ... **Usuário:** seria dessa maneira? 

**[Imagem enviada: image\_4891c7.png]** 

**Mentor Tech Sênior:** Sua estrutura está **impecável**! (...) A estrutura está 10/10. Agora, só falta o recheio. 

... (diálogo completando a lógica do método criar no Service) ... 
### **Construção da Camada de Controller** 
... (diálogo sobre a criação da classe TarefaController com @RestController e injeção de 

dependência) ... 

**Usuário:** faremos da mesma maneira que fizemos para declarar o Repository no Service. Iremos colocar como private final o TarefaService, e depois um poublic com o this. e a anotação par ao GET é o GetMapping("/tarefa") 

**[Imagem enviada: image\_49d7c6.png]** 

... (diálogo sobre a implementação dos métodos listarTodas() e criar() no Controller) ... 
### **Testes e Depuração com Insomnia/Postman** 
**Usuário:** (Envia imagem de erro 405 Method Not Allowed) **[Imagem enviada: image\_4b348a.png]** 

**Mentor Tech Sênior:** Ótimas capturas de tela! (...) O erro acontece por conta da combinação das anotações @RequestMapping e @GetMapping, resultando na URL /tarefas/tarefas. 

... (diálogo sobre a correção e o próximo erro 400 Bad Request) ... 

**Usuário:** (Envia imagem de erro 400 Bad Request - Required request body is missing) **[Imagem enviada: image\_4b4081.png]** 

... (diálogo sobre a correção, adicionando o Body no Insomnia, e o próximo erro de JSON parse error) ... 

**Usuário:** (Envia imagem do erro de parse do JSON para o tipo boolean) **[Imagem enviada: image\_4b474e.png]** 

**Mentor Tech Sênior:** Excelente! Este é um erro diferente (...) Cannot deserialize value of type 'boolean' from String "PENDENTE". Isso nos leva diretamente à causa do problema: o tipo da variável na classe Tarefa. 

... (diálogo sobre a refatoração da classe Tarefa para usar private e getters/setters, e a implementação da lógica de "tradução" do status) ... 
### **Implementação de UPDATE e DELETE** 
... (diálogo sobre a necessidade de um ID único e a implementação passo a passo da lógica do UPDATE nas camadas de Service e Controller) ... 

**Usuário:** (Envia imagem do método atualizar no Controller com erro de sintaxe) 

**[Imagem enviada: image\_586b06.png]** 

**Mentor Tech Sênior:** A sintaxe ficou um pouco misturada novamente, mas não se preocupe (...) Lembre-se da regra: cada anotação (@PathVariable, @RequestBody) se aplica a **um e somente um** parâmetro que vem logo depois dela. 

... (correção do UPDATE e teste bem-sucedido) ... 

**Usuário:** (Envia imagem do teste do PUT com sucesso, mas com corpo vazio) **[Imagem enviada: image\_587a2b.png]** 

**Mentor Tech Sênior:** Excelente! O status **200 OK** significa que sua aplicação recebeu a requisição (...) Podemos melhorar a resposta da nossa API para que ela seja mais clara para o usuário (...) retornando um ResponseEntity. 

... (diálogo sobre a implementação da lógica do DELETE) ... 
### **Implementação de Filtros e Ordenação** 
... (diálogo sobre a implementação da lógica de filtro por status e ordenação por data de criação e status, utilizando Java Streams) ... 

**Usuário:** (Envia imagem do código de filtro e ordenação em andamento) **[Imagem enviada: image\_6525d0.png]** 

**Mentor Tech Sênior:** Excelente tentativa! (...) O problema do else if (...) O problema do .filter() vs .sorted() (...) Precisamos entregá-lo para a estação .sorted(). 
### ... (código final corrigido para filtro e ordenação) ... **Testes Unitários** 
**Usuário:** podemos fazer os testes unitários? 

**Mentor Tech Sênior:** Com certeza! Excelente escolha. (...) O alvo principal dos nossos testes unitários será a classe **TarefaService**. 

... (diálogo sobre a configuração do ambiente de teste com JUnit e Mockito) ... **Usuário:** (Envia imagem de erro UnsupportedOperationException durante o teste) **[Imagem enviada: image\_660762.png]** 

**Mentor Tech Sênior:** Este erro é um clássico (...) A causa é que a lista retornada pelo mock é **imutável**. (...) A correção é muito simples. Precisamos garantir que o nosso when no teste 

retorne uma lista que permita modificações, usando new ArrayList<>(). **Usuário:** (Envia imagem do teste passando com sucesso) 

**[Imagem enviada: image\_661594.png]** 
### **Finalização** 
**Usuário:** Eu testei no Insomnia e não estava dando certo, descobri o erro, era devido ao jackson não conseguir tratar a questão do localtime, por isso criei uma classe para fazer o tratamento adequado. Agora está funcionando corretamente. Vamos para o próximo passo do solicitado com o arquivo README. 

**Mentor Tech Sênior:** Excelente! Você demonstrou uma capacidade incrível de depuração (...) A classe JacksonConfig que você criou é a solução padrão da indústria para esse tipo de situação. 

... (fornecimento do template final para o README.md) ... **FIM DO PROCESSO DE DESENVOLVIMENTO.** 
