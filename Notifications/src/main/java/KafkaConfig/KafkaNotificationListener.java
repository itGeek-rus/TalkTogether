package KafkaConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaNotificationListener {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@KafkaListener(topics = "notifications", groupId = "notifications_group")
	public void listen(String message) {
		messagingTemplate.convertAndSend("/topic/notifications", message);
	}

}
