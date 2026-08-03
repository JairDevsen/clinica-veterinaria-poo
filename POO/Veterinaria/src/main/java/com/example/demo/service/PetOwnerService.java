package com.example.demo.service;

import com.example.demo.dto.PetOwnerDTO;
import com.example.demo.mapper.PetOwnerMapper;
import com.example.demo.model.PetOwner;
import com.example.demo.repository.PetOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetOwnerService {

    private final PetOwnerRepository petOwnerRepository;

    public PetOwnerService(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    public PetOwnerDTO save(PetOwnerDTO petOwnerDTO) {
        PetOwner owner = PetOwnerMapper.toEntity(petOwnerDTO);
        PetOwner saved = petOwnerRepository.save(owner);
        return PetOwnerMapper.toDTO(saved);
    }

    public Optional<PetOwnerDTO> findById(Long id) {
        return petOwnerRepository.findById(id).map(PetOwnerMapper::toDTO);
    }

    public List<PetOwnerDTO> findAll() {
        return petOwnerRepository.findAll().stream().map(PetOwnerMapper::toDTO).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        petOwnerRepository.deleteById(id);
    }
}
