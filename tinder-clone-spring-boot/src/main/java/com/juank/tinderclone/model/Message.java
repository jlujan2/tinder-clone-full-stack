package com.juank.tinderclone.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Message {

    private Date timestamp;
    private String fromUserId;
    private String toUserId;
    private String message;
}
