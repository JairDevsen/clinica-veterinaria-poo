package com.example.demo.negocio.cadastro;

import com.example.demo.excecoes.PetNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CadastroPetTest {

    @Autowired
    private InterfaceCadastroPet cadastroPet;

    @Test
    void deveLancarExcecaoAoBuscarPetInexistente() {
        Long idInexistente = 999999L;

        PetNaoEncontradoException exception = assertThrows(
                PetNaoEncontradoException.class,
                () -> cadastroPet.procurarPetPorId(idInexistente)
        );

        assertEquals(idInexistente, exception.getId());
    }
}
