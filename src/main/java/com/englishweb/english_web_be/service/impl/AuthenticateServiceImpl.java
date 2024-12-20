package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.exception.AuthenticationException;
import com.englishweb.english_web_be.model.Authenticate;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.UserRepository;
import com.englishweb.english_web_be.security.IntrospecRequest;
import com.englishweb.english_web_be.security.IntrospecResponse;
import com.englishweb.english_web_be.service.AuthenticateService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;

@Service
public class AuthenticateServiceImpl extends BaseServiceImpl<User, UserDTO, UserRepository> implements AuthenticateService {
    private final PasswordEncoder passwordEncoder;

    @NonFinal
    protected static final String SIGNER_KEY = "1TjXchw5FloESb63Kc+DFhTARvpWL4jUGCwfGWxuG5SIf/1y/LgJxHnMqaF6A/ij";

    public IntrospecResponse introspec(IntrospecRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean isVerified = signedJWT.verify(verifier);

        // Kiểm tra tính hợp lệ của token
        boolean isValid = isVerified && expiryTime != null && expiryTime.after(new Date());

        return new IntrospecResponse(isValid); // Trả về đối tượng IntrospecResponse với true hoặc false
    }

    public AuthenticateServiceImpl(UserRepository repository) {
        super(repository);
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    UserRepository repository;
    public Authenticate authenticate(UserDTO dto) {
        Optional<User> userOptional = repository.findByEmail(dto.getEmail());

        if (userOptional.isEmpty()) {
            throw new AuthenticationException( "Email is invalid. Please check again.");
        }

        User user = userOptional.get();
        if (user.getStatus() == StatusEnum.INACTIVE) {
            throw new AuthenticationException("Account is inactive. Please contact support.");
        }

        boolean isAuthenticated = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!isAuthenticated) {
            throw new AuthenticationException("Password is invalid. Please check again.");
        }
        String id = user.getId();
        String role = user.getRoleEnum().name();
        var token = generateToken(user);
        return new Authenticate(isAuthenticated, token, role, id);
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("EnglishWebApplication.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(3, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(user))
                .claim("id", user.getId())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        // Kiểm tra nếu roleEnum không null thì thêm vào StringJoiner
        if (user.getRoleEnum() != null) {
            stringJoiner.add(user.getRoleEnum().name()); // Lấy tên của RoleEnum và thêm vào chuỗi
        }

        return stringJoiner.toString();
    }

    public UserDTO signIn(UserDTO dto) {
        Optional<User> userOptional = repository.findByEmail(dto.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Email or password is invalid. Please check again.");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email or password is invalid. Please check again.");
        }

        if (user.getStatus() != StatusEnum.ACTIVE) {
            throw new RuntimeException("Account is not active. Please contact support.");
        }

        return convertToDTO(user);
    }

    @Override
    protected User convertToEntity(UserDTO dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setAvatar(dto.getAvatar());
        entity.setContentMotivation(dto.getContentMotivation());
        entity.setRoleEnum(dto.getRole());
        entity.setStatus(dto.getStatus());
        entity.setLevelEnum(dto.getLevel());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        return entity;
    }

    @Override
    protected UserDTO convertToDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setAvatar(entity.getAvatar());
        dto.setContentMotivation(entity.getContentMotivation());
        dto.setRole(entity.getRoleEnum());
        dto.setStatus(entity.getStatus());
        dto.setLevel(entity.getLevelEnum());
        dto.setStartDate();
        dto.setEndDate(entity.getEndDate());
        return dto;
    }
}
