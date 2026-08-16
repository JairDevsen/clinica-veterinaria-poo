package com.example.demo.comunicacao.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record PetDTORequest(
        @NotBlank(message = "O nome do pet é obrigatório") String name,
        @NotBlank(message = "A espécie do pet é obrigatória") String species,
        @NotBlank(message = "A raça do pet é obrigatória") String breed,
        @NotNull(message = "A data de nascimento é obrigatória") LocalDate birthDate,
        @Positive(message = "O identificador do proprietário deve ser positivo") Long ownerId
) {
}
