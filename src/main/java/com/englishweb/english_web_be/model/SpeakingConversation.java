package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeakingConversation implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the speaking conversation'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The name of the speaking conversation'")
    private String name;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'The serial number of the speaking conversation'")
    private int serial;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Content of the speaking conversation'")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the speaking conversation'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "speaking_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated speaking lesson for this conversation'")
    private Speaking speaking;

    @PrePersist
    private void generateId() {
        this.id = "Speak_con_" + System.nanoTime()+ "_" + new Random().nextInt(10000);
    }
}
