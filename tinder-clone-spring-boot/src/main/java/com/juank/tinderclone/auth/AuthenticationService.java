package com.juank.tinderclone.auth;

import com.juank.tinderclone.config.JwtService;
import com.juank.tinderclone.model.Role;
import com.juank.tinderclone.model.User;
import com.juank.tinderclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .matches(new ArrayList<>())
                .hashedpassword(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        String userId = repository.save(user).getUser_id();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .userId(userId)
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .userId(user.getUser_id())
                .token(jwtToken)
                .build();

    }
}
