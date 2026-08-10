package com.example.demo.mapper;

import com.example.demo.dto.VeterinarianDTO;
import com.example.demo.model.Pet;
import com.example.demo.model.Veterinarian;

import java.util.List;
import java.util.stream.Collectors;

public class VeterinarianMapper {

    public static VeterinarianDTO toDTO(Veterinarian veterinarian) {
        if (veterinarian == null) {
            return null;
        }
        List<Long> petIds = veterinarian.getPets().stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
        return new VeterinarianDTO(veterinarian.getId(), veterinarian.getFirstName(), veterinarian.getLastName(), veterinarian.getBirthDate(), veterinarian.getCpf(), veterinarian.getEmploymentDate(), veterinarian.getFunction(), veterinarian.getCrmv(), veterinarian.getVeterinarianType(), petIds);
    }

    public static Veterinarian toEntity(VeterinarianDTO dto) {
        if (dto == null) {
            return null;
        }
        Veterinarian veterinarian = new Veterinarian(dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), dto.getCpf(), dto.getEmploymentDate(), dto.getFunction(), dto.getCrmv(), dto.getVeterinarianType());
        veterinarian.setId(dto.getId());
        return veterinarian;
    }
}
