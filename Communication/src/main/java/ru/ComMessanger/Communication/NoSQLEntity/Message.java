package ru.ComMessanger.Communication.NoSQLEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field("chat_id")
    private UUID chatId;

    @Field("sender_id")
    private UUID senderId;

    @Field("content")
    private String content;

    @Field("media_url")
    private String mediaURL;

    @Field("timestamp")
    private LocalDateTime timeStamp;

}
