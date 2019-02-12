package com.library.service;

import com.library.exception.AppException;
import com.library.exception.BadRequestException;
import com.library.model.client.request.LoginRequest;
import com.library.model.client.request.RegisterRequest;
import com.library.model.client.response.JwtAuthenticationResponse;
import com.library.model.server.Role;
import com.library.model.server.User;
import com.library.model.server.enums.RoleName;
import com.library.repository.RoleRepository;
import com.library.repository.UserRepository;
import com.library.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.Collections.singleton;
import static java.util.Objects.requireNonNull;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = requireNonNull(userRepository, "userRepository cannot be null");
        this.authenticationManager = requireNonNull(authenticationManager, "authenticationManager cannot be null");
        this.tokenProvider = requireNonNull(tokenProvider, "tokenProvider cannot be null");
        this.roleRepository = requireNonNull(roleRepository, "roleRepository cannot be null");
        this.passwordEncoder = requireNonNull(passwordEncoder, "passwordEncoder cannot be null");
    }

    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        return new JwtAuthenticationResponse()
                .setAccessToken(getToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
    }

    public JwtAuthenticationResponse register(RegisterRequest registerRequest) {
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(
                () -> new AppException("User Role not in database"));

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new BadRequestException("Username is already in use");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BadRequestException("Email Address already in use");
        }

        Set<Role> roles = singleton(userRole);

        User user = new User()
                .setName(registerRequest.getName())
                .setSurname(registerRequest.getSurname())
                .setUsername(registerRequest.getUsername())
                .setEmail(registerRequest.getEmail())
                .setPassword(passwordEncoder.encode(registerRequest.getPassword()))
                .setPhone(registerRequest.getPhone())
                .setRoles(roles);

        userRepository.save(user);
        return new JwtAuthenticationResponse()
                .setAccessToken(getToken(registerRequest.getEmail(), registerRequest.getPassword()));
    }

    private String getToken(String usernameOrEmail, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usernameOrEmail, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);
    }
}
