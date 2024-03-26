package com.juank.tinderclone.controller;

import com.juank.tinderclone.dto.UserUpdateDTO;
import com.juank.tinderclone.model.User;
import com.juank.tinderclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/gendered-user")
    public List<User> genderedUsers(@RequestParam String id,@RequestParam String gender) {
        return userService.getGenderUser(id, gender);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

    @PutMapping
    public User updateUser(@RequestBody UserUpdateDTO userDto) {

        return userService.updateUser(userDto);
    }


    @PutMapping("/match")
    public User updateUserMatch(@RequestParam String userId, @RequestParam String matchedUserId) {
        return userService.updateUserMatch(userId, matchedUserId);
    }

    @GetMapping("/match")
    public List<User> getUserMatch(@RequestParam(value="matchesId") List<String> matchesId) {
        return userService.getMatches(matchesId);
    }

    @GetMapping
    public User getUser(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/matches")
    public List<User> getMatches(@RequestParam String id) {
        return null;
    }


}
