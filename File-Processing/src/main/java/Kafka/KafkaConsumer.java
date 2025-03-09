package Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "file-upload-topic", groupId = "fileProcessing-group")
    public void consumeMessage(String message){
        System.out.println("Полученное сообщение от Kafka: " + message);
    }

}
