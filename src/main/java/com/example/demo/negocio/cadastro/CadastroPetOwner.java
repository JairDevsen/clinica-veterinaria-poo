package com.example.demo.negocio.cadastro;

import com.example.demo.comunicacao.conversor.PetOwnerConversor;
import com.example.demo.comunicacao.dto.request.PetOwnerDTORequest;
import com.example.demo.comunicacao.dto.response.PetOwnerDTOResponse;
import com.example.demo.excecoes.CpfInvalidoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.model.PetOwner;
import com.example.demo.repository.PetOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CadastroPetOwner implements InterfaceCadastroPetOwner {

    private final PetOwnerRepository petOwnerRepository;
    private final PetOwnerConversor petOwnerConversor;

    public CadastroPetOwner(PetOwnerRepository petOwnerRepository, PetOwnerConversor petOwnerConversor) {
        this.petOwnerRepository = petOwnerRepository;
        this.petOwnerConversor = petOwnerConversor;
    }

    @Override
    public PetOwnerDTOResponse salvarProprietario(PetOwnerDTORequest petOwnerDTORequest) throws CpfInvalidoException {
        validarCpf(petOwnerDTORequest.cpf());
        PetOwner owner = petOwnerConversor.requestToEntity(petOwnerDTORequest);
        PetOwner ownerSalvo = petOwnerRepository.save(owner);
        return petOwnerConversor.entityToResponse(ownerSalvo);
    }

    @Override
    public PetOwner salvarEntidadeProprietario(PetOwner petOwner) throws CpfInvalidoException {
        validarCpf(petOwner.getCpf());
        return petOwnerRepository.save(petOwner);
    }

    @Override
    public PetOwnerDTOResponse procurarProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException {
        return petOwnerConversor.entityToResponse(procurarEntidadeProprietarioPorId(id));
    }

    @Override
    public PetOwner procurarEntidadeProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException {
        return petOwnerRepository.findById(id)
                .orElseThrow(() -> new ProprietarioNaoEncontradoException(id));
    }

    @Override
    public List<PetOwnerDTOResponse> listarProprietarios() {
        return petOwnerRepository.findAll().stream()
                .map(petOwnerConversor::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void removerProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException {
        if (!verificarExistenciaProprietario(id)) {
            throw new ProprietarioNaoEncontradoException(id);
        }
        petOwnerRepository.deleteById(id);
    }

    @Override
    public boolean verificarExistenciaProprietario(Long id) {
        return id != null && petOwnerRepository.existsById(id);
    }

    private void validarCpf(String cpf) throws CpfInvalidoException {
        if (cpf == null || cpf.isBlank()) {
            throw new CpfInvalidoException(cpf);
        }

        String apenasDigitos = cpf.replaceAll("\\D", "");
        if (apenasDigitos.length() != 11) {
            throw new CpfInvalidoException(cpf);
        }
    }
}
