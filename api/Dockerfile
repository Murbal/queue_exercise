FROM openjdk:19

EXPOSE 8080

VOLUME /root/code
VOLUME /root/.m2
COPY . /root/code
WORKDIR /root/code

# install deps without compiling, because env variables, provided by docker compose, are needed on runtime -> not available while building image
RUN ./mvnw jar:jar install:install

CMD ./mvnw spring-boot:run