package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User implements BaseEntity {

    @Id
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the User, generated based on the role'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Full name of the User'")
    private String name;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255) COMMENT 'Email address of the User'")
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Password for the User account'")
    private String password;

    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Avatar image URL for the User'")
    private String avatar;

    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Motivation content or bio of the User'")
    private String contentMotivation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the User (e.g. ACTIVE or INACTIVE)'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ADMIN', 'STUDENT', 'TEACHER') COMMENT 'Role of the User (Admin, Student, or Teacher)'")
    private RoleEnum roleEnum = RoleEnum.STUDENT;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('BACHELOR', 'DOCTOR', 'MASTER', 'PROFESSOR') COMMENT 'Level of the User (e.g. Bachelor, Doctor, Master, or Professor)'")
    private LevelEnum levelEnum;

    @Column(columnDefinition = "DATE COMMENT 'Start date of the User account'")
    private LocalDate startDate;

    @Column(columnDefinition = "DATE COMMENT 'End date of the User account (if applicable)'")
    private LocalDate endDate;

    @PrePersist
    private void generateId() {
        if (this.roleEnum == RoleEnum.TEACHER) {
            this.id = "Teacher_" + System.nanoTime();
        } else if (this.roleEnum == RoleEnum.STUDENT) {
            this.id = "Student_" + System.nanoTime();
        } else if (this.roleEnum == RoleEnum.ADMIN) {
            this.id = "Admin_" + System.nanoTime();
        }
    }
}
