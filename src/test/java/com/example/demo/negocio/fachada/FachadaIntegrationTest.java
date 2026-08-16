package com.example.demo.negocio.fachada;

import com.example.demo.comunicacao.dto.request.PetDTORequest;
import com.example.demo.comunicacao.dto.request.PetOwnerDTORequest;
import com.example.demo.comunicacao.dto.response.PetDTOResponse;
import com.example.demo.comunicacao.dto.response.PetOwnerDTOResponse;
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
        PetOwnerDTOResponse proprietario = facade.registerPetOwner(new PetOwnerDTORequest(
                "Maria",
                "Oliveira",
                LocalDate.of(1990, 3, 20),
                "12345678901",
                "81999999999",
                "maria.oliveira@example.com"
        ));

        PetDTOResponse pet = facade.registerPet(new PetDTORequest(
                "Luna",
                "Gato",
                "Siames",
                LocalDate.of(2022, 7, 12),
                null
        ));

        PetOwnerDTOResponse proprietarioAtualizado = facade.adicionarPetAoProprietario(pet.id(), proprietario.id());

        assertEquals(1, proprietarioAtualizado.petIds().size());
        assertEquals(proprietario.id(), facade.findPetById(pet.id()).ownerId());
    }
}
