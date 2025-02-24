package ru.ComMessanger.Communication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import ru.ComMessanger.Communication.NoSQLEntity.Message;
import ru.ComMessanger.Communication.Repo.MessageRepository;
import ru.ComMessanger.Communication.Service.MessageService;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMessages(){
        UUID chatId = UUID.randomUUID();
        UUID senderId = UUID.randomUUID();
        String content = "Hello, World!";
        String mediaUrl = null;

        messageService.sendMessage(chatId, senderId, content, mediaUrl);

        verify(messageRepository, times(1)).save(any(Message.class));

        verify(kafkaTemplate, times(1)).send(eq("message-topic"), anyString(), eq(content));
    }

}
