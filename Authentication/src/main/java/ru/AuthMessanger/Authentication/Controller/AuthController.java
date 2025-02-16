package ru.AuthMessanger.Authentication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.AuthMessanger.Authentication.Service.AuthService;
import ru.AuthMessanger.Authentication.User.User;

@Controller
@RequestMapping("/authentication/api/auth/v1")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginForm(){
        return "index";
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        User authenticatedUser = authService.login(user.getUserName(), user.getPasswordHash());

        if(authenticatedUser != null){
            return ResponseEntity.ok(authenticatedUser);
        }else{
            return ResponseEntity.status(401).body(null);
        }
    }

}
