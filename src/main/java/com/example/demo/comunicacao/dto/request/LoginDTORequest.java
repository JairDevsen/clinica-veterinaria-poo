package com.example.demo.comunicacao.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTORequest(
        @NotBlank(message = "O email e obrigatorio")
        @Email(message = "O email deve ser valido")
        String email,

        @NotBlank(message = "A senha e obrigatoria")
        String senha
) {
}
