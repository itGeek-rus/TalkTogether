package KafkaTest;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import Service.RegistrationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
@EmbeddedKafka(partitions = 1, topics = {"registration-topic"})
public class KafkaTest {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private EmbeddedKafkaBroker embeddedKafkaBroker;
	
	@Test
	public void testKafkaMessageSent() {
		
		kafkaTemplate.send("registration-topic", "Test message");
		
		Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafkaBroker);
		DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps);
		Consumer<String, String> consumer = consumerFactory.createConsumer();
		embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, "registration-topic");
		
		ConsumerRecords<String, String> records = KafkaTestUtils.getRecords(consumer);
		assertFalse(records.isEmpty(), "Никаких записей от Kafka получено не было");
		String message = records.iterator().next().value();
		
		assertEquals("Test message", message);
		
	}

}
