package com.englishweb.english_web_be.repository.specifications;

import com.englishweb.english_web_be.model.Vocabulary;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.jpa.domain.Specification;

public class VocabularySpecification {
    public static Specification<Vocabulary> byWord(String word) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("word"), "%" + word + "%");
    }

    public static Specification<Vocabulary> byStatus(StatusEnum status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Vocabulary> byTopicId(String topicId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("topic").get("id"), topicId);
    }
}
