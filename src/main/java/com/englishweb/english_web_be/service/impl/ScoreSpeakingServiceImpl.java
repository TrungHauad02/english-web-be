package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.request.PostRequest;
import com.englishweb.english_web_be.dto.response.PostResponse;
import com.englishweb.english_web_be.dto.response.ScoreSpeakingResponse;
import com.englishweb.english_web_be.service.ScoreSpeakingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Slf4j
public class ScoreSpeakingServiceImpl implements ScoreSpeakingService {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${the-fluent-me.x-rapidapi-key}")
    private String rapidApiKey;
    @Value("${the-fluent-me.x-rapidapi-host}")
    private String rapidApiHost;
    @Value("${the-fluent-me.api.url}")
    private String apiUrl;

    @Override
    public PostResponse addPost(PostRequest request) {
        try {
            String url = apiUrl + "/post";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("x-rapidapi-key", rapidApiKey);
            headers.set("x-rapidapi-host", rapidApiHost);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(request);

            HttpEntity<String> requestData = new HttpEntity<>(requestBody, headers);
            log.info("Prepare header before add post fluent.me: {}", headers);
            log.info("Prepare data before add post fluent.me: {}", requestBody);
            ResponseEntity<PostResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestData, PostResponse.class);
            log.info("Add post fluent.me successfully: {}", response.getBody());
            return response.getBody();
        } catch (HttpClientErrorException e){
            log.error("Error when add post into fluent me: {}", e.getMessage());
            throw new RuntimeException("Error when add post the fluent me: " + e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePost(String id) {

    }

    @Override
    public ScoreSpeakingResponse scoreSpeaking(String postId, int scale, String audioProvided) {
        return null;
    }
}
