package com.library.controller;

import com.library.model.client.request.LoginRequest;
import com.library.model.client.request.RegisterRequest;
import com.library.model.client.response.JwtAuthenticationResponse;
import com.library.service.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import static com.library.utils.URLPaths.loginPath;
import static com.library.utils.URLPaths.registerPath;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(loginPath)
    public @ResponseBody
    JwtAuthenticationResponse signIn(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping(registerPath)
    public @ResponseBody
    JwtAuthenticationResponse signUp(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

}
