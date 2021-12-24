# syntax=docker/dockerfile:1
# Com a tag acima eu garanto que  o docker usara a sintaxe mais atual


#FROM adoptopenjdk/openjdk11:ubi as base
# A tag acima indica qual a versão do java irei utilizar
FROM openjdk:16-alpine3.13 as base

WORKDIR /app
# Aqui eu defino o diretorio de trabalho da imagem


COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# O COPY comando leva dois parâmetros
# O primeiro parâmetro informa ao Docker quais arquivos você gostaria de copiar para a imagem
# O segundo parâmetro informa ao Docker onde você deseja que os arquivos sejam copiados





RUN  ./mvnw -B  dependency:go-offline 
#RUN chmod +x ./mvnw
# Isso funciona exatamente da mesma maneira como se estivéssemos executando mvnw(ou mvn)
# dependência localmente em nossa máquina, mas desta vez as dependências serão instaladas 
# na imagem.


COPY src ./src
# Este COPY comando pega todos os arquivos localizados no diretório atual e os copia na imagem. 


FROM base as test
CMD ["./mvnw", "test"]


FROM base as development
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=dev", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]


FROM base as build
RUN ./mvnw -B package



FROM openjdk:11-jre-slim as production
EXPOSE 8080
COPY --from=build /app/target/appproduto-*.jar /appproduto.jar

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/appproduto.jar"]

# antigo -> CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=dev"]
# Agora, tudo o que temos a fazer é dizer ao Docker qual comando queremos executar
# quando nossa imagem for executada dentro de um contêiner. Fazemos isso usando o CMD comando.


#ENTRYPOINT ["java","-jar","appproduto.jar"]


     
