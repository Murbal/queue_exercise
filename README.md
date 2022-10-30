# Getting started

```sh
cp .env.dist .env # set COMPOSE_VERSION=1 if you use docker-compose (v1) and not docker compose (v2)
cp rabbitmq/rabbitmq.conf.dist rabbitmq/rabbitmq.conf

./app start
# ./app stop for stopping
# ./app logs for logs
```

## [Assignment](docs/Assignment.pdf)

# Technologies

### Java and Spring Boot

Was a requirement from the assignment.
Had to browse the docs quite a bit to understand how to implement a REST API and connect with RabbitMQ.

### Docker

Docker is something I use for every project, because I don't have to pollute the host system with different programming languages and software.

### RabbitMQ

I needed a message broker with persistency features. I considered redis, but redis' persistency feature is only saving the current state in a fixed interval to the file system, so messages can get lost, if redis crashes between the intervals. Decided to use RabbitMQ because, it's the most commonly used open-source message broker that I know of.

# Design Decisions

## Processor
### General
The processing of the message is done by the [LogProcessor](./processor/src/main/java/com/emirhangueler/processor/processors/LogProcessor.java), while the [Receiver](./processor/src/main/java/com/emirhangueler/processor/Receiver.java) is responsible for listening to the queue and distributing it to the LogProcessor. In the future, there could be more processors needed, so I put the LogProcessor into a separate package called `com.emirhangueler.processor.processors`.

### Scalability
The service as of now, is not scalable at all, because there has to be 1 and only 1 processor service. So if a task in the queue, takes up **x** amount of time, the processing of other tasks will be delayed by **x** amount.

### Maintainability
If a new processor is needed, you will have to map a message, depending on it's content, to the appropriate processor.

## API
### General
The API's main part as of now is the [Sender](./api/src/main/java/com/emirhangueler/api/Sender.java), because the [ReservationController](./api/src/main/java/com/emirhangueler/api/reservation/ReservationController.java) and [ReservationService](./api/src/main/java/com/emirhangueler/api/reservation/ReservationService.java), don't do much right now. The Sender basically just wraps the RabbitTemplate provided by the RabbitMQ integration of spring, in order to abstract the sending of a message to the queue.
I opted for the entity name of *Reservation* for the controller and service, in order to make it a bit more understandable, what they could handle in the future.

### Scalability
The service as of now can be freely scaled, because it doesn't depend on any internal state. It just connects to a RabbitMQ instance, that can also run on a remote server.

### Maintainability
If a new controller and service is needed, it can be created as a new package in the same level as the `com.emirhangueler.api.reservation` package. Spring will automatically pick up the new controller.
If RabbitMQ is to be replaced by another message broker, it will also be a trivial task (in code), as you will only have to replace the RabbitConfiguration and RabbitTemplate with a new configuration class and template.

## General
### Performance
The main bottleneck in the future will be the processor. Right now we could scale it, because we are just logging the message received. But it could be not scalable, if we are updating data in the database and depend on the order of execution in the queue. An example:

- Processor 1 takes Task 1
- Processor 2 takes Task 2
- Processor 2 finishes before Processor 1 and already takes Task 3, even though Task 1 is not finished.

In some cases this would not cause any issues, because task 1 would still get completed. But it *can* cause issues, if Task 3 is expected to only be run, after Task 1 is finished

# Question 4
I'm confused when it comes, to this question. We are using a queue, so we cannot change the execution order, as a queue is FIFO (first in, first out), and we are adding an item to the queue, the moment the REST endpoint gets called. I think answering this question requires some knowledge of the data and of what the processor does. Would gladly discuss this in a call.