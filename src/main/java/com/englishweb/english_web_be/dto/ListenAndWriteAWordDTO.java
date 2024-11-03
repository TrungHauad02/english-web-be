package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListenAndWriteAWordDTO implements BaseDTO {
    String id;
    @Positive(message = "Serial number must be greater than 0")
    int serial;
    @NotBlank(message = "Audio url cannot be empty")
    String audioUrl;
    @NotBlank(message = "Sentence cannot be empty")
    String sentence;
    @PositiveOrZero(message = "Missing index must be equal or greater than 0")
    int missingIndex;
    @NotBlank(message = "Correct answer cannot be empty")
    String correctAnswer;
    StatusEnum status;
    @NotBlank(message = "Listening id cannot be empty")
    String listeningId;
}
