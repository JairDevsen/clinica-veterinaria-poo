package com.example.demo.mapper;

import com.example.demo.comunicacao.dto.request.VeterinarianDTORequest;
import com.example.demo.comunicacao.dto.response.VeterinarianDTOResponse;
import com.example.demo.model.Pet;
import com.example.demo.model.Veterinarian;

import java.util.List;
import java.util.stream.Collectors;

public class VeterinarianMapper {

    public static VeterinarianDTOResponse toDTO(Veterinarian veterinarian) {
        if (veterinarian == null) {
            return null;
        }
        List<Long> petIds = veterinarian.getPets().stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
        return new VeterinarianDTOResponse(veterinarian.getId(), veterinarian.getFirstName(), veterinarian.getLastName(), veterinarian.getBirthDate(), veterinarian.getCpf(), veterinarian.getEmploymentDate(), veterinarian.getFunction(), veterinarian.getCrmv(), veterinarian.getVeterinarianType(), petIds);
    }

    public static Veterinarian toEntity(VeterinarianDTORequest dto) {
        if (dto == null) {
            return null;
        }
        return new Veterinarian(dto.firstName(), dto.lastName(), dto.birthDate(), dto.cpf(), dto.employmentDate(), dto.function(), dto.crmv(), dto.veterinarianType());
    }
}
