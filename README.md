## Projeto MovieFlix
> Um projeto de catálogo de filmes com serviço de autenticação 

### Backend
O backend consiste em uma **API REST** com banco de dados Postgress que disponibiliza os seguintes endpoints da aplicação:
* [GET] /genres 
Lista todos os gêneros disponíveis
* [GET] /movies?{args}
Lista todos os filmes com paginação e filtragem por gênero
* [GET] /movies/{args}
Lista as informações de um filme específico
* [POST] /reviews
Insere um comentário a um filme

Além disso, há um sistema de autenticação através de Spring Security com OAuth2, tal que usuários definidos como visitantes [ROLE] podem acessar os conteúdos e somente definidos como membros [ROLE] podem inserir comentários.
* [POST] /oauth/token
Caminho para autenticar usuário


> Endereço do deploy da aplicação no Heroku

[![](https://img.shields.io/badge/MOVIEFLIX-ALPHA-9370DB?logo=heroku&labelColor=9370DB&color=gray&style=for-the-badge)](https://nihwl-movieflix.herokuapp.com)

🚀[Postman](), [Swagger Docs](https://nihwl-movieflix.herokuapp.com/swagger-ui.html)