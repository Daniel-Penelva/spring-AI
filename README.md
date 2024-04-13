# Spring AI

Spring AI é uma estrutura de aplicação para engenharia de IA. Seu objetivo é aplicar ao domínio de IA os princípios de design do ecossistema Spring, como portabilidade e design modular, e promover o uso de POJOs como blocos de construção de uma aplicação para o domínio de IA.

## Características
Características
Suporte de API portátil em provedores de IA para modelos de chat, texto para imagem e incorporação. As opções de API síncrona e de fluxo são suportadas. Também há suporte para descer para acessar recursos específicos do modelo.

OBS. Documentação do [Spring AI](https://spring.io/projects/spring-ai)

# Classe OpenAiChatClient

A classe OpenAiChatClient é uma classe que permite a integração perfeita de aplicativos Java com a API da OpenAI, possibilitando o uso dos recursos da API da OpenAI em seus projetos Java. Este cliente não é oficialmente endossado pela OpenAI, mas é mantido pela comunidade e suporta o Java 17 ou versões posteriores.

Exemplo de código demonstrando como usar o OpenAiChatClient para Completar Conversas:

```java
var request = new ChatCompletionRequestBuilder()
    .model(OpenAIModel.GPT_4_1106_PREVIEW)
    .systemMessage("Você é um gerador de nomes de produtos sustentáveis")
    .userMessage("Por favor, gere dois nomes de produtos")
    .build();

var client = new OpenAIClient("SUA_CHAVE_API");
var response = client.chatCompletion(request);

System.out.println(response);
```

Neste exemplo, o `ChatCompletionRequestBuilder` é usado para configurar a requisição, incluindo a definição do modelo, mensagem do sistema e mensagem do usuário. A classe `OpenAIClient` é então utilizada para enviar a requisição e receber a resposta.

O OpenAiChatClient fornece métodos para configurar diversos parâmetros da API, como o modelo, mensagem do sistema e mensagem do usuário. Esses métodos podem ser usados para personalizar suas requisições e alcançar a funcionalidade desejada.

Para criar uma chave de API, você precisará seguir as instruções fornecidas pela OpenAI ou pela biblioteca cliente da OpenAI que estiver utilizando. É importante manter a chave de API segura e não compartilhá-la publicamente.

# Classe ChatResponse

A classe `ChatResponse` faz parte da biblioteca Java da OpenAI e representa a resposta recebida da API da OpenAI ao fazer uma solicitação de conclusão de chat. Ela contém o texto gerado e quaisquer metadados adicionais retornados pela API.

Exemplo de como usar a classe `ChatResponse`:

```java
var request = new ChatCompletionRequestBuilder()
    .model(OpenAIModel.GPT_4_1106_PREVIEW)
    .systemMessage("Você é um gerador de nomes de produtos sustentáveis")
    .userMessage("Por favor, gere dois nomes de produtos")
    .build();

var client = new OpenAIClient("SUA_CHAVE_API");
var response = client.chatCompletion(request);

if (response.isSuccessful()) {
    var chatResponse = response.getChatResponse();
    System.out.println(chatResponse.getText());
} else {
    System.out.println("Erro: " + response.getError());
}
```

Neste exemplo, o `ChatCompletionRequestBuilder` é usado para configurar a requisição, e a classe `OpenAIClient` é utilizada para enviar a requisição e receber a resposta. Se a resposta for bem-sucedida, o método `getChatResponse()` é chamado para obter o objeto `ChatResponse`, e o texto gerado pode ser acessado usando o método `getText()`.

A classe `ChatResponse` fornece diversos métodos para acessar o texto gerado e os metadados, como o número de tokens utilizados, o tempo de resposta da API e quaisquer erros que possam ter ocorrido.

Aqui está uma lista de alguns dos métodos fornecidos pela classe `ChatResponse`:

- `getText()`: Retorna o texto gerado.
- `getNbTokens()`: Retorna o número de tokens usados no texto gerado.
- `getDuration()`: Retorna o tempo de resposta da API em milissegundos.
- `getError()`: Retorna qualquer mensagem de erro que possa ter ocorrido.

A classe `ChatResponse` é uma ferramenta útil para trabalhar com a API da OpenAI e pode ajudar a simplificar o processo de lidar e interpretar as respostas recebidas da API.

# Classe Prompt

A classe `Prompt` no contexto da biblioteca Java da OpenAI é usada como um contêiner para uma série organizada de objetos `Message`, com cada um deles formando um segmento da solicitação geral. Cada `Message` representa um papel único dentro da solicitação, diferindo em seu conteúdo e intenção. Esses papéis podem incluir inquéritos de usuários, respostas geradas pelo AI ou informações de fundo relevantes.

A classe `Prompt` é usada para construir interações complexas e detalhadas com modelos de IA, pois a solicitação é construída a partir de mensagens múltiplas, cada uma com um papel específico a desempenhar no diálogo.

Ao criar uma instância de `Prompt`, você pode usar o `SystemPromptTemplate` para criar uma `Message` que representa o papel do sistema na conversa. Por exemplo, você pode criar uma `Message` que apresenta o sistema como um gerador de nomes de produtos sustentáveis e define o contexto para a conversa.

Exemplo de como criar uma instância de `Prompt`:

```java
String systemText = "Você é um gerador de nomes de produtos sustentáveis.";
SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", name, "voice", voice));
Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
```

Neste exemplo, o `SystemPromptTemplate` é usado para criar uma `Message` que representa o papel do sistema na conversa. O método `createMessage` é chamado com um `Map` de parâmetros, como o nome e a voz do sistema.

A classe `Prompt` é então usada para criar uma nova instância de `Prompt` com as `userMessage` e `systemMessage` objetos.

Após a criação de uma instância de `Prompt`, você pode usá-la para fazer uma solicitação de conclusão de chat à API da OpenAI usando a classe `ChatClient`. A classe `ChatClient` fornece um método `generate` que recebe uma instância de `Prompt` e retorna uma instância de `ChatResponse`.

Exemplo de como fazer uma solicitação de conclusão de chat usando uma instância de `Prompt`:

```java
List<Generation> response = chatClient.generate(prompt);
```

Neste exemplo, o método `generate` é chamado com o objeto `prompt` para fazer uma solicitação de conclusão de chat à API da OpenAI. O método `generate` retorna uma lista de objetos `Generation`, que representam o texto gerado e quaisquer metadados adicionais retornados pela API.

Ao usar a classe `Prompt`, você pode criar interações complexas e detalhadas com modelos de IA e guiá-los para gerar saídas específicas com base no design e na formulagem das solicitações.


# Classe PromptTemplate

A classe `PromptTemplate` na biblioteca Java da OpenAI é uma classe utilitária que simplifica a criação de instâncias de `Prompt`. Ela fornece uma maneira conveniente de definir um modelo de prompt e gerar instâncias de `Prompt` com diferentes parâmetros.

A classe `PromptTemplate` permite que você defina um modelo de prompt como uma string, que pode incluir espaços reservados para parâmetros. Em seguida, você pode usar o método `createPrompt` para gerar uma instância de `Prompt` com os parâmetros especificados.

Exemplo de como usar a classe `PromptTemplate`:

```java
String promptTemplate = "Você é um gerador de nomes de produtos sustentáveis. Gerar um nome para um produto chamado {productName}.";
PromptTemplate promptTemplateInstance = new PromptTemplate(promptTemplate);
Map<String, Object> params = new HashMap<>();
params.put("productName", "Eco-Friendly Water Bottle");
Prompt prompt = promptTemplateInstance.createPrompt(params);
```

Neste exemplo, a classe `PromptTemplate` é usada para definir um modelo de prompt como uma string, com um espaço reservado para o parâmetro `productName`. O método `createPrompt` é então chamado com um mapa de parâmetros para gerar uma instância de `Prompt`.

A classe `PromptTemplate` é uma ferramenta útil para criar prompts complexos e detalhados com facilidade. Ela permite que você defina um modelo de prompt uma vez e então gere várias instâncias de `Prompt` com diferentes parâmetros, tornando mais fácil gerenciar e manter seus prompts.

A classe `PromptTemplate` também fornece um método `fill` que pode ser usado para preencher os espaços reservados no modelo de prompt com os parâmetros especificados. Este método retorna uma string com os espaços reservados substituídos pelos valores dos parâmetros correspondentes.

Exemplo de como usar o método `fill`:

```java
String filledPrompt = promptTemplateInstance.fill(params);
```

Neste exemplo, o método `fill` é chamado com o mapa `params` para gerar uma string com os espaços reservados substituídos pelos valores dos parâmetros correspondentes.

A classe `PromptTemplate` é uma ferramenta poderosa para criar e gerenciar prompts em suas aplicações Java da OpenAI. Ela simplifica o processo de criação e uso de prompts, tornando-o mais fácil interagir com a API da OpenAI e gerar as saídas desejadas.

# Classe Flux

A classe `Flux` do pacote `reactor.core.publisher` é um Publicador Reactive Streams que emite uma sequência de 0 a N elementos, seguido de um sinal de conclusão. Ela é um componente central da biblioteca Reactor, que fornece um modelo de programação reativo para aplicativos não bloqueantes e dirigidos por eventos em Java.

A classe `Flux` é projetada para lidar com o backpressure, que é a capacidade de controlar a taxa de produção e consumo de dados. Isso é importante na programação reativa, onde os dados são processados assincronamente e de maneira não bloqueante.

A classe `Flux` fornece vários métodos para criar e manipular sequências de dados, como:

* `just(T... values)`: Cria um `Flux` que emite os valores especificados e depois completa.
* `fromArray(T[] values)`: Cria um `Flux` que emite os elementos do array especificado e depois completa.
* `range(long start, long end)`: Cria um `Flux` que emite uma faixa de inteiros dos valores de início e fim especificados.
* `interval(Duration interval)`: Cria um `Flux` que emite uma sequência de inteiros com o intervalo especificado entre cada emissão.
* `generate(FluxSink.OverFlowStrategy strategy, Consumer<? super FluxSink<T>> emitter)`: Cria um `Flux` que emite elementos gerados pela função de emissor especificada.

A classe `Flux` também fornece vários operadores para transformar e combinar sequências de dados, como:

* `map(Function<? super T,? extends R> mapper)`: Transforma cada elemento do `Flux` aplicando a função mapper especificada.
* `filter(Predicate<? super T> predicate)`: Filtra os elementos do `Flux` aplicando a função predicate especificada.
* `flatMap(Function<? super T,? extends Publisher<? extends R>> mapper)`: Transforma cada elemento do `Flux` em uma sequência de elementos emitidos pelo Publicador especificado.
* `reduce(BinaryOperator<T> accumulator)`: Reduz os elementos do `Flux` a um único valor aplicando a função accumulator especificada.
* `buffer(int bufferSize)`: Bufferiza os elementos do `Flux` em chunks do tamanho especificado.

A classe `Flux` é uma ferramenta poderosa para construir aplicativos reativos em Java, pois permite processamento eficiente e escalável de fluxos de dados. Ela foi projetada para funcionar seamlessmente com outros componentes da biblioteca Reactor, como a classe `Mono`, que representa uma sequência de 0 ou 1 elementos.

Em resumo, a classe `Flux` do pacote `reactor.core.publisher` é um Publicador Reactive Streams que emite uma sequência de 0 a N elementos, seguido de um sinal de conclusão. Ela fornece vários métodos para criar e manipular sequências de dados, assim como operadores para transformar e combinar sequências de dados. Ela é um componente central da biblioteca Reactor, que fornece um modelo de programação reativo para aplicativos não bloqueantes e dirigidos por eventos em Java.

## Como usar a classe flux em um chatbot

Para usar a classe `Flux` em um chatbot, você pode seguir os seguintes passos com base nas informações fornecidas nos resultados da pesquisa:

1. **Definição do Fluxo de Atendimento:**
    - O fluxo de atendimento em um chatbot é essencial para estruturar as interações com os usuários. Você pode definir os diferentes passos que o chatbot seguirá ao interagir com os usuários, como solicitar informações específicas, fornecer respostas personalizadas, etc.

2. **Criação de Fluxos Inteligentes:**
    - Utilize ferramentas como o SendFlux, que oferece um construtor visual de fluxos inteligentes para chatbots, permitindo criar fluxos de conversação de forma visual e interativa, sem a necessidade de escrever código.

3. **Integração com Plataformas e Canais de Comunicação:**
    - Integre o chatbot com plataformas de mensagens como o WhatsApp, sites ou outros sistemas. Certifique-se de configurar corretamente os canais desejados e salvar as configurações para garantir o funcionamento adequado do chatbot.

4. **Definição de Respostas Esperadas:**
    - No fluxo de atendimento, defina as possíveis respostas que o chatbot espera dos usuários, como nome completo, e-mail, número de contato, endereço, documentos, entre outros. Isso ajudará a estruturar as interações de forma mais eficiente.

5. **Utilização de Inteligência Artificial e Automação:**
    - Aproveite tecnologias de inteligência artificial, como o ChatGPT, para automatizar a criação de fluxogramas e tornar o processo mais eficiente. Essas ferramentas podem ajudar na criação de fluxos de conversação de forma mais rápida e eficaz.

Ao seguir esses passos e utilizar a classe `Flux` de forma adequada, você poderá criar um chatbot eficiente e estruturado, capaz de interagir com os usuários de forma inteligente e personalizada, atendendo às necessidades específicas do seu negócio ou operação.


# Configuração de dependência para o OpenAPI

Este trecho de código XML mostra a configuração de dependências para o OpenAPI em um projeto Spring Boot. Analisando cada parte:

1. **Properties**:
   ```xml
   <properties>
       <spring-ai.version>0.8.1</spring-ai.version>
   </properties>
   ```
   Aqui, uma propriedade chamada `spring-ai.version` é definida com o valor `0.8.1`. Isso é útil para manter a versão do Spring AI consistente em todo o projeto e facilita a atualização posterior, se necessário.

2. **Dependency do OpenAI Spring Boot Starter**:
   ```xml
   <dependency>
       <groupId>org.springframework.ai</groupId>
       <artifactId>spring-ai-openai-spring-boot-starter</artifactId>
   </dependency>
   ```
   Esta é a dependência principal para o OpenAI Spring Boot Starter. Ela inclui as bibliotecas necessárias para integrar o OpenAI em um projeto Spring Boot.

3. **Dependency Management**:
   ```xml
   <dependencyManagement>
       <dependencies>
           <dependency>
               <groupId>org.springframework.ai</groupId>
               <artifactId>spring-ai-bom</artifactId>
               <version>${spring-ai.version}</version>
               <type>pom</type>
               <scope>import</scope>
           </dependency>
       </dependencies>
   </dependencyManagement>
   ```
   Esta seção define o Gerenciamento de Dependências. A dependência do "spring-ai-bom" é definida com o `groupId`, `artifactId`, e `version` correspondentes à propriedade definida anteriormente (`spring-ai.version`). Isso é útil para garantir que todas as dependências relacionadas ao Spring AI sejam mantidas em sincronia com a mesma versão, evitando conflitos de dependência.

4. **Repositórios**:
   ```xml
   <repositories>
       <repository>
           <id>spring-milestones</id>
           <name>Spring Milestones</name>
           <url>https://repo.spring.io/milestone</url>
           <snapshots>
               <enabled>false</enabled>
           </snapshots>
       </repository>
   </repositories>
   ```
   Aqui, um repositório chamado "Spring Milestones" é configurado para obter dependências do Spring. O URL especificado é onde o Maven irá procurar por essas dependências.

No geral, este trecho de código configura as dependências necessárias para o OpenAPI no Spring Boot, garantindo que todas as bibliotecas relevantes estejam incluídas e mantidas com a mesma versão.

# Classe Controller 

Este é um exemplo de um controlador Spring Boot que utiliza a funcionalidade de um assistente virtual baseado em OpenAI para responder a consultas relacionadas a uma livraria. 

```java
package com.bookstore.ai.controller;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bookstore")
public class BookstoreAssistantController {

    private final OpenAiChatClient chatClient;

    public BookstoreAssistantController(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    // http://localhost:8080/bookstore/informations
    // http://localhost:8080/bookstore/informations?message=Pode falar sobre a classe OpenAiChatClient?
    @GetMapping("/informations")
    public String bookstoreChat(@RequestParam(value = "message", defaultValue = "Faça um resumo sobre Spring AI?") String message){
        return chatClient.call(message);
    }


    // http://localhost:8080/bookstore/chat2/informations
    // http://localhost:8080/bookstore/chat2/informations?message=Pode falar sobre a classe OpenAiChatClient?
    @GetMapping("/chat2/informations")
    public ChatResponse bookstoreChatEx2(@RequestParam(value = "message", defaultValue = "Faça um resumo sobre Spring AI?") String message){
        return chatClient.call(new Prompt(message));
    }

    // http://localhost:8080/bookstore/reviews
    // http://localhost:8080/bookstore/reviews?book=O mundo de sofia
    @GetMapping("/reviews")
    public String bookstoreReview(@RequestParam(value = "book", defaultValue = "Dom Quixote") String book) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                  Por favor, me forneça
                  um breve resumo do livro {book}
                  e também a biografia de seu autor.
                """);
        promptTemplate.add("book", book);
        return this.chatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
    }

    // http://localhost:8080/bookstore/stream/informations
    // http://localhost:8080/bookstore/stream/informations?message=Qual a biografia de Charles Duhigg?
    @GetMapping("/stream/informations")
    public Flux<String> bookstoreChatStream(@RequestParam(value = "message",
            defaultValue = "Quais são os livros best sellers dos ultimos anos?") String message){
        return chatClient.stream(message);
    }

    // http://localhost:8080/bookstore/chat2/stream/informations
    // http://localhost:8080/bookstore/chat2/stream/informations?message=Qual a biografia de Charles Duhigg?
    @GetMapping("/chat2/stream/informations")
    public Flux<ChatResponse> bookstoreChatStreamEx2(@RequestParam(value = "message",
            defaultValue = "Quais são os livros best sellers dos ultimos anos?") String message){
        return chatClient.stream(new Prompt(message));
    }

}
```

Explicando cada método presente neste controlador:

1. **Construtor e Injeção de Dependência**:
   ```java
   private final OpenAiChatClient chatClient;

   public BookstoreAssistantController(OpenAiChatClient chatClient) {
       this.chatClient = chatClient;
   }
   ```
   Este é o construtor da classe `BookstoreAssistantController`, que recebe um `OpenAiChatClient` como parâmetro. O `OpenAiChatClient` é uma classe que encapsula a lógica para se comunicar com o serviço de chat da OpenAI.

2. **Método `bookstoreChat`**:
   ```java
   @GetMapping("/informations")
   public String bookstoreChat(@RequestParam(value = "message", defaultValue = "Faça um resumo sobre Spring AI?") String message){
       return chatClient.call(message);
   }
   ```
   Este método mapeia requisições GET para `/bookstore/informations`. Ele espera um parâmetro `message`, que é a pergunta enviada para o assistente virtual. A resposta do assistente virtual é retornada como uma string.

3. **Método `bookstoreChatEx2`**:
   ```java
   @GetMapping("/chat2/informations")
   public ChatResponse bookstoreChatEx2(@RequestParam(value = "message", defaultValue = "Faça um resumo sobre Spring AI?") String message){
       return chatClient.call(new Prompt(message));
   }
   ```
   Similar ao método anterior, este mapeia requisições GET para `/bookstore/chat2/informations`. No entanto, em vez de retornar uma string, retorna um objeto `ChatResponse`, que é uma representação da resposta do assistente virtual em formato estruturado.

4. **Método `bookstoreReview`**:
   ```java
   @GetMapping("/reviews")
   public String bookstoreReview(@RequestParam(value = "book", defaultValue = "Dom Quixote") String book) {
       PromptTemplate promptTemplate = new PromptTemplate("""
               Por favor, me forneça
               um breve resumo do livro {book}
               e também a biografia de seu autor.
           """);
       promptTemplate.add("book", book);
       return this.chatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
   }
   ```
   Este método mapeia requisições GET para `/bookstore/reviews`. Ele espera um parâmetro `book`, que é o nome do livro sobre o qual se deseja obter uma revisão. Ele cria um prompt de solicitação para obter um resumo do livro e a biografia do autor, substituindo o marcador de posição `{book}` pelo nome do livro fornecido. A resposta do assistente virtual é retornada como uma string.

5. **Método `bookstoreChatStream` e `bookstoreChatStreamEx2`**:
   Esses métodos funcionam de maneira semelhante aos métodos `bookstoreChat` e `bookstoreChatEx2`, mas retornam um fluxo (`Flux`) de strings ou `ChatResponse` em vez de uma única resposta. Isso pode ser útil para cenários em que é necessário lidar com respostas de maneira assíncrona ou em tempo real.

Em resumo, este controlador Spring Boot oferece endpoints para interagir com um assistente virtual baseado em OpenAI, permitindo que os usuários façam perguntas sobre livros e recebam respostas relevantes.

--- 

# Autor
## Feito por: `Daniel Penelva de Andrade`