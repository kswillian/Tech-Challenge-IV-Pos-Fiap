# TECH CHALLENGE PÓS FIAP - FIAPLUS API

## Descrição do Projeto

Este repositório contém o código-fonte do Tech Challenge IV do programa de pós-graduação FIAP + Alura, focado no desenvolvimento de uma aplicação web de streaming de vídeos. O projeto envolve a criação de uma plataforma que permite o gerenciamento e exibição de vídeos, aplicando conceitos avançados de desenvolvimento com Spring Framework.

## Objetivo

O objetivo deste desafio é desenvolver uma aplicação que permita:

- Criação, atualização, listagem e exclusão de vídeos.
- Filtragem de vídeos por título, data de publicação e categoria.
- Marcação de vídeos como favoritos.
- Implementação de um sistema de recomendação baseado nos favoritos do usuário.
- Visualização de estatísticas, como quantidade total de vídeos, vídeos favoritados e média de visualizações.

## Requisitos Funcionais

- Os vídeos devem conter título, descrição, URL e data de publicação.
- A listagem de vídeos deve ser paginada e ordenável.
- Implementação de filtros de busca e categorização de vídeos.
- Sistema de recomendação e marcação de favoritos.
- Endpoint para estatísticas relacionadas aos vídeos.

## Requisitos Técnicos

- Uso do Spring WebFlux para endpoints reativos.
- Spring Boot para configuração e inicialização da aplicação.
- Spring Data para a camada de persistência com suporte a bancos de dados reativos (ex: MongoDB).
- Clean Architecture para estruturação da aplicação em Controllers, Services, Use Cases, Repositories.
- Testes unitários e de integração com cobertura de pelo menos 80%.
- Boas práticas de nomenclatura, organização de código e documentação.
- Validação de entrada nos endpoints.
- Gerenciamento de dependências com Maven ou Gradle.

## Tecnologias Utilizadas

- [Java - Versão 17](https://www.oracle.com/java/)
- [Spring Framework](https://spring.io/projects/spring-framework)
- [Spring WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
- [Lombok](https://projectlombok.org/)
- [MongoDB](https://www.mongodb.com/)
- [Docker](https://www.docker.com/)

## Como Executar o Projeto

### Preparativos

1. **Certifique-se de que o Docker esteja instalado** em sua máquina.
2. **Clone este repositório** para o seu ambiente de desenvolvimento.

### Configuração do Ambiente

1. Abra o projeto na sua IDE de preferência que suporte desenvolvimento Java, como IntelliJ IDEA ou Eclipse.

### Inicialização do MySQL via Docker

1. Inicie o Docker Daemon em sua máquina.
2. No terminal ou prompt de comando, navegue até a pasta raiz do projeto e execute o seguinte comando:

```bash
docker-compose up -d
```

Nota: A opção -d fará com que os containers rodem em background.

3. Execute o projeto - o servidor será iniciado automaticamente.
   Certifique-se de ter o ambiente Java e todas as outras dependências instaladas.

## Contribuições

- Sinta-se à vontade para contribuir para este projeto. Para problemas, solicitações de recursos ou correções de bugs, abra uma issue no GitHub.

---

Desenvolvido como parte do TECH CHALLENGE PÓS FIAP - FIAPLUS API.


Desenvolvido por:

* Diogo Valente - RM 348497
* Matheus Sena - RM 430025
* Willian Kaminski - RM 430025
