package KafkaTest;

import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;

import KafkaConfig.NotificationProducer;

@ExtendWith(MockitoExtension.class)
@EmbeddedKafka(partitions = 1, topics = {"notification-topic"})
public class NotificationProducerTest {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private NotificationProducer notificationProducer;
	
	@Autowired
	private EmbeddedKafkaBroker embeddedKafkaBroker;
	
	@Test
	public void testSendNotification() {
		//Отправка уведомлений
		notificationProducer.sendNotifications("Test notification");
		
		//Проверка, что метод send() был вызван с правильными параметрами
		Mockito.verify(kafkaTemplate, Mockito.times(1)).send(anyString(), anyString());
	}

}
