FROM rabbitmq:3
EXPOSE 5672 15672
VOLUME /var/lib/rabbitmq
COPY rabbitmq.conf /etc/rabbitmq/

RUN rabbitmq-plugins enable rabbitmq_management