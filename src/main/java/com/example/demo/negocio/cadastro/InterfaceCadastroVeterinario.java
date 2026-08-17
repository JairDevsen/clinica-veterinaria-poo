package com.example.demo.negocio.cadastro;

import com.example.demo.comunicacao.dto.request.VeterinarianDTORequest;
import com.example.demo.comunicacao.dto.response.VeterinarianDTOResponse;
import com.example.demo.model.Veterinarian;

import java.util.List;
import java.util.Optional;

public interface InterfaceCadastroVeterinario {

    VeterinarianDTOResponse salvarVeterinario(VeterinarianDTORequest veterinarianDTORequest);

    Veterinarian salvarEntidadeVeterinario(Veterinarian veterinarian);

    Optional<VeterinarianDTOResponse> procurarVeterinarioPorId(Long id);

    Optional<Veterinarian> procurarEntidadeVeterinarioPorId(Long id);

    List<VeterinarianDTOResponse> listarVeterinarios();

    void removerVeterinarioPorId(Long id);

    boolean verificarExistenciaVeterinario(Long id);
}
