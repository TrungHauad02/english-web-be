package com.englishweb.english_web_be.dto.request;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO implements BaseDTO {
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

    public void setStartDate() { this.startDate = LocalDate.now();}
}
