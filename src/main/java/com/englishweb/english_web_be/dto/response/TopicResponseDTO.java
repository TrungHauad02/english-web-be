package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicResponseDTO implements BaseDTO {
    private String id;
    private String title;
    private int serial;
    private String image;
    private String description;
    private StatusEnum status;
}
