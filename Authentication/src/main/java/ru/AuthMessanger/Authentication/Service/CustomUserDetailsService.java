package ru.AuthMessanger.Authentication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.AuthMessanger.Authentication.User.User;
import ru.AuthMessanger.Authentication.UserRepo.UserRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Пользователь с таким именем не найден: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPasswordHash(),
                new ArrayList<>()
        );
    }
}
