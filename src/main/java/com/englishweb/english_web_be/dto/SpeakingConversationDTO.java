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
public class SpeakingConversationDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Name cannot be empty")
    String name;
    @Positive(message = "Serial number must be greater than 0")
    int serial;
    @NotBlank(message = "Content cannot be empty")
    String content;
    StatusEnum status;
    @NotBlank(message = "Speaking id cannot be empty")
    String speakingId;
}
