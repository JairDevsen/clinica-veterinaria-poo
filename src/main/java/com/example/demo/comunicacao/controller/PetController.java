package com.example.demo.comunicacao.controller;

import com.example.demo.comunicacao.dto.request.PetDTORequest;
import com.example.demo.comunicacao.dto.response.PetDTOResponse;
import com.example.demo.excecoes.PetNaoEncontradoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.negocio.fachada.VeterinaryClinicFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final VeterinaryClinicFacade facade;

    public PetController(VeterinaryClinicFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public ResponseEntity<?> createPet(@Valid @RequestBody PetDTORequest petDTORequest) {
        try {
            PetDTOResponse savedPet = facade.registerPet(petDTORequest);
            return ResponseEntity.created(URI.create("/api/pets/" + savedPet.id())).body(savedPet);
        } catch (ProprietarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPet(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(facade.findPetById(id));
        } catch (PetNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PetDTOResponse>> listPets() {
        return ResponseEntity.ok(facade.listPets());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable Long id, @Valid @RequestBody PetDTORequest petDTORequest) {
        try {
            PetDTOResponse petAtualizado = facade.findPetById(id);
            if (petAtualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(facade.registerPet(new PetDTORequest(
                    petDTORequest.name(),
                    petDTORequest.species(),
                    petDTORequest.breed(),
                    petDTORequest.birthDate(),
                    petDTORequest.ownerId())));
        } catch (ProprietarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PetNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        try {
            facade.deletePetById(id);
            return ResponseEntity.noContent().build();
        } catch (PetNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
