package com.juank.tinderclone.controller;

import com.juank.tinderclone.model.Message;
import com.juank.tinderclone.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/messages")
@CrossOrigin(origins = "http://localhost:3000")
public class MessagesController {

    @Autowired
    MessageService messageService;


    @PostMapping
    public void saveMessage(@RequestBody Message message) {
        messageService.saveMessage(message);
    }

    @GetMapping
    public List<Message> getMessages(@RequestParam String userId, @RequestParam String correspondingUserId) {
        return messageService.getMessages(userId, correspondingUserId);
    }
}
