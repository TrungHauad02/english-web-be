package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.request.PostRequest;
import com.englishweb.english_web_be.dto.response.PostResponse;
import com.englishweb.english_web_be.dto.response.ScoreSpeakingResponse;
import com.englishweb.english_web_be.repository.SpeakingConversationRepository;
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

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class ScoreSpeakingServiceImpl implements ScoreSpeakingService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final SpeakingConversationRepository speakingConversationRepository;
    @Value("${the-fluent-me.x-rapidapi-key}")
    private String rapidApiKey;
    @Value("${the-fluent-me.x-rapidapi-host}")
    private String rapidApiHost;
    @Value("${the-fluent-me.api.url}")
    private String apiUrl;

    public ScoreSpeakingServiceImpl(SpeakingConversationRepository speakingConversationRepository) {
        this.speakingConversationRepository = speakingConversationRepository;
    }

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
        try {
            // Tạo URL bằng cách thêm ID vào endpoint API
            String url = String.format("%s/post/%s", apiUrl, id);

            // Tạo headers cho request
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("x-rapidapi-key", rapidApiKey);
            headers.set("x-rapidapi-host", rapidApiHost);

            // Tạo HttpEntity với headers
            HttpEntity<String> requestEntity = new HttpEntity<>("{}", headers);

            // Gửi yêu cầu DELETE
            log.info("Sending DELETE request to FluentMe API: {}", url);
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    requestEntity,
                    String.class
            );

            // Log phản hồi thành công
            log.info("Deleted post successfully: {}", response.getBody());
        } catch (HttpClientErrorException e) {
            log.error("Error when deleting post from FluentMe: {}", e.getMessage());
            throw new RuntimeException("Error when deleting post: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error when deleting post: {}", e.getMessage());
            throw new RuntimeException("Unexpected error when deleting post: " + e.getMessage());
        }
    }


    @Override
    public ScoreSpeakingResponse scoreSpeaking(String speakingConversationId, int scale, String audioProvided) {
        try {
            String postId = speakingConversationRepository.findById(speakingConversationId).get().getPostId();
            // Construct the URL with the postId and scale
            String url = String.format("%s/score/%s?scale=%d", apiUrl, postId, scale);

            // Set the request headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("x-rapidapi-key", rapidApiKey);
            headers.set("x-rapidapi-host", rapidApiHost);

            // Create the request body
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(
                    Map.of("audio_provided", audioProvided)
            );

            // Create the HttpEntity with the headers and body
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Log the request details
            log.info("Prepare headers for scoring: {}", headers);
            log.info("Prepare request body for scoring: {}", requestBody);

            // Make the POST request and get the response
            ResponseEntity<ScoreSpeakingResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    ScoreSpeakingResponse.class
            );

            // Log the successful response
            log.info("Score speaking successfully: {}", response.getBody());

            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("HTTP error while scoring speaking: {}", e.getMessage());
            throw new RuntimeException("Error while scoring speaking: " + e.getMessage());
        } catch (JsonProcessingException e) {
            log.error("Error processing JSON: {}", e.getMessage());
            throw new RuntimeException("Error processing JSON: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            throw new RuntimeException("Unexpected error while scoring speaking: " + e.getMessage());
        }
    }
}
