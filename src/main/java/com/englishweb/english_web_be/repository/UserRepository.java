package com.englishweb.english_web_be.repository;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);
    Page<User> findByRoleEnum(RoleEnum role, Pageable pageable);
}
