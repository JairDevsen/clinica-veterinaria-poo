package com.example.demo.excecoes;

public final class ProprietarioNaoEncontradoException extends Exception {

    private final Long id;
    private final String cpf;

    public ProprietarioNaoEncontradoException(Long id) {
        super("Proprietario nao encontrado com id: " + id);
        this.id = id;
        this.cpf = null;
    }

    public ProprietarioNaoEncontradoException(String cpf) {
        super("Proprietario nao encontrado com CPF: " + cpf);
        this.id = null;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }
}
