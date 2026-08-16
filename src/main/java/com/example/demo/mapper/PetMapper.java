package com.example.demo.mapper;

import com.example.demo.comunicacao.dto.request.PetDTORequest;
import com.example.demo.comunicacao.dto.response.PetDTOResponse;
import com.example.demo.model.Pet;
import com.example.demo.model.PetOwner;

public class PetMapper {

    public static PetDTOResponse toDTO(Pet pet) {
        if (pet == null) {
            return null;
        }
        Long ownerId = pet.getOwner() != null ? pet.getOwner().getId() : null;
        return new PetDTOResponse(pet.getId(), pet.getName(), pet.getSpecies(), pet.getBreed(), pet.getBirthDate(), ownerId);
    }

    public static Pet toEntity(PetDTORequest dto, PetOwner owner) {
        if (dto == null) {
            return null;
        }
        Pet pet = new Pet(dto.name(), dto.species(), dto.breed(), dto.birthDate());
        pet.setOwner(owner);
        return pet;
    }
}
