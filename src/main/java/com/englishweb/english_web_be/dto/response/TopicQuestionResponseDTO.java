package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicQuestionResponseDTO implements BaseDTO {
    String id;
    String content;
    int serial;
    String explanation;
    StatusEnum status;
    List<TopicAnswerResponseDTO> answers;
    String topicId;
}
