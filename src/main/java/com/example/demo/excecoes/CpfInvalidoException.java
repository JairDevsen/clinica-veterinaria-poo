package com.example.demo.excecoes;

public final class CpfInvalidoException extends Exception {

    private final String cpf;

    public CpfInvalidoException(String cpf) {
        super("CPF invalido para cadastro de proprietario: " + cpf);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
