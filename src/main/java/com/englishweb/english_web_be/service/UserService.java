package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import org.springframework.data.domain.Page;

public interface UserService extends BaseService<UserDTO> {

    Page<UserDTO> findByRole(RoleEnum role, int page, int size, String sortBy, String sortDir, Class<UserDTO> userResponseDTOClass);

    UserDTO createStudent(UserDTO dto);

    UserDTO createTeacher(UserDTO dto);

//    UserResponseDTO deleteUser(String id);

    UserDTO getInfor();

    UserDTO update(UserDTO dto, String id);

    void sendOtpForPasswordReset(String email);

    boolean verifyOtp(String email, String inputOtp);

    void resetPassword(String email, String newPassword, String confirmPassword);
}
