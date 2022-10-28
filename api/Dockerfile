FROM openjdk:19

EXPOSE 8080

VOLUME /root/code
VOLUME /root/.m2
COPY . /root/code
WORKDIR /root/code

CMD ./mvnw spring-boot:run