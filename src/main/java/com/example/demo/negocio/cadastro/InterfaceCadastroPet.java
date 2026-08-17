package com.example.demo.negocio.cadastro;

import com.example.demo.comunicacao.dto.request.PetDTORequest;
import com.example.demo.comunicacao.dto.response.PetDTOResponse;
import com.example.demo.excecoes.PetNaoEncontradoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.model.Pet;

import java.util.List;

public interface InterfaceCadastroPet {

    PetDTOResponse salvarPet(PetDTORequest petDTORequest) throws ProprietarioNaoEncontradoException;

    Pet salvarEntidadePet(Pet pet);

    PetDTOResponse procurarPetPorId(Long id) throws PetNaoEncontradoException;

    Pet procurarEntidadePetPorId(Long id) throws PetNaoEncontradoException;

    List<PetDTOResponse> listarPets();

    void removerPetPorId(Long id) throws PetNaoEncontradoException;

    boolean verificarExistenciaPet(Long id);
}
