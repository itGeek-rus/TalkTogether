package DTO;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto {
	
	@Field("userid")
	private String userId;
	
	@Field("message")
	private String message;
	
	@Field("type")
	private String type;

}
