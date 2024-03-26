package com.juank.tinderclone.service;

import com.juank.tinderclone.dto.UserUpdateDTO;
import com.juank.tinderclone.model.User;
import com.juank.tinderclone.repository.UserRepository;
import lombok.AllArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    @Autowired
    UserRepository repo;

    MongoTemplate mongoTemplate;

    public List<User> getGenderUser(String id, String gender) {
        List<String> userMatchedIds = repo.findById(id).get().getMatches();

        Query query = new Query();
        query.addCriteria(Criteria.where("gender_identity").is(gender));
        List<User> users = mongoTemplate.find(query, User.class);
        if (userMatchedIds!= null && userMatchedIds.size() > 0)
            users = users.stream().filter(user -> !userMatchedIds.contains(user.getUser_id())).collect(Collectors.toList());
        return users;
    }

    public User addUser(User user) {
        return repo.save(user);
    }

    public User updateUser(UserUpdateDTO userDTO) {
        Optional<User> userRepo = repo.findById(userDTO.getUser_id());

        if(userRepo.isPresent()) {
            User userToSave = userRepo.get();
            userToSave.setAbout(userDTO.getAbout());
            userToSave.setDob_day(userDTO.getDob_day());
            userToSave.setDob_month(userDTO.getDob_month());
            userToSave.setDob_year(userDTO.getDob_year());
            userToSave.setUrl(userDTO.getUrl());
            userToSave.setGender_identity(userDTO.getGender_identity());
            userToSave.setGender_interest(userDTO.getGender_interest());
            userToSave.setShow_gender(userDTO.getShow_gender());

            return repo.save(userToSave);
        }

        return null;
    }

    public User getUserById(String id) {
        Optional<User> user = repo.findById(id);
        if(user.isPresent())
            return user.get();

        else
            return null;
    }

    public User updateUserMatch(String userId, String matchedUserId) {
        Optional<User> userRepo = repo.findById(userId);

        if(userRepo.isPresent()) {
            User userToSave = userRepo.get();
            userToSave.getMatches().add(matchedUserId);
            return repo.save(userToSave);
        }
        return null;
    }

    public List<User> getMatches(List<String> matchesId) {

        List<User> users = repo.findAllById(matchesId);
        return users;
    }
}
