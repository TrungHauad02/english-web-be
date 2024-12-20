package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

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
    public ResponseEntity<Page<UserDTO>> findByPage(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                    @RequestParam(defaultValue = "asc") String sortDir) {
        var authenticate = SecurityContextHolder.getContext().getAuthentication();
        log.info("email: {}", authenticate.getName());
        log.info("Role: {}", authenticate.getAuthorities().toString());
        return new ResponseEntity<>(userService.findByPage(page, size, sortBy, sortDir, UserDTO.class), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get paginated list of teachers",
            description = "Send a request via this API to get a paginated list of teachers")
    @GetMapping("/teachers")
    public ResponseEntity<Page<UserDTO>> findTeachersBySpecification(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) LevelEnum level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<UserDTO> result = userService.findTeachersBySpecification(name, startDate, endDate, level, page, size);
        return ResponseEntity.ok(result);
    }

    @Operation(method = "GET", summary = "Get paginated list of students",
            description = "Send a request via this API to get a paginated list of students")
    @GetMapping("/students")
    public ResponseEntity<Page<UserDTO>> findStudentsBySpecification(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<UserDTO> result = userService.findStudentsBySpecification(name, startDate, endDate, page, size);
        return ResponseEntity.ok(result);
    }

    @Operation(method = "GET", summary = "Get user by ID",
            description = "Send a request via this API to get a user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new user",
            description = "Send a request via this API to create a new user")
    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
    }

    @Operation(method = "DELETE", summary = "Delete user",
            description = "Send a request via this API to delete a user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(method = "PATCH", summary = "Update user",
            description = "Send a request via this API to update an existing user")
    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO, @PathVariable String id) {
        return new ResponseEntity<>(userService.update(userDTO, id), HttpStatus.OK);
    }

    @PatchMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable String id, @RequestBody Map<String, String> passwordData) {
        String oldPassword = passwordData.get("oldPassword");
        String newPassword = passwordData.get("newPassword");
        userService.changePassword(id, oldPassword, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new student user",
            description = "Send a request via this API to create a new student user")
    @PostMapping("/student/signup")
    public ResponseEntity<UserDTO> createStudent(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createStudent(userDTO), HttpStatus.CREATED);
    }

    @Operation(method = "POST", summary = "Create new teacher user",
            description = "Send a request via this API to create a new teacher user")
    @PostMapping("/teacher/signup")
    public ResponseEntity<UserDTO> createTeacher(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createTeacher(userDTO), HttpStatus.CREATED);
    }

    @Operation(method = "GET", summary = "Get current user information",
            description = "Send a request via this API to get the current user's information")
    @GetMapping("/myinfo")
    public ResponseEntity<UserDTO> myInfor() {
        return new ResponseEntity<>(userService.getInfor(), HttpStatus.OK);
    }

    @PostMapping("/forgot-password/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, String> request) {
            String email = request.get("email");
            userService.sendOtpForPasswordReset(email);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/forgot-password/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String otp = request.get("otp");
            if (userService.verifyOtp(email, otp)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/forgot-password/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String newPassword = request.get("newPassword");
            String confirmPassword = request.get("confirmPassword");

            userService.resetPassword(email, newPassword, confirmPassword);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
