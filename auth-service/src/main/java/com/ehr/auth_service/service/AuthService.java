package com.ehr.auth_service.service;

import com.ehr.auth_service.dto.LoginRequestDTO;
import com.ehr.auth_service.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private  final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String>  authenticate(LoginRequestDTO loginRequestDTO){

        Optional<String> token = userService.findByEmail(loginRequestDTO.getEmail())
                .filter(user -> passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword()))
                .map(user -> jwtUtil.generateToken(user.getEmail(),user.getRole()));


    return  token;

    }

    public boolean validateToken(String token) {

        try{
            jwtUtil.validateToken(token);
            return  true;
        } catch (Exception e){
            return  false;
        }

    }
}
