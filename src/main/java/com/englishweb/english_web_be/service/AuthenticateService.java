package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.model.Authenticate;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.security.IntrospecRequest;
import com.englishweb.english_web_be.security.IntrospecResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticateService {

    IntrospecResponse introspec(IntrospecRequest request) throws JOSEException, ParseException;

    Authenticate authenticate(UserDTO dto);

    UserDTO signIn(UserDTO dto);
}
