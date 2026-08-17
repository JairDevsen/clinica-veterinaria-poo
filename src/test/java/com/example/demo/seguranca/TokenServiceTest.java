package com.example.demo.seguranca;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
class TokenServiceTest {

    private final TokenService tokenService = new TokenService("segredo-de-teste-com-mais-de-256-bits");

    @Test
    void deveGerarTokenValidoEExtrairEmail() {
        String email = "admin@example.com";

        String token = tokenService.gerarToken(email);

        assertEquals(email, tokenService.validarToken(token));
    }

    @Test
    void deveRejeitarTokenCorrompido() {
        String tokenCorrompido = tokenService.gerarToken("admin@example.com") + "abc";

        assertNull(tokenService.validarToken(tokenCorrompido));
    }
}
