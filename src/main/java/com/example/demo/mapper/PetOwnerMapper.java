package com.example.demo.mapper;

import com.example.demo.comunicacao.dto.request.PetOwnerDTORequest;
import com.example.demo.comunicacao.dto.response.PetOwnerDTOResponse;
import com.example.demo.model.PetOwner;

import java.util.List;
import java.util.stream.Collectors;

public class PetOwnerMapper {

    public static PetOwnerDTOResponse toDTO(PetOwner owner) {
        if (owner == null) {
            return null;
        }
        List<Long> petIds = owner.getPets().stream()
                .map(pet -> pet.getId())
                .collect(Collectors.toList());
        return new PetOwnerDTOResponse(owner.getId(), owner.getFirstName(), owner.getLastName(), owner.getBirthDate(), owner.getCpf(), owner.getPhoneNumber(), owner.getEmail(), petIds);
    }

    public static PetOwner toEntity(PetOwnerDTORequest dto) {
        if (dto == null) {
            return null;
        }
        return new PetOwner(dto.firstName(), dto.lastName(), dto.birthDate(), dto.cpf(), dto.phoneNumber(), dto.email());
    }
}
