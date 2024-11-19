package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.request.PostRequest;
import com.englishweb.english_web_be.dto.response.PostResponse;
import com.englishweb.english_web_be.dto.response.ScoreSpeakingResponse;

public interface ScoreSpeakingService {

    PostResponse addPost(PostRequest request);

    void deletePost(String id);

    ScoreSpeakingResponse scoreSpeaking(String postId, int scale, String audioProvided);
}
