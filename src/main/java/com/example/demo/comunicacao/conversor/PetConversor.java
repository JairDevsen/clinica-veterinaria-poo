package com.example.demo.comunicacao.conversor;

import com.example.demo.comunicacao.dto.request.PetDTORequest;
import com.example.demo.comunicacao.dto.response.PetDTOResponse;
import com.example.demo.model.Pet;
import com.example.demo.model.PetOwner;
import org.springframework.stereotype.Component;

@Component
public class PetConversor {

    public Pet requestToEntity(PetDTORequest request) {
        if (request == null) {
            return null;
        }

        Pet pet = new Pet();
        pet.setName(request.name());
        pet.setSpecies(request.species());
        pet.setBreed(request.breed());
        pet.setBirthDate(request.birthDate());

        if (request.ownerId() != null) {
            PetOwner owner = new PetOwner();
            owner.setId(request.ownerId());
            pet.setOwner(owner);
        }

        return pet;
    }

    public PetDTOResponse entityToResponse(Pet pet) {
        if (pet == null) {
            return null;
        }

        Long ownerId = pet.getOwner() != null ? pet.getOwner().getId() : null;
        return new PetDTOResponse(
                pet.getId(),
                pet.getName(),
                pet.getSpecies(),
                pet.getBreed(),
                pet.getBirthDate(),
                ownerId
        );
    }
}
