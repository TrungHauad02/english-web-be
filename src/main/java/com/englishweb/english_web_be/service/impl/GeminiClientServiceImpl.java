package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.request.GeminiRequest;
import com.englishweb.english_web_be.service.GeminiClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

@Service
public class GeminiClientServiceImpl implements GeminiClientService {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${gemini.api.key}")
    private String apiKey;
    @Value("${gemini.api.url}")
    private String apiUrl;

    @Override
    public String generateText(String prompt) {
        GeminiRequest geminiRequest = GeminiRequest.builder()
                .contents(List.of(Map.of("parts", List.of(Map.of("text", prompt)))))
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = objectMapper.writeValueAsString(geminiRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting request object to JSON: " + e.getMessage(), e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        String url = apiUrl + ":generateContent?key=" + apiKey;

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            String responseBody = response.getBody();
            ObjectMapper responseMapper = new ObjectMapper();
            JsonNode rootNode = responseMapper.readTree(responseBody);
            JsonNode candidatesNode = rootNode.path("candidates");
            if (candidatesNode.isArray() && !candidatesNode.isEmpty()) {
                JsonNode contentNode = candidatesNode.get(0).path("content").path("parts").get(0);
                return contentNode.path("text").asText();
            } else {
                throw new RuntimeException("No candidates found in the response.");
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new RuntimeException("Unauthorized access to Gemini API. Please check your API key and permissions.", e);
            } else {
                throw new RuntimeException("Error communicating with Gemini API: " + e.getMessage(), e);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred: " + e.getMessage(), e);
        }
    }
}

