package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.dto.request.UserRequestDTO;
import com.englishweb.english_web_be.dto.response.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<UserDTO, UserRequestDTO, UserResponseDTO> {

    @Override
    public UserDTO mapToDTO(UserRequestDTO requestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO mapToResponseDTO(UserDTO dto) {
        return null;
    }
}
