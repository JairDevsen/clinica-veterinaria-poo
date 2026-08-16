package com.example.demo.excecoes;

public final class PetNaoEncontradoException extends Exception {

    private final Long id;

    public PetNaoEncontradoException(Long id) {
        super("Pet nao encontrado com id: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
