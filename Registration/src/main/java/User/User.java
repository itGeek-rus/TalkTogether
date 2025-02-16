package User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "uuid")
	private UUID id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String userName;
	
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "update_at")
	private LocalDateTime updateAt;

}
