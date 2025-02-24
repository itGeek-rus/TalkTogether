package ru.ComMessanger.Communication.KafkaConfig;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "message-topic", groupId = "communication-group")
    public void listen(String message){
        System.out.println("Полученное сообщение: " + message);
    }

}
