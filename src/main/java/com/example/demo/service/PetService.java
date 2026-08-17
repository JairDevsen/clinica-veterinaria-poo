package com.example.demo.service;

import com.example.demo.comunicacao.conversor.PetConversor;
import com.example.demo.comunicacao.dto.request.PetDTORequest;
import com.example.demo.comunicacao.dto.response.PetDTOResponse;
import com.example.demo.model.Pet;
import com.example.demo.model.PetOwner;
import com.example.demo.repository.PetOwnerRepository;
import com.example.demo.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final PetOwnerRepository petOwnerRepository;
    private final PetConversor petConversor;

    public PetService(PetRepository petRepository, PetOwnerRepository petOwnerRepository, PetConversor petConversor) {
        this.petRepository = petRepository;
        this.petOwnerRepository = petOwnerRepository;
        this.petConversor = petConversor;
    }

    public PetDTOResponse save(PetDTORequest petDTORequest) {
        PetOwner owner = null;
        if (petDTORequest.ownerId() != null) {
            owner = petOwnerRepository.findById(petDTORequest.ownerId())
                    .orElseThrow(() -> new IllegalArgumentException("Owner not found: " + petDTORequest.ownerId()));
        }
        Pet pet = petConversor.requestToEntity(petDTORequest);
        pet.setOwner(owner);
        Pet saved = petRepository.save(pet);
        return petConversor.entityToResponse(saved);
    }

    public Optional<PetDTOResponse> findById(Long id) {
        return petRepository.findById(id).map(petConversor::entityToResponse);
    }

    public List<PetDTOResponse> findAll() {
        return petRepository.findAll().stream().map(petConversor::entityToResponse).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
