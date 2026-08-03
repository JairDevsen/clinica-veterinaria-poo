package com.example.demo;

import com.example.demo.model.Pet;
import com.example.demo.model.PetOwner;
import com.example.demo.repository.PetOwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class PersistenceTests {

    @Autowired
    private PetOwnerRepository petOwnerRepository;

    @Test
    void shouldSavePetOwnerWithPetAndCascadePersistPet() {
        PetOwner owner = new PetOwner(
                "Joao",
                "Silva",
                LocalDate.of(1985, 5, 15),
                "12345678901",
                "11999999999",
                "joao.silva@example.com"
        );

        Pet pet = new Pet("Rex", "Cao", "Labrador", LocalDate.of(2020, 9, 1));
        owner.addPet(pet);

        PetOwner savedOwner = petOwnerRepository.save(owner);

        assertThat(savedOwner.getId()).isNotNull();
        assertThat(savedOwner.getPets()).hasSize(1);
        assertThat(savedOwner.getPets().iterator().next().getId()).isNotNull();
        assertThat(savedOwner.getPets().iterator().next().getOwner()).isEqualTo(savedOwner);
    }
}
