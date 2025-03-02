package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DTO.NotificationDto;
import Entity.Notifications;
import Service.NotificationService;

@RestController
@RequestMapping("notifications/api/v1")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@PostMapping
	public ResponseEntity<Notifications> createNotifications(@RequestBody NotificationDto notificationDto){
		
		Notifications notifications = notificationService.createNotifications(notificationDto.getUserId(), notificationDto.getMessage(), null);
		return ResponseEntity.status(HttpStatus.CREATED).body(notifications);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<Notifications>>getUserNotifications(@PathVariable String userId, @RequestParam boolean isRead){
		List<Notifications> notifications = notificationService.getUserNotifications(userId, isRead);
		return ResponseEntity.ok(notifications);
	}

}
