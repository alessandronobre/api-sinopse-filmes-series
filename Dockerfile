FROM openjdk
WORKDIR /app
COPY target/api-sinopse-filmes-series-0.0.1-SNAPSHOT.jar /app/sinopsefs-api-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/app/sinopsefs-api-0.0.1.jar"]