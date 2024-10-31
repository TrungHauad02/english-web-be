package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.UserDTO;

public interface UserService extends BaseService<UserDTO> {

    UserDTO createStudent(UserDTO dto);

    UserDTO createTeacher(UserDTO dto);

    UserDTO deleteUser(String id);
}
