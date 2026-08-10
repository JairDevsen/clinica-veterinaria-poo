package com.example.demo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VeterinarianDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String cpf;
    private LocalDate employmentDate;
    private String function;
    private String crmv;
    private String veterinarianType;
    private List<Long> petIds = new ArrayList<>();

    public VeterinarianDTO() {
    }

    public VeterinarianDTO(Long id, String firstName, String lastName, LocalDate birthDate, String cpf, LocalDate employmentDate, String function, String crmv, String veterinarianType, List<Long> petIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.employmentDate = employmentDate;
        this.function = function;
        this.crmv = crmv;
        this.veterinarianType = veterinarianType;
        this.petIds = petIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
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

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }
}
