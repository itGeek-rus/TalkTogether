package Service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import Entity.Notifications;
import Repo.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final String TOPIC = "notifications";
	
	public Notifications createNotifications(String userId, String message, String type) {
		
		Notifications notifications = new Notifications();
		notifications.setUserId(userId);
		notifications.setMessage(message);
		notifications.setTimeStamp(LocalDateTime.now());
		notifications.setRead(false);
		notifications.setType(type);
		
		//сохранение уведомления в MongoDb
		notificationRepository.save(notifications);
		
		//отправка уведомления в Kafka
		kafkaTemplate.send(TOPIC, notifications.getId(), message);
		
		return notifications;
		
		
	}
	
	public List<Notifications> getUserNotifications(String userId, boolean isRead){
		return notificationRepository.findByUserIdAndIsRead(userId, isRead);
	}

}
