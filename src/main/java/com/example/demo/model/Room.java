package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "veterinary_clinic_id")
    private VeterinaryClinic veterinaryClinic;

    public Room() {
    }

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public VeterinaryClinic getVeterinaryClinic() {
        return veterinaryClinic;
    }

    public void setVeterinaryClinic(VeterinaryClinic veterinaryClinic) {
        this.veterinaryClinic = veterinaryClinic;
    }
}
