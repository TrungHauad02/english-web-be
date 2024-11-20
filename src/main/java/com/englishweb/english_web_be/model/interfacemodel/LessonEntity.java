package com.englishweb.english_web_be.model.interfacemodel;

public interface LessonEntity extends BaseEntity {
    String getTitle();

    void setTitle(String title);

    int getSerial();

    void setSerial(int serial);

    String getDescription();

    void setDescription(String description);

    String getImage();

    void setImage(String image);
}
