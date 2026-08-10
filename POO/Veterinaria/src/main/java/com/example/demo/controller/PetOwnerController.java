package com.example.demo.controller;

import com.example.demo.dto.PetOwnerDTO;
import com.example.demo.excecoes.CpfInvalidoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.negocio.fachada.VeterinaryClinicFacade;
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
    public ResponseEntity<PetOwnerDTO> createPetOwner(@RequestBody PetOwnerDTO petOwnerDTO) throws CpfInvalidoException {
        PetOwnerDTO saved = facade.registerPetOwner(petOwnerDTO);
        return ResponseEntity.created(URI.create("/api/pet-owners/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetOwnerDTO> getPetOwner(@PathVariable Long id) throws ProprietarioNaoEncontradoException {
        return ResponseEntity.ok(facade.findPetOwnerById(id));
    }

    @GetMapping
    public ResponseEntity<List<PetOwnerDTO>> listPetOwners() {
        return ResponseEntity.ok(facade.listPetOwners());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetOwner(@PathVariable Long id) throws ProprietarioNaoEncontradoException {
        facade.deletePetOwnerById(id);
        return ResponseEntity.noContent().build();
    }
}
