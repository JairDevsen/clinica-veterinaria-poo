package com.example.demo.negocio.cadastro;

import com.example.demo.dto.VeterinarianDTO;
import com.example.demo.mapper.VeterinarianMapper;
import com.example.demo.model.Veterinarian;
import com.example.demo.repository.VeterinarianRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CadastroVeterinario implements InterfaceCadastroVeterinario {

    private final VeterinarianRepository veterinarianRepository;

    public CadastroVeterinario(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    public VeterinarianDTO salvarVeterinario(VeterinarianDTO veterinarianDTO) {
        Veterinarian veterinarian = VeterinarianMapper.toEntity(veterinarianDTO);
        Veterinarian veterinarianSalvo = veterinarianRepository.save(veterinarian);
        return VeterinarianMapper.toDTO(veterinarianSalvo);
    }

    @Override
    public Veterinarian salvarEntidadeVeterinario(Veterinarian veterinarian) {
        return veterinarianRepository.save(veterinarian);
    }

    @Override
    public Optional<VeterinarianDTO> procurarVeterinarioPorId(Long id) {
        return veterinarianRepository.findById(id).map(VeterinarianMapper::toDTO);
    }

    @Override
    public Optional<Veterinarian> procurarEntidadeVeterinarioPorId(Long id) {
        return veterinarianRepository.findById(id);
    }

    @Override
    public List<VeterinarianDTO> listarVeterinarios() {
        return veterinarianRepository.findAll().stream()
                .map(VeterinarianMapper::toDTO)
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
