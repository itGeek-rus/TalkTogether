package Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	
	@KafkaListener(topics = "myTopic", groupId = " registration-group")
	public void listen(String message) {
		System.out.println("Recived message: " + message);
	}

}
