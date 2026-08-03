package com.example.demo.facade;

import com.example.demo.dto.PetDTO;
import com.example.demo.dto.PetOwnerDTO;
import com.example.demo.dto.VeterinarianDTO;
import com.example.demo.service.PetOwnerService;
import com.example.demo.service.PetService;
import com.example.demo.service.VeterinarianService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinaryClinicFacade {

    private final PetService petService;
    private final VeterinarianService veterinarianService;
    private final PetOwnerService petOwnerService;

    public VeterinaryClinicFacade(PetService petService,
                                  VeterinarianService veterinarianService,
                                  PetOwnerService petOwnerService) {
        this.petService = petService;
        this.veterinarianService = veterinarianService;
        this.petOwnerService = petOwnerService;
    }

    public PetDTO registerPet(PetDTO petDTO) {
        return petService.save(petDTO);
    }

    public Optional<PetDTO> findPetById(Long id) {
        return petService.findById(id);
    }

    public List<PetDTO> listPets() {
        return petService.findAll();
    }

    public void deletePetById(Long id) {
        petService.deleteById(id);
    }

    public VeterinarianDTO registerVeterinarian(VeterinarianDTO veterinarianDTO) {
        return veterinarianService.save(veterinarianDTO);
    }

    public Optional<VeterinarianDTO> findVeterinarianById(Long id) {
        return veterinarianService.findById(id);
    }

    public List<VeterinarianDTO> listVeterinarians() {
        return veterinarianService.findAll();
    }

    public void deleteVeterinarianById(Long id) {
        veterinarianService.deleteById(id);
    }

    public PetOwnerDTO registerPetOwner(PetOwnerDTO petOwnerDTO) {
        return petOwnerService.save(petOwnerDTO);
    }

    public Optional<PetOwnerDTO> findPetOwnerById(Long id) {
        return petOwnerService.findById(id);
    }

    public List<PetOwnerDTO> listPetOwners() {
        return petOwnerService.findAll();
    }

    public void deletePetOwnerById(Long id) {
        petOwnerService.deleteById(id);
    }
}
