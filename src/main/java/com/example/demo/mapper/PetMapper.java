package com.example.demo.mapper;

import com.example.demo.dto.PetDTO;
import com.example.demo.model.Pet;
import com.example.demo.model.PetOwner;

public class PetMapper {

    public static PetDTO toDTO(Pet pet) {
        if (pet == null) {
            return null;
        }
        Long ownerId = pet.getOwner() != null ? pet.getOwner().getId() : null;
        return new PetDTO(pet.getId(), pet.getName(), pet.getSpecies(), pet.getBreed(), pet.getBirthDate(), ownerId);
    }

    public static Pet toEntity(PetDTO dto, PetOwner owner) {
        if (dto == null) {
            return null;
        }
        Pet pet = new Pet(dto.getName(), dto.getSpecies(), dto.getBreed(), dto.getBirthDate());
        pet.setId(dto.getId());
        pet.setOwner(owner);
        return pet;
    }
}
