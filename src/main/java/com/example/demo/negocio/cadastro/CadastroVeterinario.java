package com.example.demo.negocio.cadastro;

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
public class CadastroVeterinario implements InterfaceCadastroVeterinario {

    private final VeterinarianRepository veterinarianRepository;
    private final VeterinarianConversor veterinarianConversor;

    public CadastroVeterinario(VeterinarianRepository veterinarianRepository, VeterinarianConversor veterinarianConversor) {
        this.veterinarianRepository = veterinarianRepository;
        this.veterinarianConversor = veterinarianConversor;
    }

    @Override
    public VeterinarianDTOResponse salvarVeterinario(VeterinarianDTORequest veterinarianDTORequest) {
        Veterinarian veterinarian = veterinarianConversor.requestToEntity(veterinarianDTORequest);
        Veterinarian veterinarianSalvo = veterinarianRepository.save(veterinarian);
        return veterinarianConversor.entityToResponse(veterinarianSalvo);
    }

    @Override
    public Veterinarian salvarEntidadeVeterinario(Veterinarian veterinarian) {
        return veterinarianRepository.save(veterinarian);
    }

    @Override
    public Optional<VeterinarianDTOResponse> procurarVeterinarioPorId(Long id) {
        return veterinarianRepository.findById(id).map(veterinarianConversor::entityToResponse);
    }

    @Override
    public Optional<Veterinarian> procurarEntidadeVeterinarioPorId(Long id) {
        return veterinarianRepository.findById(id);
    }

    @Override
    public List<VeterinarianDTOResponse> listarVeterinarios() {
        return veterinarianRepository.findAll().stream()
                .map(veterinarianConversor::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void removerVeterinarioPorId(Long id) {
        veterinarianRepository.deleteById(id);
    }

    @Override
    public boolean verificarExistenciaVeterinario(Long id) {
        return id != null && veterinarianRepository.existsById(id);
    }
}
