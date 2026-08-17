package com.example.demo.service;

import com.example.demo.comunicacao.conversor.VeterinarianConversor;
import com.example.demo.comunicacao.dto.request.VeterinarianDTORequest;
import com.example.demo.comunicacao.dto.response.VeterinarianDTOResponse;
import com.example.demo.model.Veterinarian;
import com.example.demo.repository.VeterinarianRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;
    private final VeterinarianConversor veterinarianConversor;

    public VeterinarianService(VeterinarianRepository veterinarianRepository, VeterinarianConversor veterinarianConversor) {
        this.veterinarianRepository = veterinarianRepository;
        this.veterinarianConversor = veterinarianConversor;
    }

    public VeterinarianDTOResponse save(VeterinarianDTORequest veterinarianDTORequest) {
        Veterinarian veterinarian = veterinarianConversor.requestToEntity(veterinarianDTORequest);
        Veterinarian saved = veterinarianRepository.save(veterinarian);
        return veterinarianConversor.entityToResponse(saved);
    }

    public Optional<VeterinarianDTOResponse> findById(Long id) {
        return veterinarianRepository.findById(id).map(veterinarianConversor::entityToResponse);
    }

    public List<VeterinarianDTOResponse> findAll() {
        return veterinarianRepository.findAll().stream().map(veterinarianConversor::entityToResponse).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        veterinarianRepository.deleteById(id);
    }
}
