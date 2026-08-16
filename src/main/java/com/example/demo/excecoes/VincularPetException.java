package com.example.demo.excecoes;

public final class VincularPetException extends Exception {

    private final Long petId;
    private final Long ownerId;
    private final Long veterinarianId;
    private final String motivo;

    public VincularPetException(Long petId, Long ownerId, String motivo) {
        super("Nao foi possivel vincular o pet " + petId + " ao proprietario " + ownerId + ": " + motivo);
        this.petId = petId;
        this.ownerId = ownerId;
        this.veterinarianId = null;
        this.motivo = motivo;
    }

    public VincularPetException(Long petId, Long veterinarianId, String motivo, boolean atendimentoVeterinario) {
        super("Nao foi possivel vincular o veterinario " + veterinarianId + " ao pet " + petId + ": " + motivo);
        this.petId = petId;
        this.ownerId = null;
        this.veterinarianId = veterinarianId;
        this.motivo = motivo;
    }

    public Long getPetId() {
        return petId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Long getVeterinarianId() {
        return veterinarianId;
    }

    public String getMotivo() {
        return motivo;
    }
}
