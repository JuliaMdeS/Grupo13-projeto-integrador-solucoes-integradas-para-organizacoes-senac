# Projeto Integrador - Hemoclick 🩸

## Descrição

Hemoclick é uma aplicação com objetivo principal de criar uma comunidade conectada de doadores de sangue e hemocentros, simplificando o processo de doação e aumentando a disponibilidade de sangue para aqueles que necessitam.

### Participantes 👥

- **Luan Silva Fulnazari de Souza**
- **Jonathan Bandeira de Souza**
- **Julia Magalhães de Sant Anna**
- **Lucas da Silva Gonçalves**

### Protótipo 🖌️

[Clique aqui para visitar.](https://www.figma.com/file/F4Y4xlyGj2p76RkSUNRNCz/Projeto-Integrador?type=design&node-id=0-2&mode=design)


### Stack Utilizada 🌐

- **Back-end:** Java e Spring Boot
- **Front-end:** HTML, CSS e JavaScript
- **Banco de Dados:** PostgreSQL
- **Build e Execução:** Docker

## POC

Este entrega tem como objetivo realizar uma prova de conceito para testar o sistema de login e cadastro de usuários. A POC foi escolhida pelo time como uma etapa inicial para validar a viabilidade técnica e funcional do processo de autenticação e registro de usuários.

### Objetivos da POC 📝

- Verificar a integridade e segurança do sistema de login.
- Testar a funcionalidade de cadastro de novos usuários.
- Avaliar a usabilidade e a experiência do usuário durante o processo de autenticação e registro.

### Metodologia 🔄

A POC será conduzida em um ambiente controlado, onde os casos de teste serão executados em diferentes cenários para identificar possíveis falhas e limitações do sistema. Serão utilizadas técnicas de teste de software e monitoramento para registrar o desempenho e comportamento do sistema durante os testes.

### Resultados Esperados 📦

Espera-se que a POC forneça insights valiosos sobre a robustez e eficácia do sistema de login e cadastro de usuários. Com base nos resultados obtidos, o time poderá tomar decisões informadas sobre os próximos passos do projeto, incluindo ajustes, melhorias e implementações adicionais.

### Apresentando o projeto concluído:

https://github.com/JuliaMdeS/projeto-integrador-mobile-web-grupo27/assets/123558286/c8df8bdd-82a2-4966-955a-cb16a4e2e234

## Assista no YouTube:

[Clique aqui para assistir.](https://youtu.be/ZOJ_lZtjPV8)

## Instruções para Execução

1. Clone o Repositório:

   ```sh  
   git clone https://github.com/JuliaMdeS/projeto-integrador-mobile-web-grupo27.git 
   ```

2. Navege até a pasta /api e faça o build da aplicação backend a partir do comando maven:

    ```sh
    mvn -U clean install
    ```

3. Instale o Docker: Certifique-se de ter o Docker instalado em sua máquina. Você pode encontrar instruções de instalação [clicando aqui.](https://www.docker.com/products/docker-desktop/)


4. Rode o Comando Docker Compose: No diretório raiz do projeto, execute o seguinte comando:

    ```sh
    docker-compose up  
    ```

    Este comando irá construir e iniciar os contêineres Docker para o front-end e back-end da aplicação, juntamente com o banco de dados.


5. Acesse o Front-end: Abra seu navegador e acesse a seguinte URL:

    ```arduino
    http://localhost:8080/login/
    ```

6. Acesse o Back-end: Para acessar o back-end da aplicação, utilize a seguinte URL:

    ```arduino
    http://localhost:8081  
    ```

7. Acessa a documentação no Swagger: Para acessar a documentação da aplicação, utilize a seguinte URL:

    ```arduino
    http://localhost:8081/swagger-ui.html
    ```

8. Para que qualquer alteração reflita no container, é necessário rodar o comando:

    ```sh
    docker-compose up --build
    ```

9. Considerações Finais: Certifique-se que as portas 8080; 8081 e 5432 da sua máquina estão disponíveis, pois o projeto está configurado para utilizar essas portas como entrada do frontend, backend e banco de dados respectivamente.
