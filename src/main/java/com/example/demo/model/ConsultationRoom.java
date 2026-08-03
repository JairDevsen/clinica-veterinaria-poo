package com.example.demo.model;

import jakarta.persistence.Entity;

@Entity
public class ConsultationRoom extends Room {

    private boolean hasExamTable;

    public ConsultationRoom() {
    }

    public ConsultationRoom(String roomNumber, boolean hasExamTable) {
        super(roomNumber);
        this.hasExamTable = hasExamTable;
    }

    public boolean isHasExamTable() {
        return hasExamTable;
    }

    public void setHasExamTable(boolean hasExamTable) {
        this.hasExamTable = hasExamTable;
    }
}
