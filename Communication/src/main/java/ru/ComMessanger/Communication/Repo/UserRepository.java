package ru.ComMessanger.Communication.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ComMessanger.Communication.SQLEntity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUserName(String userName);
}
