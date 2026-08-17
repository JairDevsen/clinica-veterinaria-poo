package com.example.demo.comunicacao.controller;

import com.example.demo.comunicacao.dto.request.LoginDTORequest;
import com.example.demo.comunicacao.dto.response.TokenDTOResponse;
import com.example.demo.seguranca.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTOResponse> login(@Valid @RequestBody LoginDTORequest loginDTORequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginDTORequest.email(),
                    loginDTORequest.senha()
            );
            authenticationManager.authenticate(authenticationToken);

            String token = tokenService.gerarToken(loginDTORequest.email());
            return ResponseEntity.ok(new TokenDTOResponse(token));
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
