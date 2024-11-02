package com.englishweb.english_web_be.dto.request;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.WordTypeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyRequestDTO implements BaseDTO {
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
