package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserSpringDataJpaRepository extends JpaRepository<User, String> {
    // @Query("select u from User u where u.id = ?")
    User findUserById(String id);

}
