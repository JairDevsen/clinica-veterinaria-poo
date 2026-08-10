package com.example.demo.controller;

import com.example.demo.dto.PetDTO;
import com.example.demo.excecoes.PetNaoEncontradoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.negocio.fachada.VeterinaryClinicFacade;
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
    public ResponseEntity<PetDTO> createPet(@RequestBody PetDTO petDTO) throws ProprietarioNaoEncontradoException {
        PetDTO savedPet = facade.registerPet(petDTO);
        return ResponseEntity.created(URI.create("/api/pets/" + savedPet.getId())).body(savedPet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPet(@PathVariable Long id) throws PetNaoEncontradoException {
        return ResponseEntity.ok(facade.findPetById(id));
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> listPets() {
        return ResponseEntity.ok(facade.listPets());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) throws PetNaoEncontradoException {
        facade.deletePetById(id);
        return ResponseEntity.noContent().build();
    }
}
