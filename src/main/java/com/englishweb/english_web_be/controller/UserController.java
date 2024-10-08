package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.repository.UserSpringDataJpaRepository;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import java.util.List;

@RestController
public class UserController {
    UserSpringDataJpaRepository repository;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public UserController(UserSpringDataJpaRepository repository){this.repository = repository;}
    @GetMapping("/users/{id}")
    public User findById(@PathVariable String id){
        return repository.findById(id).orElseGet(User::new);
    }

    @GetMapping("/users")
    public List<User> findAllUser(){
        return repository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return repository.save(user);
    }
}
