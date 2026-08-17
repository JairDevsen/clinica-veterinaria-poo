package com.example.demo.negocio.cadastro;

import com.example.demo.comunicacao.conversor.PetConversor;
import com.example.demo.comunicacao.dto.request.PetDTORequest;
import com.example.demo.comunicacao.dto.response.PetDTOResponse;
import com.example.demo.excecoes.PetNaoEncontradoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.model.Pet;
import com.example.demo.model.PetOwner;
import com.example.demo.repository.PetOwnerRepository;
import com.example.demo.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CadastroPet implements InterfaceCadastroPet {

    private final PetRepository petRepository;
    private final PetOwnerRepository petOwnerRepository;
    private final PetConversor petConversor;

    public CadastroPet(PetRepository petRepository, PetOwnerRepository petOwnerRepository, PetConversor petConversor) {
        this.petRepository = petRepository;
        this.petOwnerRepository = petOwnerRepository;
        this.petConversor = petConversor;
    }

    @Override
    public PetDTOResponse salvarPet(PetDTORequest petDTORequest) throws ProprietarioNaoEncontradoException {
        PetOwner owner = null;
        if (petDTORequest.ownerId() != null) {
            owner = petOwnerRepository.findById(petDTORequest.ownerId())
                    .orElseThrow(() -> new ProprietarioNaoEncontradoException(petDTORequest.ownerId()));
        }

        Pet pet = petConversor.requestToEntity(petDTORequest);
        pet.setOwner(owner);
        Pet petSalvo = petRepository.save(pet);
        return petConversor.entityToResponse(petSalvo);
    }

    @Override
    public Pet salvarEntidadePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public PetDTOResponse procurarPetPorId(Long id) throws PetNaoEncontradoException {
        return petConversor.entityToResponse(procurarEntidadePetPorId(id));
    }

    @Override
    public Pet procurarEntidadePetPorId(Long id) throws PetNaoEncontradoException {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNaoEncontradoException(id));
    }

    @Override
    public List<PetDTOResponse> listarPets() {
        return petRepository.findAll().stream()
                .map(petConversor::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void removerPetPorId(Long id) throws PetNaoEncontradoException {
        if (!verificarExistenciaPet(id)) {
            throw new PetNaoEncontradoException(id);
        }
        petRepository.deleteById(id);
    }

    @Override
    public boolean verificarExistenciaPet(Long id) {
        return id != null && petRepository.existsById(id);
    }
}
