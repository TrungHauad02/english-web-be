package com.englishweb.english_web_be.repository.specifications;

import com.englishweb.english_web_be.model.SubmitTest;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SubmitTestSpecification {


    public static Specification<SubmitTest> hasSubmitTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return (root, query, criteriaBuilder) -> {
            if (startTime == null && endTime == null) {
                return criteriaBuilder.conjunction();
            }

            if (startTime != null && endTime != null) {
                return criteriaBuilder.between(root.get("submitTime"), startTime, endTime);
            } else if (startTime != null) {
                return criteriaBuilder.greaterThan(root.get("submitTime"), startTime);
            } else {
                return criteriaBuilder.lessThan(root.get("submitTime"), endTime);
            }
        };
    }


    public static Specification<SubmitTest> hasUserId(String userId) {
        return (root, query, criteriaBuilder) -> userId == null || userId.isEmpty()
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    public static Specification<SubmitTest> hasTestTitle(String testTitle) {
        return (root, query, criteriaBuilder) -> testTitle == null || testTitle.isEmpty()
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(root.get("test").get("title"), "%" + testTitle + "%");
    }
    public static Specification<SubmitTest> hasTestStatus(StatusEnum status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {

                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("test").get("status"), status);
        };
    }



    public static Specification<SubmitTest> hasTestType(TestTypeEnum testType) {
        return  (root, query, criteriaBuilder) -> testType == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("test").get("type"), testType);
    }
}
