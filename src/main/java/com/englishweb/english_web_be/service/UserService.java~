package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import org.springframework.data.domain.Page;

public interface UserService extends BaseService<UserDTO> {

    UserDTO createStudent(UserDTO dto);

    UserDTO createTeacher(UserDTO dto);

    UserDTO deleteUser(String id);

    UserDTO getInfor();

    UserDTO update(UserDTO dto, String id);

    Page<UserDTO> findByRole(int page, int size, String sortBy, String sortDir, RoleEnum role, Class<UserDTO> dtoClass);

    void sendOtpByEmail(UserDTO userDTO);

    boolean verifyOtp(String email, String otp);

    UserDTO resetPassword(UserDTO userDTO);
}
