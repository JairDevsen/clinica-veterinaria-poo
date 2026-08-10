package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee extends Person {

    private LocalDate employmentDate;
    private String function;

    @ManyToMany(mappedBy = "employees")
    private Set<AdministrativeOffice> administrativeOffices = new HashSet<>();

    public Employee() {
    }

    public Employee(String firstName, String lastName, LocalDate birthDate, String cpf, LocalDate employmentDate, String function) {
        super(firstName, lastName, birthDate, cpf);
        this.employmentDate = employmentDate;
        this.function = function;
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

    public Set<AdministrativeOffice> getAdministrativeOffices() {
        return administrativeOffices;
    }

    public void setAdministrativeOffices(Set<AdministrativeOffice> administrativeOffices) {
        this.administrativeOffices = administrativeOffices;
    }
}
