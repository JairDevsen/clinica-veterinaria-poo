package com.example.demo.comunicacao.conversor;

import com.example.demo.comunicacao.dto.request.PetOwnerDTORequest;
import com.example.demo.comunicacao.dto.response.PetOwnerDTOResponse;
import com.example.demo.model.PetOwner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetOwnerConversor {

    public PetOwner requestToEntity(PetOwnerDTORequest request) {
        if (request == null) {
            return null;
        }

        PetOwner owner = new PetOwner();
        owner.setFirstName(request.firstName());
        owner.setLastName(request.lastName());
        owner.setBirthDate(request.birthDate());
        owner.setCpf(request.cpf());
        owner.setPhoneNumber(request.phoneNumber());
        owner.setEmail(request.email());
        return owner;
    }

    public PetOwnerDTOResponse entityToResponse(PetOwner owner) {
        if (owner == null) {
            return null;
        }

        List<Long> petIds = owner.getPets() == null ? List.of() : owner.getPets().stream()
                .map(pet -> pet.getId())
                .collect(Collectors.toList());

        return new PetOwnerDTOResponse(
                owner.getId(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getBirthDate(),
                owner.getCpf(),
                owner.getPhoneNumber(),
                owner.getEmail(),
                petIds
        );
    }
}
