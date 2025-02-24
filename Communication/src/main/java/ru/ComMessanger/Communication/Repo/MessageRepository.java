package ru.ComMessanger.Communication.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ComMessanger.Communication.NoSQLEntity.Message;
import ru.ComMessanger.Communication.SQLEntity.Chats;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByChatId(UUID chatId);
}
