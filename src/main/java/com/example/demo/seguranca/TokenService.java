package com.example.demo.seguranca;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    private static final long VALIDADE_EM_HORAS = 2;

    private final SecretKey chaveAssinatura;

    public TokenService(@Value("${api.security.token.secret:chave-padrao-jwt-com-pelo-menos-256-bits}") String segredo) {
        this.chaveAssinatura = Keys.hmacShaKeyFor(segredo.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(String email) {
        Instant agora = Instant.now();

        return Jwts.builder()
                .subject(email)
                .issuedAt(Date.from(agora))
                .expiration(Date.from(agora.plus(VALIDADE_EM_HORAS, ChronoUnit.HOURS)))
                .signWith(chaveAssinatura)
                .compact();
    }

    public String validarToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(chaveAssinatura)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException exception) {
            return null;
        }
    }
}
