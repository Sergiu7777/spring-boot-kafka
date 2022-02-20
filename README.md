# spring-boot-kafka
Demo application for Spring Boot with Kafka.

This is a small example of how to use Kafka for microservices communication. The project consists of 2 microservices - a producer and a consumer. 

Kafka server has to be installed on the local machine. If using Docker - it can be installed on a Linux image in Docker. More details here: https://kafka.apache.org/quickstart.

A producer is a client that sends messages to the Kafka server to the specified topic.

A consumers is a recipient who receives messages from the Kafka server.

A simple POJO is used as a message content - hence we can see how to send and receive objects via Kafka.

First start the producer-service and after the consumer-service. You'll see in the IDE outputs how the messages are sent and then received in real time.

Please, contact for any questions :)
