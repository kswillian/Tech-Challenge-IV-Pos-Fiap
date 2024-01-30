# Tech Challenge Pós FIAP - FIAPLUS API

## Descrição do Projeto

Este repositório contém o código-fonte do Tech Challenge IV do programa de pós-graduação FIAP + Alura. O projeto foca no desenvolvimento de uma aplicação web de streaming de vídeos utilizando tecnologias avançadas com Spring Framework. A plataforma permite gerenciamento e exibição de vídeos, aplicando conceitos de desenvolvimento modernos e eficientes.

## Objetivo

Desenvolver uma aplicação de streaming de vídeos com as seguintes funcionalidades:

- Criação, atualização, listagem e exclusão de vídeos.
- Filtragem de vídeos por título, data de publicação e categoria.
- Marcação de vídeos como favoritos.
- Sistema de recomendação baseado nos favoritos do usuário.
- Visualização de estatísticas (total de vídeos, favoritos, média de visualizações).

## Requisitos Funcionais

- Vídeos com título, descrição, URL e data de publicação.
- Listagem paginada e ordenável de vídeos.
- Filtros de busca e categorização de vídeos.
- Sistema de recomendação e favoritos.
- Endpoint para estatísticas de vídeos.

## Requisitos Técnicos

- Spring WebFlux para endpoints reativos.
- Spring Boot para configuração e inicialização.
- Spring Data para persistência com bancos de dados reativos (ex: MongoDB).
- Clean Architecture: Controllers, Services, Use Cases, Repositories.
- Testes unitários e de integração (cobertura ≥ 80%).
- Boas práticas de código e documentação.
- Validação de entrada nos endpoints.
- Gerenciamento de dependências com Maven ou Gradle.

## Tecnologias Utilizadas

- [Java - Versão 17](https://www.oracle.com/java/)
- [Spring Framework](https://spring.io/projects/spring-framework)
- [Spring WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
- [Lombok](https://projectlombok.org/)
- [MongoDB](https://www.mongodb.com/)
- [JUnit](https://junit.org/junit5/)
- [Mockito](http://mockito.org/)

## Como Executar o Projeto

### Preparativos

1. Clone este repositório.

### Configuração e Execução

1. Abra o projeto na sua IDE preferida (ex: IntelliJ IDEA, Eclipse).
2Execute o projeto (servidor inicia automaticamente).

## Sobre a modelagem

O diagrama abaixo mostra a modelagem de classes do projeto:
![](modelagem.png)


### Classe \`Statistic\`

Esta classe representa estatísticas relacionadas a vídeos. Ela possui dois atributos: \`numberViews\` (o número de visualizações) e \`numberFavoriteVideos\` (o número de vezes que os vídeos foram marcados como favoritos).

### Classe \`Video\`

Esta classe representa um vídeo. Inclui atributos como \`id\` (identificador único), \`title\`, \`description\`, \`category\` (tipo de vídeo, por exemplo, DRAMA, COMÉDIA), \`url\` (link para o vídeo) e \`dateRegister\` (a data de registro do vídeo).

### Classe \`User\`

Esta classe representa um usuário. Inclui \`id\` (identificador único), \`name\`, \`email\` (endereço de e-mail único), \`favorites\` (uma coleção de vídeos favoritos) e \`dateRegister\` (a data de registro do usuário).

### Enumeração \`Category\`

Esta é uma enumeração que representa diferentes categorias de vídeos, como DRAMA, THRILLER, AVENTURA, etc.

O diagrama também mostra uma relação entre \`User\` e \`Video\`, indicando que um usuário pode ter vários vídeos favoritos:

## Endpoints

Esses são os endpoints disponíveis na API:

### FedController

- **GET `/v1/fed/upload`**: Renderiza a página de upload.
- **GET `/v1/fed/streaming`**: Renderiza a página de streaming.

### UserController

- **POST `/v1/users`**: Cria um novo usuário.
- **GET `/v1/users`**: Lista todos os usuários com suporte a paginação e filtros.
- **GET `/v1/users/{id}`**: Retorna um usuário pelo ID.
- **PATCH `/v1/users/{id}`**: Atualiza um usuário pelo ID.
- **DELETE `/v1/users/{id}`**: Exclui um usuário pelo ID.

### VideoController

- **POST `/v1/videos`**: Cria um novo vídeo.
- **GET `/v1/videos`**: Lista todos os vídeos com suporte a paginação e filtros.
- **GET `/v1/videos/listAllByCategory/{category}`**: Lista vídeos por categoria.
- **GET `/v1/videos/{id}`**: Retorna um vídeo pelo ID.
- **GET `/v1/videos/findByTitle/{title}`**: Busca um vídeo pelo título.
- **PATCH `/v1/videos/{id}`**: Atualiza um vídeo pelo ID.
- **DELETE `/v1/videos/{id}`**: Exclui um vídeo pelo ID.
- **POST `/v1/videos/upload`**: Permite o upload de um vídeo.
- **GET `/v1/videos/video/{title}`**: Fornece streaming de vídeo pelo título.

Para testes na API, utilize o [Postman](https://www.postman.com/).

## Uso do Spring WebFlux

Este projeto utiliza o Spring WebFlux para criar uma aplicação reativa non-blocking, otimizando o processamento assíncrono e eficiência nas operações I/O.

As principais características incluem:

- **ReactiveMongoTemplate:** Utilizado para interações reativas com o MongoDB.
- **Retorno de Flux e Mono:** Métodos das classes de serviço retornam \`Mono\` ou \`Flux\`, facilitando operações assíncronas.
- **CRUD Reativo:** As operações CRUD são implementadas de forma reativa, utilizando os repositórios do Spring Data.
- **Upload e Streaming Reativos:** O upload de arquivos e o streaming de vídeos são gerenciados de forma reativa, proporcionando uma experiência de usuário eficiente e responsiva.

## Testes Unitários

O projeto utiliza JUnit e Mockito para realizar testes unitários, garantindo a qualidade e a corretude das funcionalidades implementadas. Os testes abrangem os serviços de vídeo e usuário, verificando operações CRUD e outras lógicas de negócios.

### Testes de Vídeo (`VideoServiceImplTest`)

- **Criação de Vídeo:** Testa a criação de vídeos com as propriedades corretas.
- **Listagem por ID:** Verifica a recuperação de vídeos por ID.
- **Atualização de Vídeo:** Testa a atualização de informações de um vídeo.
- **Exclusão de Vídeo:** Verifica a remoção correta de vídeos pelo ID.

### Testes de Usuário (`UserServiceImplTest`)

- **Criação de Usuário:** Testa o salvamento e retorno de usuários criados.
- **Listagem de Todos os Usuários:** Verifica se a lista paginada de usuários é retornada corretamente.
- **Listagem por ID:** Testa a recuperação de usuários por ID.
- **Atualização de Usuário:** Verifica a atualização correta de usuários.
- **Exclusão de Usuário:** Testa a exclusão de usuários pelo ID.

Estes testes são fundamentais para assegurar o comportamento esperado das funcionalidades e a estabilidade do sistema.

O projeto segue os princípios da Clean Architecture para promover uma separação clara de preocupações, facilitar a manutenção e a escalabilidade e melhorar a testabilidade. Aqui está uma avaliação da estrutura do projeto:

- **Entidades (Document):** Localizadas no coração da aplicação, representam o modelo de negócios.
- **Casos de Uso (Service):** Implementam a lógica de negócios, agindo como a camada de aplicação.
- **Adaptadores de Interface (Controller, Mapper):** Transformam dados entre a camada de casos de uso e a camada externa, como a web.
- **Frameworks e Drivers (Configuration, Repository):** Configurações e interações com o banco de dados estão isoladas da lógica de negócios.

O projeto também inclui camadas auxiliares como `builder` para a construção de objetos, `enums` para definições de tipos enumerados, `exception` para o tratamento de exceções, `model` para objetos de transferência de dados e `util`


### Benefícios da Clean Architecture
- **Independência de Frameworks**: A lógica de negócios não é dependente de bibliotecas externas ou frameworks, tornando-a mais resistente a mudanças tecnológicas.
- **Testabilidade**: As camadas internas não dependem das externas, o que facilita a escrita de testes unitários.
- **Independência de UI**: A UI pode mudar sem afetar as regras de negócio.
- **Independência de Banco de Dados**: A lógica de negócio não está atrelada a um tipo específico de banco de dados.
- **Flexibilidade e Manutenibilidade**: O código é mais organizado, flexível e fácil de manter.


## Contribuições

Contribuições são bem-vindas. Para problemas, recursos ou correções, abra uma issue no GitHub.

---

Desenvolvido por Diogo Valente, Matheus Sena e Willian Kaminski como parte do TECH CHALLENGE PÓS FIAP - FIAPLUS API.
