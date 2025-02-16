package RegServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Service.RegistrationService;
import User.User;
import UserRepo.UserRepository;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
	
	@InjectMocks
	private RegistrationService registrationService;
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	public void testRegisterUser() {
		
		User user = new User();
		user.setUserName("testUser");
		user.setPasswordHash("password123");
		user.setEmail("test@mail.ru");
		
		User registeredUser = registrationService.register(user);
		
		assertNotNull(registeredUser);
		assertEquals("testUser", registeredUser.getUserName());
		
	}

}
