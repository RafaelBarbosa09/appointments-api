FROM maven:3.9.4-amazoncorretto-20-al2023

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

EXPOSE 8080

CMD mvn spring-boot:run