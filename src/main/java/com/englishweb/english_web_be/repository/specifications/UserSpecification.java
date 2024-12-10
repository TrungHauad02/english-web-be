package com.englishweb.english_web_be.repository.specifications;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class UserSpecification {

    public static Specification<User> hasName(String name) {
        return (root, query, criteriaBuilder) -> name == null || name.isEmpty()
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<User> hasRole(RoleEnum role) {
        return (root, query, criteriaBuilder) -> role == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("roleEnum"), role);
    }

    public static Specification<User> hasDateRange(LocalDate searchStartDate, LocalDate searchEndDate) {
        return (root, query, criteriaBuilder) -> {
            if (searchStartDate == null && searchEndDate == null) {
                return criteriaBuilder.conjunction();
            }

            // Dùng danh sách các điều kiện để kiểm tra
            List<Predicate> predicates = new ArrayList<>();

            if (searchStartDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), searchStartDate));
            }

            if (searchEndDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), searchEndDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    public static Specification<User> hasLevel(LevelEnum level) {
        return (root, query, criteriaBuilder) -> {
            if (level == null) {
                return criteriaBuilder.conjunction(); // Bỏ qua lọc level nếu là null
            }
            return criteriaBuilder.equal(root.get("levelEnum"), level); // Sử dụng levelEnum thay vì level
        };
    }
}
