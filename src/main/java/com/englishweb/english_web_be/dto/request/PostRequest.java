package com.englishweb.english_web_be.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {
    @JsonProperty("post_language_id")
    private String postLanguageId;
    @JsonProperty("post_title")
    private String postTitle;
    @JsonProperty("post_content")
    private String postContent;
}
