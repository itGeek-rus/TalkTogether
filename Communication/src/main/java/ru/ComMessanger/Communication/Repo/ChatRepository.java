package ru.ComMessanger.Communication.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ComMessanger.Communication.NoSQLEntity.Message;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends MongoRepository<Message, String> {
    List<Message> findByChatId(UUID chatId);
}
