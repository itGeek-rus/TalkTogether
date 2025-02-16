package ru.AuthMessanger.Authentication.UserRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.AuthMessanger.Authentication.User.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

}
