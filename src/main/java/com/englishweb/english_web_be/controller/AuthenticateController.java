package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.model.Authenticate;
import com.englishweb.english_web_be.security.IntrospecRequest;
import com.englishweb.english_web_be.security.IntrospecResponse;
import com.englishweb.english_web_be.service.AuthenticateService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    @Autowired
    public AuthenticateController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

//    // Endpoint cho đăng nhập
//    @PostMapping("/api/signin")
//    public ResponseEntity<UserDTO> signIn(@Valid @RequestBody UserDTO userDTO) {
//        UserDTO authenticatedUser = authenticateService.signIn(userDTO);
//        return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
//    }

    // Endpoint cho đăng nhập
    @PostMapping("/api/token")
    public ResponseEntity<Authenticate> authenticate(@RequestBody UserDTO userDTO) {
        Authenticate result = authenticateService.authenticate(userDTO); // Trả về đối tượng Authenticate có trạng thái và token
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/introspec")
    public ResponseEntity<IntrospecResponse> authenticate(@RequestBody IntrospecRequest request) throws ParseException, JOSEException {
        IntrospecResponse result = authenticateService.introspec(request); // Trả về đối tượng IntrospecResponse với trạng thái và thông báo
        return ResponseEntity.ok(result);
    }
}
