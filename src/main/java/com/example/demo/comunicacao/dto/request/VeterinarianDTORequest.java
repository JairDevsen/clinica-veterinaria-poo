package com.example.demo.comunicacao.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record VeterinarianDTORequest(
        @NotBlank(message = "O nome do veterinário é obrigatório") String firstName,
        @NotBlank(message = "O sobrenome do veterinário é obrigatório") String lastName,
        @NotNull(message = "A data de nascimento é obrigatória") LocalDate birthDate,
        @NotBlank(message = "O CPF é obrigatório") String cpf,
        @NotNull(message = "A data de contratação é obrigatória") LocalDate employmentDate,
        @NotBlank(message = "A função do veterinário é obrigatória") String function,
        @NotBlank(message = "O CRMV é obrigatório") String crmv,
        @NotBlank(message = "O tipo do veterinário é obrigatório") String veterinarianType
) {
}
