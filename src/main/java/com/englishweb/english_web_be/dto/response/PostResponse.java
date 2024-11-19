package com.englishweb.english_web_be.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.ZonedDateTime;

@Getter
@Setter
public class PostResponse {
    @JsonProperty("ai_reading")
    private URL aiReading;

    @JsonProperty("date_posted")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss z", locale = "en")
    private ZonedDateTime datePosted;

    @JsonProperty("post_content")
    private String postContent;

    @JsonProperty("post_id")
    private String postId;

    @JsonProperty("post_language_id")
    private String postLanguageId;

    @JsonProperty("post_language_name")
    private String postLanguageName;

    @JsonProperty("post_language_voice")
    private String postLanguageVoice;

    @JsonProperty("post_source")
    private String postSource;

    @JsonProperty("post_title")
    private String postTitle;
}
