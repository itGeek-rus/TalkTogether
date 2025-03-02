package ru.NotMessanger.Notifications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import Entity.Notifications;
import Repo.NotificationRepository;
import Service.NotificationService;

@ExtendWith(MockitoExtension.class)
public class NotificationsServiceTest {
	
	@InjectMocks
	private NotificationService notificationService;
	
	@Mock
	private NotificationRepository notificationRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testCreateNotifications() {
		Notifications notifications = new Notifications();
		notifications.setUserId("1");
		notifications.setMessage("Test message");
		notifications.setTimeStamp(LocalDateTime.now());
		notifications.setRead(false);
		
		//Настраиваем Mock для репозитория
		Mockito.when(notificationRepository.save(any(Notifications.class))).thenReturn(notifications);
		
		//Вызов метода создания уведомления
		Notifications createdNotifications = notificationService.createNotifications("1", "Test message", null);
		
		//Проверям результаты
		assertNotNull(createdNotifications);
		assertEquals("Test message", createdNotifications.getMessage());
		assertEquals("1", createdNotifications.getUserId());
		assertEquals(false, createdNotifications.isRead());
		
	}
	
	@Test
	public void testGetUserNotifications() {
		Notifications notifications = new Notifications();
		notifications.setUserId("1");
		notifications.setMessage("Test message");
		notifications.setTimeStamp(LocalDateTime.now());
		notifications.setRead(false);
		
		//Настройка Mock для репозитория
		Mockito.when(notificationRepository.findByUserIdAndIsRead("1", false))
		.thenReturn(Collections.singletonList(notifications));
		
		//Вызов метода получения уведомлений
		List<Notifications> notifications1 = notificationService.getUserNotifications("1", false);
		
		//Проверка результатов
		assertNotNull(notifications1);
		assertEquals("1", notifications1.size());
		assertEquals("Test message", notifications1.get(0).getMessage());
		
	}

}
