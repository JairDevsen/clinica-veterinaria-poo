package com.example.demo.comunicacao.dto.response;

import java.time.LocalDate;
import java.util.List;

public record PetOwnerDTOResponse(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String cpf,
        String phoneNumber,
        String email,
        List<Long> petIds
) {
}
