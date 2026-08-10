package com.example.demo.service;

import com.example.demo.dto.VeterinarianDTO;
import com.example.demo.mapper.VeterinarianMapper;
import com.example.demo.model.Veterinarian;
import com.example.demo.repository.VeterinarianRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;

    public VeterinarianService(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    public VeterinarianDTO save(VeterinarianDTO veterinarianDTO) {
        Veterinarian veterinarian = VeterinarianMapper.toEntity(veterinarianDTO);
        Veterinarian saved = veterinarianRepository.save(veterinarian);
        return VeterinarianMapper.toDTO(saved);
    }

    public Optional<VeterinarianDTO> findById(Long id) {
        return veterinarianRepository.findById(id).map(VeterinarianMapper::toDTO);
    }

    public List<VeterinarianDTO> findAll() {
        return veterinarianRepository.findAll().stream().map(VeterinarianMapper::toDTO).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        veterinarianRepository.deleteById(id);
    }
}
