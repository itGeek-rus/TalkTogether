package UserRepo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import User.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	User findByUserName(String userName);
	User findByEmail(String email);

}
