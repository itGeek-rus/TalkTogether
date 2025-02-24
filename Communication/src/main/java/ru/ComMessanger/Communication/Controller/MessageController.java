package ru.ComMessanger.Communication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ComMessanger.Communication.NoSQLEntity.Message;
import ru.ComMessanger.Communication.Service.MessageService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/communication/api/com/v1")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public String showMessagesForm(){
        return "index";
    }

    @PostMapping("/messages")
    public ResponseEntity<Void> sendMessage(@RequestParam UUID chatId,
                                            @RequestParam UUID senderId,
                                            @RequestParam String content,
                                            @RequestParam(required = false) String mediaUrl){
        messageService.sendMessage(chatId, senderId, content, mediaUrl);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable UUID chatId){
        List<Message> messages = messageService.getMessageByChatId(chatId);

        return ResponseEntity.ok(messages);
    }


}
