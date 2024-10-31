package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicQuestionDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Content cannot be empty")
    String content;
    @Positive(message = "Serial number must be greater than 0")
    int serial;
    @NotBlank(message = "Explanation cannot be empty")
    String explanation;
    StatusEnum status;
    List<TopicAnswerDTO> answers;
    @NotBlank(message = "Topic id cannot be empty")
    String topicId;
}
