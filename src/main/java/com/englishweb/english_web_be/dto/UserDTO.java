package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements BaseDTO {
    String id;
    String name;
    @NotBlank(message = "Email cannot be empty")
    String email;
    @NotBlank(message = "Password cannot be empty")
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
