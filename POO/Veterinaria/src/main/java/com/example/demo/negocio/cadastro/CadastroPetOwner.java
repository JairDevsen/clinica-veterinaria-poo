package com.example.demo.negocio.cadastro;

import com.example.demo.dto.PetOwnerDTO;
import com.example.demo.excecoes.CpfInvalidoException;
import com.example.demo.excecoes.ProprietarioNaoEncontradoException;
import com.example.demo.mapper.PetOwnerMapper;
import com.example.demo.model.PetOwner;
import com.example.demo.repository.PetOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CadastroPetOwner implements InterfaceCadastroPetOwner {

    private final PetOwnerRepository petOwnerRepository;

    public CadastroPetOwner(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public PetOwnerDTO salvarProprietario(PetOwnerDTO petOwnerDTO) throws CpfInvalidoException {
        validarCpf(petOwnerDTO.getCpf());
        PetOwner owner = PetOwnerMapper.toEntity(petOwnerDTO);
        PetOwner ownerSalvo = petOwnerRepository.save(owner);
        return PetOwnerMapper.toDTO(ownerSalvo);
    }

    @Override
    public PetOwner salvarEntidadeProprietario(PetOwner petOwner) throws CpfInvalidoException {
        validarCpf(petOwner.getCpf());
        return petOwnerRepository.save(petOwner);
    }

    @Override
    public PetOwnerDTO procurarProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException {
        return PetOwnerMapper.toDTO(procurarEntidadeProprietarioPorId(id));
    }

    @Override
    public PetOwner procurarEntidadeProprietarioPorId(Long id) throws ProprietarioNaoEncontradoException {
        return petOwnerRepository.findById(id)
                .orElseThrow(() -> new ProprietarioNaoEncontradoException(id));
    }

    @Override
    public List<PetOwnerDTO> listarProprietarios() {
        return petOwnerRepository.findAll().stream()
                .map(PetOwnerMapper::toDTO)
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
