package com.pulsar;

import org.apache.pulsar.client.api.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

	@Autowired
	Producer<String> producer;

	@GetMapping("/test-pulsar")
	public String test(@RequestParam String content) {
		producer.sendAsync(content).thenAccept(msgId -> {
			System.out.printf("Message with ID %s successfully sent", msgId);
			System.out.println("");
		});
		return "Msg Sent";
	}
}
