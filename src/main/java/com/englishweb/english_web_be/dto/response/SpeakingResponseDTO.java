package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeakingResponseDTO implements BaseDTO {
    String id;
    String title;
    int serial;
    String description;
    String image;
    String topic;
    int duration;
    StatusEnum status;
}
