package com.pulsar;

import java.util.concurrent.CompletableFuture;

import org.apache.pulsar.client.api.CompressionType;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	private static final String SERVICE_URL = "pulsar://apache-pulsar:6650";
	private static final String TOPIC_NAME = "test-topic";
	private static final String SUBSCRIPTION_NAME = "test-subscription";
	
	@Bean
	public Producer<String> producer() throws PulsarClientException{
		   return client().newProducer(Schema.STRING)
		  .topic(TOPIC_NAME)
		  .compressionType(CompressionType.LZ4)
		  .create();
	}
	
	private PulsarClient client() throws PulsarClientException {
		return PulsarClient.builder()
		  .serviceUrl(SERVICE_URL)
		  .build();
	}

	@Bean
	public CompletableFuture<Void> consumer() throws PulsarClientException {
		CompletableFuture<Void> consumer = client().newConsumer(Schema.STRING)
				  .topic(TOPIC_NAME)
				  .subscriptionType(SubscriptionType.Exclusive)
				  .subscriptionName(SUBSCRIPTION_NAME)
				  .subscribeAsync()
				  .thenAccept(this::receiveMessageFromConsumer);
		return consumer;
	}
	
	private void receiveMessageFromConsumer(Consumer<String> consumer) {
	    consumer.receiveAsync().thenAccept(message -> {
	    	            System.out.printf("Message received: %s", new String(message.getData()));
	    	            System.out.println("");
	                try {
						consumer.acknowledge(message);
					} catch (PulsarClientException e) {
						e.printStackTrace();
					}
	                receiveMessageFromConsumer(consumer);
	            });
	}
}
