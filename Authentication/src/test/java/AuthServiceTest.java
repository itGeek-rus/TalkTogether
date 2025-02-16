import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import ru.AuthMessanger.Authentication.Service.AuthService;
import ru.AuthMessanger.Authentication.User.User;
import ru.AuthMessanger.Authentication.UserRepo.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension .class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testLoginSuccess(){
        User user = new User();
        user.setUserName("testuser");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("password"));

        when(userRepository.findByUserName("testuser")).thenReturn(user);

        User authenticatedUser = authService.login("testuser", "password");

        assertNotNull(authenticatedUser);
        assertEquals("testuser", authenticatedUser.getUserName());
    }
    
    @Test
    public void testLoginFailure(){
        when(userRepository.findByUserName("testuser")).thenReturn(null);

        User authenticatedUser = authService.login("testuser", "wrongpassword");

        assertNull(authenticatedUser);
    }


}
