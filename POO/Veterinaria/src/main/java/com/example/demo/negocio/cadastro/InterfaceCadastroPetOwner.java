package com.example.demo.negocio.cadastro;

import com.example.demo.dto.PetOwnerDTO;
import com.example.demo.excecoes.CpfInvalidoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.model.PetOwner;

import java.util.List;

public interface InterfaceCadastroPetOwner {

    PetOwnerDTO salvarProprietario(PetOwnerDTO petOwnerDTO) throws CpfInvalidoException;

    PetOwner salvarEntidadeProprietario(PetOwner petOwner) throws CpfInvalidoException;

    PetOwnerDTO procurarProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException;

    PetOwner procurarEntidadeProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException;

    List<PetOwnerDTO> listarProprietarios();

    void removerProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException;

    boolean verificarExistenciaProprietario(Long id);
}
