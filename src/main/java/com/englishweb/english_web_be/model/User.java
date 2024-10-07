package com.englishweb.english_web_be.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    String id;
    @Column(name = "name")
    String name;
    String email;
    String password;
    String avatar;
    String contentMotivation;
    StatusEnum statusEnum;
    RoleEnum roleEnum;
    LevelEnum levelEnum;
    public User() {
    }

    public User(String id, String name, String email, String password, String avatar, String contentMotivation, StatusEnum statusEnum, RoleEnum roleEnum, LevelEnum levelEnum) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.contentMotivation = contentMotivation;
        this.statusEnum = statusEnum;
        this.roleEnum = roleEnum;
        this.levelEnum = levelEnum;
    }

    public String getId() {
        return id;
    }

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

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public LevelEnum getLevelEnum() {
        return levelEnum;
    }

    public void setLevelEnum(LevelEnum levelEnum) {
        this.levelEnum = levelEnum;
    }
}
