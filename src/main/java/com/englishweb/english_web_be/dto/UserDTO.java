package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;

public class UserDTO implements BaseDTO{
    String id;
    @NotBlank(message = "Name cannot be empty")
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

    public UserDTO() {}

    public UserDTO(String id, String name, String email, String password, String avatar, String contentMotivation, StatusEnum status, RoleEnum role, LevelEnum level) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.contentMotivation = contentMotivation;
        this.status = status;
        this.role = role;
        this.level = level;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContentMotivation() {
        return contentMotivation;
    }

    public void setContentMotivation(String contentMotivation) {
        this.contentMotivation = contentMotivation;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }
}
