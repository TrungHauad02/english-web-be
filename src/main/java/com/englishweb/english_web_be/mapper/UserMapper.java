package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.dto.request.UserRequestDTO;
import com.englishweb.english_web_be.dto.response.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<UserDTO, UserRequestDTO, UserResponseDTO> {

    @Override
    public UserDTO mapToDTO(UserRequestDTO requestDTO) {
        return UserDTO.builder()
                .id(requestDTO.getId())
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .avatar(requestDTO.getAvatar())
                .contentMotivation(requestDTO.getContentMotivation())
                .status(requestDTO.getStatus())
                .role(requestDTO.getRole())
                .level(requestDTO.getLevel())
                .startDate(requestDTO.getStartDate())
                .endDate(requestDTO.getEndDate())
                .build();
    }

    @Override
    public UserResponseDTO mapToResponseDTO(UserDTO dto) {
        return UserResponseDTO.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .avatar(dto.getAvatar())
                .contentMotivation(dto.getContentMotivation())
                .status(dto.getStatus())
                .role(dto.getRole())
                .level(dto.getLevel())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
