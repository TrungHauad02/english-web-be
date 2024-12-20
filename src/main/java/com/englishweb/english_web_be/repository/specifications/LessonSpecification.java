package com.englishweb.english_web_be.repository.specifications;

import com.englishweb.english_web_be.model.interfacemodel.LessonEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.jpa.domain.Specification;

public class LessonSpecification{
    public static <T extends LessonEntity> Specification<T> byTitle(String title){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static <T extends LessonEntity> Specification<T> hasStatus(StatusEnum status){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }
}
