package com.example.demo.negocio.fachada;

import com.example.demo.dto.PetDTO;
import com.example.demo.dto.PetOwnerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class FachadaIntegrationTest {

    @Autowired
    private VeterinaryClinicFacade facade;

    @Test
    void deveAdicionarPetAoProprietario() throws Exception {
        PetOwnerDTO proprietario = facade.registerPetOwner(new PetOwnerDTO(
                null,
                "Maria",
                "Oliveira",
                LocalDate.of(1990, 3, 20),
                "12345678901",
                "81999999999",
                "maria.oliveira@example.com",
                new ArrayList<>()
        ));

        PetDTO pet = facade.registerPet(new PetDTO(
                null,
                "Luna",
                "Gato",
                "Siames",
                LocalDate.of(2022, 7, 12),
                null
        ));

        PetOwnerDTO proprietarioAtualizado = facade.adicionarPetAoProprietario(pet.getId(), proprietario.getId());

        assertEquals(1, proprietarioAtualizado.getPets().size());
        assertEquals(proprietario.getId(), facade.findPetById(pet.getId()).getOwnerId());
    }
}
