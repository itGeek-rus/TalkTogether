package KafkaTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import KafkaConfig.KafkaNotificationListener;

@ExtendWith(MockitoExtension.class)
@EmbeddedKafka(partitions = 1, topics = {"notification-topic"})
public class KafkaNotificationListenerTest {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private KafkaNotificationListener kafkaNotificationListener;

	@Test
	public void testListen() {
		String message = "Test notification";
		
		//Вызов метода слушателя
		kafkaNotificationListener.listen(message);
		
		//Проверка на то, что сообщение было отправлено через WebSocket
		Mockito.verify(messagingTemplate, Mockito.times(1)).convertAndSend("/topic/notifications", message);
	}
}
