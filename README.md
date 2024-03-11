# projeto-integrador-mobile-web-grupo27

Projeto Integrador
Quarto semestre TADS e TSI
Segunda Etapa
Prof. Gustavo Calixto

## Integrantes
- Johnatan Bandeira de Souza
- Júlia Magalhães de Sant'Anna
- Luan Silva Fulnazari de Souza
- Lucas Da Silva Gonçalves

## Revisitar Projeto e definir a prova de conceito

Na segunda etapa do projeto integrador, revisitamos a ideia originalmente proposta e decidimos implementar o fluxo de login/cadsatro de um doador do app Hemo Click.

As telas de login e cadastro do Hemo Click haviam sido primeiramente projetadas para uma aplicação mobile, entretanto, ao revisitar a ideia proposta, decidimos seguir com a implementação das telas no formato Web devido a familiaridade dos integrantes com a tecnologia.

## Ambiente de desenvolvimento

### Frontend
- HTML
- CSS
- Javascript

### Backend
- Java (Springboot)

### Banco de dados
- PostgreSQL

### Build e execução
- Docker

## Executando o projeto

Navege até a pasta /api e faça o build da aplicação backend a partir do comando maven:

```sh
mvn -U clean package
```

Como resultado, será criada uma pasta `target/` contendo o executável da aplicação que será posteriormente transferida para o container do docker

No diretório root do projeto, execute o comando

```sh
docker-compose up
```

Espere o tempo de execução do build (pode levar alguns minutos na primeira execução). Após a etapa de build e inicialização do projeto, você poderá visualizar as telas a partir do endereço `http://localhost:8080/login` e `http://localhost:8080/cadastro`. Certifique-se que as portas `8080`; `8081` e `5432` da sua máquina estão disponíveis, pois o projeto está configurado para utilizar essas portas como entrada do frontend, backend e banco de dados respectivamente.

Ao fim das execuções, rodar o comando para desativar os containeres:

```sh
docker-compose down
```

Para que qualquer alteração no frontend reflita no container, é necessário rodar o comando:

```
docker-compose up --build
```

