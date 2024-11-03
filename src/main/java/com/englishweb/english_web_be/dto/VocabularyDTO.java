package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.WordTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VocabularyDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Example cannot be empty")
    String example;
    @NotBlank(message = "Image cannot be empty")
    String image;
    @NotBlank(message = "Word cannot be empty")
    String word;
    @NotBlank(message = "Phonetic cannot be empty")
    String phonetic;
    @NotBlank(message = "Meaning cannot be empty")
    String meaning;
    WordTypeEnum wordType;
    StatusEnum status;
    @NotBlank(message = "Topic id cannot be empty")
    String topicId;
}
