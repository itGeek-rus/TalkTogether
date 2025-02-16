package ru.AuthMessanger.Authentication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.AuthMessanger.Authentication.User.User;
import ru.AuthMessanger.Authentication.UserRepo.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User login(String userName, String password){
        User user = userRepository.findByUserName(userName);

        if (user != null && passwordEncoder.matches(password, user.getPasswordHash())){
            return user;
        }
        return null;
    }

}
