package Repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import Entity.Notifications;

public interface NotificationRepository extends MongoRepository<Notifications, String> {
	
	List<Notifications> findByUserIdAndIsRead(String userId, boolean idRead);
	List<Notifications> findByUserIdAndTimestampAfter(String userId, LocalDateTime timeStamp);
	List<Notifications> findByUserIdAndType(String userId, String type);

}
