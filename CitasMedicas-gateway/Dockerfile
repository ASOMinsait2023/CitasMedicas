FROM openjdk:17

WORKDIR /app

COPY ./CitasMedicas-gateway/target/CitasMedicas-gateway-0.0.1-SNAPSHOT.jar .

EXPOSE 7090

CMD ["java","-jar", "CitasMedicas-gateway-0.0.1-SNAPSHOT.jar"]