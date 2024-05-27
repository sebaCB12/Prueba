FROM openjdk:17
COPY "./target/proyecto_Vehiculos-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8100
ENTRYPOINT [ "java" , "-jar" , "app.jar" ] 