package com.example.demo.service;

import com.example.demo.comunicacao.conversor.PetOwnerConversor;
import com.example.demo.comunicacao.dto.request.PetOwnerDTORequest;
import com.example.demo.comunicacao.dto.response.PetOwnerDTOResponse;
import com.example.demo.model.PetOwner;
import com.example.demo.repository.PetOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetOwnerService {

    private final PetOwnerRepository petOwnerRepository;
    private final PetOwnerConversor petOwnerConversor;

    public PetOwnerService(PetOwnerRepository petOwnerRepository, PetOwnerConversor petOwnerConversor) {
        this.petOwnerRepository = petOwnerRepository;
        this.petOwnerConversor = petOwnerConversor;
    }

    public PetOwnerDTOResponse save(PetOwnerDTORequest petOwnerDTORequest) {
        PetOwner owner = petOwnerConversor.requestToEntity(petOwnerDTORequest);
        PetOwner saved = petOwnerRepository.save(owner);
        return petOwnerConversor.entityToResponse(saved);
    }

    public Optional<PetOwnerDTOResponse> findById(Long id) {
        return petOwnerRepository.findById(id).map(petOwnerConversor::entityToResponse);
    }

    public List<PetOwnerDTOResponse> findAll() {
        return petOwnerRepository.findAll().stream().map(petOwnerConversor::entityToResponse).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        petOwnerRepository.deleteById(id);
    }
}
