package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import User.User;
import UserRepo.UserRepository;

import java.time.LocalDateTime;

@Service
public class RegistrationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public User register(User user) {
		if(userRepository.findByUserName(user.getUserName()) != null || userRepository.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException("Пользователь с таким именем и email уже есть в системе");
		}
		
		user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdateAt(LocalDateTime.now());
		
		User redisteredUser = userRepository.save(user);
		
		kafkaTemplate.send("registration-topic", "Пользователь зарегистрирован: " + redisteredUser.getUserName());
		
		return redisteredUser;
		
	}

}
