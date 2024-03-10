# Use uma imagem base do servidor web (por exemplo, nginx)
FROM nginx:alpine

# Crie um diretório para os arquivos da aplicação
WORKDIR /usr/share/nginx/html

# Copie todos os arquivos da pasta cadatros para o contêiner
COPY ./cadastro/ /usr/share/nginx/html/cadastro/

# Copie todos os arquivos da pasta login para o contêiner
COPY ./login/ /usr/share/nginx/html/login/
