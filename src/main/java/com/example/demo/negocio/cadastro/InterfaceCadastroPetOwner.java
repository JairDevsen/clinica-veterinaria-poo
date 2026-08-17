package com.example.demo.negocio.cadastro;

import com.example.demo.comunicacao.dto.request.PetOwnerDTORequest;
import com.example.demo.comunicacao.dto.response.PetOwnerDTOResponse;
import com.example.demo.excecoes.CpfInvalidoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.model.PetOwner;

import java.util.List;

public interface InterfaceCadastroPetOwner {

    PetOwnerDTOResponse salvarProprietario(PetOwnerDTORequest petOwnerDTORequest) throws CpfInvalidoException;

    PetOwner salvarEntidadeProprietario(PetOwner petOwner) throws CpfInvalidoException;

    PetOwnerDTOResponse procurarProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException;

    PetOwner procurarEntidadeProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException;

    List<PetOwnerDTOResponse> listarProprietarios();

    void removerProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException;

    boolean verificarExistenciaProprietario(Long id);
}
