package com.juank.tinderclone.repository;

import com.juank.tinderclone.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
