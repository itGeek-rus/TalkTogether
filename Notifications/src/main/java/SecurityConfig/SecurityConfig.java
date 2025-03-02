package SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("user")
		.password(passwordEncoder().encode("password"))
		.roles("USER");
	}
	
	protected void configure1(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/public/**").permitAll()
				.anyRequest().authenticated()
				)
		.formLogin(form -> form
				.loginPage("/login")
				.permitAll()
				)
		.logout(logout -> logout
				.permitAll()
				);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
