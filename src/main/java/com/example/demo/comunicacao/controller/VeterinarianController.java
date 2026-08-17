package com.example.demo.comunicacao.controller;

import com.example.demo.comunicacao.dto.request.VeterinarianDTORequest;
import com.example.demo.comunicacao.dto.response.VeterinarianDTOResponse;
import com.example.demo.negocio.fachada.VeterinaryClinicFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/veterinarians")
public class VeterinarianController {

    private final VeterinaryClinicFacade facade;

    public VeterinarianController(VeterinaryClinicFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public ResponseEntity<VeterinarianDTOResponse> createVeterinarian(@Valid @RequestBody VeterinarianDTORequest veterinarianDTORequest) {
        VeterinarianDTOResponse saved = facade.registerVeterinarian(veterinarianDTORequest);
        return ResponseEntity.created(URI.create("/api/veterinarians/" + saved.id())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarianDTOResponse> getVeterinarian(@PathVariable Long id) {
        return facade.findVeterinarianById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<VeterinarianDTOResponse>> listVeterinarians() {
        return ResponseEntity.ok(facade.listVeterinarians());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarianDTOResponse> updateVeterinarian(@PathVariable Long id, @Valid @RequestBody VeterinarianDTORequest veterinarianDTORequest) {
        return facade.findVeterinarianById(id)
                .map(existing -> ResponseEntity.ok(facade.registerVeterinarian(veterinarianDTORequest)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinarian(@PathVariable Long id) {
        facade.deleteVeterinarianById(id);
        return ResponseEntity.noContent().build();
    }
}
