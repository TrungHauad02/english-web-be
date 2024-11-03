package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO implements BaseDTO {
    String id;
    String name;
    String email;
    String avatar;
    String contentMotivation;
    StatusEnum status;
    RoleEnum role;
    LevelEnum level;
    LocalDate startDate;
    LocalDate endDate;

    public void setStartDate() { this.startDate = LocalDate.now();}
}
