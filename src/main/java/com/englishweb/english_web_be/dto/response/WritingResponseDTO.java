package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WritingResponseDTO implements BaseDTO {
    String id;
    String title;
    int serial;
    String description;
    String topic;
    String image;
    StatusEnum status;
}
