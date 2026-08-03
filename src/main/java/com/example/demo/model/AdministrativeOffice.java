package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AdministrativeOffice extends Room {

    private String officeName;

    @ManyToMany
    @JoinTable(
            name = "administrative_office_employee",
            joinColumns = @JoinColumn(name = "administrative_office_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>();

    public AdministrativeOffice() {
    }

    public AdministrativeOffice(String roomNumber, String officeName) {
        super(roomNumber);
        this.officeName = officeName;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.getAdministrativeOffices().add(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.getAdministrativeOffices().remove(this);
    }
}
