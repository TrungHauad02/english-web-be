package com.englishweb.english_web_be.util;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import org.springframework.data.jpa.domain.Specification;
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
                return criteriaBuilder.conjunction(); // Không lọc theo ngày
            }

            if (searchStartDate != null && searchEndDate != null) {
                // Khi cả startDate và endDate đều có giá trị
                return criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), searchStartDate),
                        criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), searchEndDate)
                );
            } else if (searchStartDate != null) {
                // Khi chỉ có startDate
                return criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), searchStartDate);
            } else {
                // Khi chỉ có endDate
                return criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), searchEndDate);
            }
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
