package com.example.demo.controller;

import com.example.demo.dto.PetOwnerDTO;
import com.example.demo.facade.VeterinaryClinicFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pet-owners")
public class PetOwnerController {

    private final VeterinaryClinicFacade facade;

    public PetOwnerController(VeterinaryClinicFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public ResponseEntity<PetOwnerDTO> createPetOwner(@RequestBody PetOwnerDTO petOwnerDTO) {
        PetOwnerDTO saved = facade.registerPetOwner(petOwnerDTO);
        return ResponseEntity.created(URI.create("/api/pet-owners/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetOwnerDTO> getPetOwner(@PathVariable Long id) {
        return facade.findPetOwnerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PetOwnerDTO>> listPetOwners() {
        return ResponseEntity.ok(facade.listPetOwners());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetOwner(@PathVariable Long id) {
        facade.deletePetOwnerById(id);
        return ResponseEntity.noContent().build();
    }
}
