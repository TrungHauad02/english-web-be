package com.englishweb.english_web_be.model;

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
            this.id = "Teacher_" + System.nanoTime();
        } else if (this.roleEnum == RoleEnum.STUDENT) {
            this.id = "Student_" + System.nanoTime();
        }
    }
}
