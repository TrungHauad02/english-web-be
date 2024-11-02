package com.englishweb.english_web_be.dto.request;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicRequestDTO {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @Positive(message = "Serial number must be greater than 0")
    private int serial;
    @NotBlank(message = "Image cannot be empty")
    private String image;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    private StatusEnum status;
}
