FROM maven:latest

WORKDIR /app
COPY . .
RUN mvn clean install

CMD ["mvn", "spring-boot:run"]
