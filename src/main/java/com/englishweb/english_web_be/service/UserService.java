package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.request.UserRequestDTO;
import com.englishweb.english_web_be.dto.response.UserResponseDTO;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import org.springframework.data.domain.Page;

public interface UserService extends BaseService<UserRequestDTO, UserResponseDTO> {

    Page<UserResponseDTO> findByRole(RoleEnum role, int page, int size, String sortBy, String sortDir, Class<UserResponseDTO> userResponseDTOClass);

    UserResponseDTO createStudent(UserRequestDTO dto);

    UserResponseDTO createTeacher(UserRequestDTO dto);

    UserResponseDTO deleteUser(String id);

    UserResponseDTO getInfor();

    UserResponseDTO update(UserRequestDTO dto, String id);

    void sendOtpForPasswordReset(String email);

    boolean verifyOtp(String email, String inputOtp);

    void resetPassword(String email, String newPassword, String confirmPassword);
}
