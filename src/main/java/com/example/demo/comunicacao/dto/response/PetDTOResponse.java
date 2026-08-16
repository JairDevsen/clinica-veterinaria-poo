package com.example.demo.comunicacao.dto.response;

import java.time.LocalDate;

public record PetDTOResponse(
        Long id,
        String name,
        String species,
        String breed,
        LocalDate birthDate,
        Long ownerId
) {
}
