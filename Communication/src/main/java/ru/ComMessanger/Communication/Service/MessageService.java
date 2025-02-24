package ru.ComMessanger.Communication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.ComMessanger.Communication.NoSQLEntity.Message;
import ru.ComMessanger.Communication.Repo.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(UUID chatId, UUID senderId, String content, String mediaUrl){
        Message message = new Message();
        message.setChatId(chatId);
        message.setSenderId(senderId);
        message.setContent(content);
        message.setMediaURL(mediaUrl);
        message.setTimeStamp(LocalDateTime.now());

        message.setId(UUID.randomUUID().toString());

        messageRepository.save(message);

        kafkaTemplate.send("message-topic", message.getId(), content);
    }

    public List<Message> getMessageByChatId(UUID chatId){
        return messageRepository.findByChatId(chatId);
    }

}
