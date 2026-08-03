package com.example.demo.model;

import jakarta.persistence.Entity;

@Entity
public class SurgeryRoom extends Room {

    private int numberOfTables;

    public SurgeryRoom() {
    }

    public SurgeryRoom(String roomNumber, int numberOfTables) {
        super(roomNumber);
        this.numberOfTables = numberOfTables;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }
}
