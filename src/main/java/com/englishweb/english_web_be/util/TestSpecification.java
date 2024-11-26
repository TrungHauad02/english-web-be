package com.englishweb.english_web_be.util;

import com.englishweb.english_web_be.model.Test;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import org.springframework.data.jpa.domain.Specification;

public class TestSpecification {

    public static Specification<Test> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> title == null || title.isEmpty()
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Test> hasType(TestTypeEnum type) {
        return (root, query, criteriaBuilder) -> type == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("type"), type);

    }
}