package com.example.demo.service;

import com.example.demo.dto.PetDTO;
import com.example.demo.mapper.PetMapper;
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

    public PetService(PetRepository petRepository, PetOwnerRepository petOwnerRepository) {
        this.petRepository = petRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    public PetDTO save(PetDTO petDTO) {
        PetOwner owner = null;
        if (petDTO.getOwnerId() != null) {
            owner = petOwnerRepository.findById(petDTO.getOwnerId())
                    .orElseThrow(() -> new IllegalArgumentException("Owner not found: " + petDTO.getOwnerId()));
        }
        Pet pet = PetMapper.toEntity(petDTO, owner);
        Pet saved = petRepository.save(pet);
        return PetMapper.toDTO(saved);
    }

    public Optional<PetDTO> findById(Long id) {
        return petRepository.findById(id).map(PetMapper::toDTO);
    }

    public List<PetDTO> findAll() {
        return petRepository.findAll().stream().map(PetMapper::toDTO).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
