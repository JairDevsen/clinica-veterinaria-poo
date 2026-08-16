package com.example.demo.comunicacao.controller;

import com.example.demo.comunicacao.dto.request.PetOwnerDTORequest;
import com.example.demo.comunicacao.dto.response.PetOwnerDTOResponse;
import com.example.demo.excecoes.CpfInvalidoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.negocio.fachada.VeterinaryClinicFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> createPetOwner(@Valid @RequestBody PetOwnerDTORequest petOwnerDTORequest) {
        try {
            PetOwnerDTOResponse saved = facade.registerPetOwner(petOwnerDTORequest);
            return ResponseEntity.created(URI.create("/api/pet-owners/" + saved.id())).body(saved);
        } catch (CpfInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPetOwner(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(facade.findPetOwnerById(id));
        } catch (ProprietarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PetOwnerDTOResponse>> listPetOwners() {
        return ResponseEntity.ok(facade.listPetOwners());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePetOwner(@PathVariable Long id, @Valid @RequestBody PetOwnerDTORequest petOwnerDTORequest) {
        try {
            PetOwnerDTOResponse atual = facade.findPetOwnerById(id);
            if (atual == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(facade.registerPetOwner(petOwnerDTORequest));
        } catch (ProprietarioNaoEncontradoException | CpfInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetOwner(@PathVariable Long id) {
        try {
            facade.deletePetOwnerById(id);
            return ResponseEntity.noContent().build();
        } catch (ProprietarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
