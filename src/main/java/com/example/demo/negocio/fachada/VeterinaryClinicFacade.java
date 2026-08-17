package com.example.demo.negocio.fachada;

import com.example.demo.comunicacao.conversor.PetOwnerConversor;
import com.example.demo.comunicacao.conversor.VeterinarianConversor;
import com.example.demo.comunicacao.dto.request.PetDTORequest;
import com.example.demo.comunicacao.dto.request.PetOwnerDTORequest;
import com.example.demo.comunicacao.dto.request.VeterinarianDTORequest;
import com.example.demo.comunicacao.dto.response.PetDTOResponse;
import com.example.demo.comunicacao.dto.response.PetOwnerDTOResponse;
import com.example.demo.comunicacao.dto.response.VeterinarianDTOResponse;
import com.example.demo.excecoes.CpfInvalidoException;
import com.example.demo.excecoes.PetNaoEncontradoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.excecoes.VincularPetException;
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
    private final PetOwnerConversor petOwnerConversor;
    private final VeterinarianConversor veterinarianConversor;

    public VeterinaryClinicFacade(InterfaceCadastroPet cadastroPet,
                                  InterfaceCadastroVeterinario cadastroVeterinario,
                                  InterfaceCadastroPetOwner cadastroPetOwner,
                                  PetOwnerConversor petOwnerConversor,
                                  VeterinarianConversor veterinarianConversor) {
        this.cadastroPet = cadastroPet;
        this.cadastroVeterinario = cadastroVeterinario;
        this.cadastroPetOwner = cadastroPetOwner;
        this.petOwnerConversor = petOwnerConversor;
        this.veterinarianConversor = veterinarianConversor;
    }

    public PetDTOResponse registerPet(PetDTORequest petDTORequest) throws ProprietarioNaoEncontradoException {
        return cadastroPet.salvarPet(petDTORequest);
    }

    public PetDTOResponse findPetById(Long id) throws PetNaoEncontradoException {
        return cadastroPet.procurarPetPorId(id);
    }

    public List<PetDTOResponse> listPets() {
        return cadastroPet.listarPets();
    }

    public void deletePetById(Long id) throws PetNaoEncontradoException {
        cadastroPet.removerPetPorId(id);
    }

    public VeterinarianDTOResponse registerVeterinarian(VeterinarianDTORequest veterinarianDTORequest) {
        return cadastroVeterinario.salvarVeterinario(veterinarianDTORequest);
    }

    public Optional<VeterinarianDTOResponse> findVeterinarianById(Long id) {
        return cadastroVeterinario.procurarVeterinarioPorId(id);
    }

    public List<VeterinarianDTOResponse> listVeterinarians() {
        return cadastroVeterinario.listarVeterinarios();
    }

    public void deleteVeterinarianById(Long id) {
        cadastroVeterinario.removerVeterinarioPorId(id);
    }

    public PetOwnerDTOResponse registerPetOwner(PetOwnerDTORequest petOwnerDTORequest) throws CpfInvalidoException {
        return cadastroPetOwner.salvarProprietario(petOwnerDTORequest);
    }

    public PetOwnerDTOResponse findPetOwnerById(Long id) throws ProprietarioNaoEncontradoException {
        return cadastroPetOwner.procurarProprietarioPorId(id);
    }

    public List<PetOwnerDTOResponse> listPetOwners() {
        return cadastroPetOwner.listarProprietarios();
    }

    public void deletePetOwnerById(Long id) throws ProprietarioNaoEncontradoException {
        cadastroPetOwner.removerProprietarioPorId(id);
    }

    @Transactional
    public PetOwnerDTOResponse adicionarPetAoProprietario(Long petId, Long ownerId) throws VincularPetException {
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
            return petOwnerConversor.entityToResponse(ownerSalvo);
        } catch (PetNaoEncontradoException | ProprietarioNaoEncontradoException | CpfInvalidoException exception) {
            throw new VincularPetException(petId, ownerId, exception.getMessage());
        }
    }

    @Transactional
    public VeterinarianDTOResponse associarVeterinarioAoPet(Long vetId, Long petId) throws VincularPetException {
        try {
            Pet pet = cadastroPet.procurarEntidadePetPorId(petId);
            Veterinarian veterinarian = cadastroVeterinario.procurarEntidadeVeterinarioPorId(vetId)
                    .orElseThrow(() -> new VincularPetException(petId, vetId, "veterinario nao encontrado", true));

            veterinarian.addPet(pet);
            Veterinarian veterinarianSalvo = cadastroVeterinario.salvarEntidadeVeterinario(veterinarian);
            return veterinarianConversor.entityToResponse(veterinarianSalvo);
        } catch (PetNaoEncontradoException exception) {
            throw new VincularPetException(petId, vetId, exception.getMessage(), true);
        }
    }
}
