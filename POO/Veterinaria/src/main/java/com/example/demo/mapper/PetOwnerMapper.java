package com.example.demo.mapper;

import com.example.demo.dto.PetDTO;
import com.example.demo.dto.PetOwnerDTO;
import com.example.demo.model.Pet;
import com.example.demo.model.PetOwner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PetOwnerMapper {

    public static PetOwnerDTO toDTO(PetOwner owner) {
        if (owner == null) {
            return null;
        }
        List<PetDTO> pets = owner.getPets().stream()
                .map(PetMapper::toDTO)
                .collect(Collectors.toList());
        return new PetOwnerDTO(owner.getId(), owner.getFirstName(), owner.getLastName(), owner.getBirthDate(), owner.getCpf(), owner.getPhoneNumber(), owner.getEmail(), pets);
    }

    public static PetOwner toEntity(PetOwnerDTO dto) {
        if (dto == null) {
            return null;
        }
        PetOwner owner = new PetOwner(dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), dto.getCpf(), dto.getPhoneNumber(), dto.getEmail());
        owner.setId(dto.getId());
        for (PetDTO petDTO : petsDoDto(dto)) {
            Pet pet = PetMapper.toEntity(petDTO, owner);
            owner.addPet(pet);
        }
        return owner;
    }

    private static List<PetDTO> petsDoDto(PetOwnerDTO dto) {
        return dto.getPets() == null ? Collections.emptyList() : dto.getPets();
    }
}
