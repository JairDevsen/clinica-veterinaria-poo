package com.example.demo.comunicacao.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record PetOwnerDTORequest(
        @NotBlank(message = "O nome do proprietário é obrigatório") String firstName,
        @NotBlank(message = "O sobrenome do proprietário é obrigatório") String lastName,
        @NotNull(message = "A data de nascimento é obrigatória") LocalDate birthDate,
        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos") String cpf,
        @NotBlank(message = "O telefone é obrigatório") String phoneNumber,
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail deve ser válido") String email
) {
}
