package Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "notifications")
public class Notifications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Field("userid")
	private String userId;
	
	@Field("message")
	private String message;
	
	@Field("timestamp")
	private LocalDateTime timeStamp;
	
	@Field("isRead")
	private boolean isRead;
	
	@Field("type")
	private String type;
	
}
