package com.example.demo.comunicacao.dto.response;

import java.time.LocalDate;
import java.util.List;

public record VeterinarianDTOResponse(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String cpf,
        LocalDate employmentDate,
        String function,
        String crmv,
        String veterinarianType,
        List<Long> petIds
) {
}
