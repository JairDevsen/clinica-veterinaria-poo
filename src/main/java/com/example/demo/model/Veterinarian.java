package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Veterinarian extends Employee {

    private String crmv;
    private String veterinarianType;

    @ManyToMany
    @JoinTable(
            name = "veterinarian_pet",
            joinColumns = @JoinColumn(name = "veterinarian_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private Set<Pet> pets = new HashSet<>();

    public Veterinarian() {
    }

    public Veterinarian(String firstName, String lastName, LocalDate birthDate, String cpf, LocalDate employmentDate, String function, String crmv, String veterinarianType) {
        super(firstName, lastName, birthDate, cpf, employmentDate, function);
        this.crmv = crmv;
        this.veterinarianType = veterinarianType;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getVeterinarianType() {
        return veterinarianType;
    }

    public void setVeterinarianType(String veterinarianType) {
        this.veterinarianType = veterinarianType;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.getVeterinarians().add(this);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.getVeterinarians().remove(this);
    }
}
