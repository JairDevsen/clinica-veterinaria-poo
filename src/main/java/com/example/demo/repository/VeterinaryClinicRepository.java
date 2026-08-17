package com.example.demo.repository;

import com.example.demo.model.VeterinaryClinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinaryClinicRepository extends JpaRepository<VeterinaryClinic, Long> {
}
