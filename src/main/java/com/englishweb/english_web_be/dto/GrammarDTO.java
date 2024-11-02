package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrammarDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Title cannot be empty")
    String title;
    @Positive(message = "Serial number must be greater than 0")
    int serial;
    @NotBlank(message = "Content cannot be empty")
    String content;
    @NotBlank(message = "Description cannot be empty")
    String description;
    @NotBlank(message = "Image cannot be empty")
    String image;
    @NotBlank(message = "Example cannot be empty")
    String example;
    @NotBlank(message = "File cannot be empty")
    String file;
    StatusEnum status;
}
