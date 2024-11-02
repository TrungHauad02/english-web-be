package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.UserRequestDTO;
import com.englishweb.english_web_be.dto.response.UserResponseDTO;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(method = "GET", summary = "Get paginated list of users",
            description = "Send a request via this API to get a paginated list of users")
    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> findByPage(@RequestParam int page,
                                                            @RequestParam int size,
                                                            @RequestParam(defaultValue = "id") String sortBy,
                                                            @RequestParam(defaultValue = "asc") String sortDir) {
        var authenticate = SecurityContextHolder.getContext().getAuthentication();
        log.info("email: {}", authenticate.getName());
        log.info("Role: {}", authenticate.getAuthorities().toString());
        return new ResponseEntity<>(userService.findByPage(page, size, sortBy, sortDir, UserResponseDTO.class), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get paginated list of teachers",
            description = "Send a request via this API to get a paginated list of teachers")
    @GetMapping("/teachers")
    public ResponseEntity<Page<UserResponseDTO>> findTeachersByPage(@RequestParam int page,
                                                                    @RequestParam int size,
                                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                                    @RequestParam(defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(userService.findByRole(RoleEnum.TEACHER, page, size, sortBy, sortDir , UserResponseDTO.class), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get paginated list of students",
            description = "Send a request via this API to get a paginated list of students")
    @GetMapping("/students")
    public ResponseEntity<Page<UserResponseDTO>> findStudentsByPage(@RequestParam int page,
                                                                    @RequestParam int size,
                                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                                    @RequestParam(defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(userService.findByRole(RoleEnum.STUDENT, page, size, sortBy, sortDir, UserResponseDTO.class), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get user by ID",
            description = "Send a request via this API to get a user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new user",
            description = "Send a request via this API to create a new user")
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO userDTO) {
        UserResponseDTO createdUser = userService.create(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @Operation(method = "DELETE", summary = "Delete user",
            description = "Send a request via this API to delete a user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(method = "PUT", summary = "Update user",
            description = "Send a request via this API to update an existing user")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@Valid @RequestBody UserRequestDTO userDTO, @PathVariable String id) {
        return new ResponseEntity<>(userService.update(userDTO, id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new student user",
            description = "Send a request via this API to create a new student user")
    @PostMapping("/student/signup")
    public ResponseEntity<UserResponseDTO> createStudent(@Valid @RequestBody UserRequestDTO userDTO) {
        return new ResponseEntity<>(userService.createStudent(userDTO), HttpStatus.CREATED);
    }

    @Operation(method = "POST", summary = "Create new teacher user",
            description = "Send a request via this API to create a new teacher user")
    @PostMapping("/teacher/signup")
    public ResponseEntity<UserResponseDTO> createTeacher(@Valid @RequestBody UserRequestDTO userDTO) {
        return new ResponseEntity<>(userService.createTeacher(userDTO), HttpStatus.CREATED);
    }

    @Operation(method = "GET", summary = "Get current user information",
            description = "Send a request via this API to get the current user's information")
    @GetMapping("/myinfor")
    public ResponseEntity<UserResponseDTO> myInfor() {
        return new ResponseEntity<>(userService.getInfor(), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Send OTP for password reset",
            description = "Send a request via this API to send an OTP for password reset")
    @PostMapping("/forgot-password/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody String email) {
        try {
            UserRequestDTO userDTO = new UserRequestDTO();
            userDTO.setEmail(email);
            userService.sendOtpByEmail(userDTO);
            return new ResponseEntity<>("Mã OTP đã được gửi thành công. Vui lòng kiểm tra email của bạn.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(method = "POST", summary = "Verify OTP for password reset",
            description = "Send a request via this API to verify the OTP for password reset")
    @PostMapping("/forgot-password/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isOtpValid = userService.verifyOtp(email, otp);
        if (isOtpValid) {
            return new ResponseEntity<>("Mã OTP hợp lệ.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mã OTP không hợp lệ hoặc đã hết hạn.", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(method = "POST", summary = "Reset password using OTP",
            description = "Send a request via this API to reset the password using an OTP")
    @PostMapping("/forgot-password/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody UserRequestDTO userDTO) {
        try {
            userService.resetPassword(userDTO);
            return new ResponseEntity<>("Mật khẩu đã được đặt lại thành công.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
