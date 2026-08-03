package com.example.demo.controller;

import com.example.demo.dto.VeterinarianDTO;
import com.example.demo.facade.VeterinaryClinicFacade;
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
    public ResponseEntity<VeterinarianDTO> createVeterinarian(@RequestBody VeterinarianDTO veterinarianDTO) {
        VeterinarianDTO saved = facade.registerVeterinarian(veterinarianDTO);
        return ResponseEntity.created(URI.create("/api/veterinarians/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarianDTO> getVeterinarian(@PathVariable Long id) {
        return facade.findVeterinarianById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<VeterinarianDTO>> listVeterinarians() {
        return ResponseEntity.ok(facade.listVeterinarians());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinarian(@PathVariable Long id) {
        facade.deleteVeterinarianById(id);
        return ResponseEntity.noContent().build();
    }
}
