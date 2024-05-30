<h1 align="center">
  <br>
  <picture>
    <source media="(prefers-color-scheme: dark)" width="200" srcset="https://raw.githubusercontent.com/LuccaBarroso/correnem/main/public/LogoWhite.png">
    <source media="(prefers-color-scheme: light)" width="200" srcset="https://raw.githubusercontent.com/LuccaBarroso/correnem/main/public/LogoMain.png">
    <img alt="Logo do Correnem" width="200" src="https://raw.githubusercontent.com/LuccaBarroso/correnem/main/public/LogoMain.png">
  </picture>
  <br>
  Correnem
  <br>
</h1>

<h4 align="center">Uma plataforma para correção de redações do ENEM de forma automatizada com <a href="https://deepmind.google/technologies/gemini/pro/" target="_blank">IA</a>.</h4>

<p align="center">
  <a href="#funcionalidades">Funcionalidades</a> •
  <a href="#como-usar">Como usar</a> •
  <a href="#creditos">Creditos</a>
</p>

![screenshot](https://raw.githubusercontent.com/LuccaBarroso/correnem/main/public/CorrenemDemo.gif)

## Funcionalidades

-   **Correção automática de redações por modelos de IA contemporâneos.**
-   **Feedback detalhado para alunos.**
-   **Edição de feedback por professores em prol da sua autonomia.**
-   **Interface intuitiva de usuário.**
-   **Upload e gestão de redações.**
-   **Relatórios de desempenho dos alunos.**
-   **Autonomia para professores.**

## Como usar

Para clonar a aplicação você vai precisar do [Git](https://git-scm.com), do [Node.js](https://nodejs.org/en/download/) (que vem com o [npm](http://npmjs.com)) caso queira executar o frontend localmente e finalmente, o [Docker](https://www.docker.com/products/docker-desktop/). Na linha de comando:

```bash
# Clonar o repositório
$ git clone https://github.com/gabriel-fonseca-repo/glyp-correnem

# Entrar no repositório
$ cd glyp-correnem

# Executar a aplicação
$ docker-compose --profile system up -d --build
```

Versões utilizadas de cada ferramenta:

-   `$ git --version -> git version 2.45.1.windows.1`
-   `$ node --version -> v18.20.2`
-   `$ npm --version -> 10.5.0`
-   `$ docker --version -> Docker version 26.1.1, build 4cf5afa`

## Creditos

Esse software usa as seguintes tecnologias de código-aberto:

-   [Spring Boot](https://spring.io/)
-   [Maven](https://maven.apache.org/)
-   [PostgreSQL](https://www.postgresql.org/)
-   [VueJS](https://vuejs.org/)
-   [Docker](https://www.docker.com/)
