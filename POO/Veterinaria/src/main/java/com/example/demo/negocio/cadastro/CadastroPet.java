package com.example.demo.negocio.cadastro;

import com.example.demo.dto.PetDTO;
import com.example.demo.excecoes.PetNaoEncontradoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.mapper.PetMapper;
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

    public CadastroPet(PetRepository petRepository, PetOwnerRepository petOwnerRepository) {
        this.petRepository = petRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public PetDTO salvarPet(PetDTO petDTO) throws ProprietarioNaoEncontradoException {
        PetOwner owner = null;
        if (petDTO.getOwnerId() != null) {
            owner = petOwnerRepository.findById(petDTO.getOwnerId())
                    .orElseThrow(() -> new ProprietarioNaoEncontradoException(petDTO.getOwnerId()));
        }

        Pet pet = PetMapper.toEntity(petDTO, owner);
        Pet petSalvo = petRepository.save(pet);
        return PetMapper.toDTO(petSalvo);
    }

    @Override
    public Pet salvarEntidadePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public PetDTO procurarPetPorId(Long id) throws PetNaoEncontradoException {
        return PetMapper.toDTO(procurarEntidadePetPorId(id));
    }

    @Override
    public Pet procurarEntidadePetPorId(Long id) throws PetNaoEncontradoException {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNaoEncontradoException(id));
    }

    @Override
    public List<PetDTO> listarPets() {
        return petRepository.findAll().stream()
                .map(PetMapper::toDTO)
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
