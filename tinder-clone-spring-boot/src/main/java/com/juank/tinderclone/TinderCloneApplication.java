package com.juank.tinderclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TinderCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinderCloneApplication.class, args);
	}

}
