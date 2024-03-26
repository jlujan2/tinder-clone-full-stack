package com.juank.tinderclone.service;

import com.juank.tinderclone.model.Message;
import com.juank.tinderclone.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MessageService {

    MessageRepository repo;

    MongoTemplate mongoTemplate;
    public void saveMessage(Message message) {
        repo.save(message);
    }

    public List<Message> getMessages(String userId, String correspondingUserId) {
        List<Message> messageList = new ArrayList<>();

        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(
                Criteria.where("fromUserId").is(userId).and("toUserId").is(correspondingUserId),
                Criteria.where("fromUserId").is(correspondingUserId).and("toUserId").is(userId)
        ));

        //query.addCriteria(Criteria.where("fromUserId").is(correspondingUserId).and("toUserId").is(userId));

        List<Message> messages = mongoTemplate.find(query, Message.class);

        return messages;
    }
}
