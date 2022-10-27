FROM openjdk:19

VOLUME /root/code
VOLUME /root/.m2
COPY . /root/code
WORKDIR /root/code

# install without compiling
RUN ./mvnw jar:jar install:install

CMD ./mvnw spring-boot:run