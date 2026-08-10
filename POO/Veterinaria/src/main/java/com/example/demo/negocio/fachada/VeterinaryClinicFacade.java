package com.example.demo.negocio.fachada;

import com.example.demo.dto.PetDTO;
import com.example.demo.dto.PetOwnerDTO;
import com.example.demo.dto.VeterinarianDTO;
import com.example.demo.excecoes.CpfInvalidoException;
import com.example.demo.excecoes.PetNaoEncontradoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.excecoes.VincularPetException;
import com.example.demo.mapper.PetOwnerMapper;
import com.example.demo.mapper.VeterinarianMapper;
import com.example.demo.model.Pet;
import com.example.demo.model.PetOwner;
import com.example.demo.model.Veterinarian;
import com.example.demo.negocio.cadastro.InterfaceCadastroPet;
import com.example.demo.negocio.cadastro.InterfaceCadastroPetOwner;
import com.example.demo.negocio.cadastro.InterfaceCadastroVeterinario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinaryClinicFacade {

    private final InterfaceCadastroPet cadastroPet;
    private final InterfaceCadastroVeterinario cadastroVeterinario;
    private final InterfaceCadastroPetOwner cadastroPetOwner;

    public VeterinaryClinicFacade(InterfaceCadastroPet cadastroPet,
                                  InterfaceCadastroVeterinario cadastroVeterinario,
                                  InterfaceCadastroPetOwner cadastroPetOwner) {
        this.cadastroPet = cadastroPet;
        this.cadastroVeterinario = cadastroVeterinario;
        this.cadastroPetOwner = cadastroPetOwner;
    }

    public PetDTO registerPet(PetDTO petDTO) throws ProprietarioNaoEncontradoException {
        return cadastroPet.salvarPet(petDTO);
    }

    public PetDTO findPetById(Long id) throws PetNaoEncontradoException {
        return cadastroPet.procurarPetPorId(id);
    }

    public List<PetDTO> listPets() {
        return cadastroPet.listarPets();
    }

    public void deletePetById(Long id) throws PetNaoEncontradoException {
        cadastroPet.removerPetPorId(id);
    }

    public VeterinarianDTO registerVeterinarian(VeterinarianDTO veterinarianDTO) {
        return cadastroVeterinario.salvarVeterinario(veterinarianDTO);
    }

    public Optional<VeterinarianDTO> findVeterinarianById(Long id) {
        return cadastroVeterinario.procurarVeterinarioPorId(id);
    }

    public List<VeterinarianDTO> listVeterinarians() {
        return cadastroVeterinario.listarVeterinarios();
    }

    public void deleteVeterinarianById(Long id) {
        cadastroVeterinario.removerVeterinarioPorId(id);
    }

    public PetOwnerDTO registerPetOwner(PetOwnerDTO petOwnerDTO) throws CpfInvalidoException {
        return cadastroPetOwner.salvarProprietario(petOwnerDTO);
    }

    public PetOwnerDTO findPetOwnerById(Long id) throws ProprietarioNaoEncontradoException {
        return cadastroPetOwner.procurarProprietarioPorId(id);
    }

    public List<PetOwnerDTO> listPetOwners() {
        return cadastroPetOwner.listarProprietarios();
    }

    public void deletePetOwnerById(Long id) throws ProprietarioNaoEncontradoException {
        cadastroPetOwner.removerProprietarioPorId(id);
    }

    @Transactional
    public PetOwnerDTO adicionarPetAoProprietario(Long petId, Long ownerId) throws VincularPetException {
        try {
            PetOwner owner = cadastroPetOwner.procurarEntidadeProprietarioPorId(ownerId);
            Pet pet = cadastroPet.procurarEntidadePetPorId(petId);

            if (pet.getOwner() != null && !pet.getOwner().getId().equals(ownerId)) {
                throw new VincularPetException(petId, ownerId, "pet ja pertence a outro proprietario");
            }

            if (pet.getOwner() == null) {
                owner.addPet(pet);
            }

            PetOwner ownerSalvo = cadastroPetOwner.salvarEntidadeProprietario(owner);
            return PetOwnerMapper.toDTO(ownerSalvo);
        } catch (PetNaoEncontradoException | ProprietarioNaoEncontradoException | CpfInvalidoException exception) {
            throw new VincularPetException(petId, ownerId, exception.getMessage());
        }
    }

    @Transactional
    public VeterinarianDTO associarVeterinarioAoPet(Long vetId, Long petId) throws VincularPetException {
        try {
            Pet pet = cadastroPet.procurarEntidadePetPorId(petId);
            Veterinarian veterinarian = cadastroVeterinario.procurarEntidadeVeterinarioPorId(vetId)
                    .orElseThrow(() -> new VincularPetException(petId, vetId, "veterinario nao encontrado", true));

            veterinarian.addPet(pet);
            Veterinarian veterinarianSalvo = cadastroVeterinario.salvarEntidadeVeterinario(veterinarian);
            return VeterinarianMapper.toDTO(veterinarianSalvo);
        } catch (PetNaoEncontradoException exception) {
            throw new VincularPetException(petId, vetId, exception.getMessage(), true);
        }
    }
}
