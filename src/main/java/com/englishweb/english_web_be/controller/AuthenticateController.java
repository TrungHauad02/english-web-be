package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.model.Authenticate;
import com.englishweb.english_web_be.security.IntrospecRequest;
import com.englishweb.english_web_be.security.IntrospecResponse;
import com.englishweb.english_web_be.service.AuthenticateService;
import com.nimbusds.jose.JOSEException;
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

    @PostMapping("/api/users/token")
    public ResponseEntity<Authenticate> authenticate(@RequestBody UserDTO userDTO) {
        Authenticate result = authenticateService.authenticate(userDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/users/introspec")
    public ResponseEntity<IntrospecResponse> authenticate(@RequestBody IntrospecRequest request) throws ParseException, JOSEException {
        IntrospecResponse result = authenticateService.introspec(request);
        return ResponseEntity.ok(result);
    }
}
