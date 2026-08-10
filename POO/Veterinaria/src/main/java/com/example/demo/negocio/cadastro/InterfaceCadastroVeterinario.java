package com.example.demo.negocio.cadastro;

import com.example.demo.dto.VeterinarianDTO;
import com.example.demo.model.Veterinarian;

import java.util.List;
import java.util.Optional;

public interface InterfaceCadastroVeterinario {

    VeterinarianDTO salvarVeterinario(VeterinarianDTO veterinarianDTO);

    Veterinarian salvarEntidadeVeterinario(Veterinarian veterinarian);

    Optional<VeterinarianDTO> procurarVeterinarioPorId(Long id);

    Optional<Veterinarian> procurarEntidadeVeterinarioPorId(Long id);

    List<VeterinarianDTO> listarVeterinarios();

    void removerVeterinarioPorId(Long id);

    boolean verificarExistenciaVeterinario(Long id);
}
