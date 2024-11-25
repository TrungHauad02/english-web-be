package com.englishweb.english_web_be.dto;

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
public class UserDTO implements BaseDTO {
    String id;
    String name;
    String email;
    String password;
    String avatar;
    String contentMotivation;
    StatusEnum status;
    RoleEnum role;
    LevelEnum level;
    LocalDate startDate;
    LocalDate endDate;

    public LocalDate getStartDate() { return startDate;}

    public void setStartDate() { this.startDate = startDate;}

    public LocalDate getEndDate() { return endDate;}

    public void setEndDate(LocalDate endDate) { this.endDate = endDate;}
}
