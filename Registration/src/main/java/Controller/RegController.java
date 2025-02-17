package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import Service.RegistrationService;
import User.User;

@Controller
@RequestMapping("/registration/api/reg/v1")
public class RegController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@GetMapping("/registr")
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping("/registr")
	public ResponseEntity<User> register(@RequestBody User user){
		
		User registeredUser = registrationService.register(user);
		return ResponseEntity.ok(registeredUser);
		
	}

}
