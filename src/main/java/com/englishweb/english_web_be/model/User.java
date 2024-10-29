package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "USERS")
public class User implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String avatar;
    private String contentMotivation;
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum = StatusEnum.ACTIVE;
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum = RoleEnum.STUDENT;
    @Enumerated(EnumType.STRING)
    private LevelEnum levelEnum;
    private LocalDate startDate;
    private LocalDate endDate;

    @PrePersist
    private void generateId() {
        if (this.roleEnum == RoleEnum.TEACHER) {
            this.id = "Teacher_" + System.currentTimeMillis();
        } else if (this.roleEnum == RoleEnum.STUDENT) {
            this.id = "Student_" + System.currentTimeMillis();
        }
    }

    public User() {
    }

    public User(String id, String name, String email, String password, String avatar, String contentMotivation, StatusEnum statusEnum, RoleEnum roleEnum, LevelEnum levelEnum, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.contentMotivation = contentMotivation;
        this.statusEnum = statusEnum;
        this.roleEnum = roleEnum;
        this.levelEnum = levelEnum;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
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

    public LocalDate getStartDate() {return startDate;}

    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    public LocalDate getEndDate() {return endDate;}

    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}
}
