package com.englishweb.english_web_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {   
    @Autowired
    UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping("/api/users")
    public ResponseEntity<Page<UserDTO>> findByPage(@RequestParam int page,
                                                     @RequestParam int size,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(userService.findByPage(page, size, sortBy, sortDir, UserDTO.class), HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.create(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/api/users")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.update(userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

}
