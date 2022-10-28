FROM openjdk:19

VOLUME /root/code
VOLUME /root/.m2
COPY . /root/code
WORKDIR /root/code

CMD ./mvnw spring-boot:run