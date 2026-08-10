package com.example.demo.negocio.cadastro;

import com.example.demo.dto.PetDTO;
import com.example.demo.excecoes.PetNaoEncontradoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.model.Pet;

import java.util.List;

public interface InterfaceCadastroPet {

    PetDTO salvarPet(PetDTO petDTO) throws ProprietarioNaoEncontradoException;

    Pet salvarEntidadePet(Pet pet);

    PetDTO procurarPetPorId(Long id) throws PetNaoEncontradoException;

    Pet procurarEntidadePetPorId(Long id) throws PetNaoEncontradoException;

    List<PetDTO> listarPets();

    void removerPetPorId(Long id) throws PetNaoEncontradoException;

    boolean verificarExistenciaPet(Long id);
}
