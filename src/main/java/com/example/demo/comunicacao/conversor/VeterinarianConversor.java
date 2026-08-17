package com.example.demo.comunicacao.conversor;

import com.example.demo.comunicacao.dto.request.VeterinarianDTORequest;
import com.example.demo.comunicacao.dto.response.VeterinarianDTOResponse;
import com.example.demo.model.Veterinarian;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VeterinarianConversor {

    public Veterinarian requestToEntity(VeterinarianDTORequest request) {
        if (request == null) {
            return null;
        }

        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setFirstName(request.firstName());
        veterinarian.setLastName(request.lastName());
        veterinarian.setBirthDate(request.birthDate());
        veterinarian.setCpf(request.cpf());
        veterinarian.setEmploymentDate(request.employmentDate());
        veterinarian.setFunction(request.function());
        veterinarian.setCrmv(request.crmv());
        veterinarian.setVeterinarianType(request.veterinarianType());
        return veterinarian;
    }

    public VeterinarianDTOResponse entityToResponse(Veterinarian veterinarian) {
        if (veterinarian == null) {
            return null;
        }

        List<Long> petIds = veterinarian.getPets() == null ? List.of() : veterinarian.getPets().stream()
                .map(pet -> pet.getId())
                .collect(Collectors.toList());

        return new VeterinarianDTOResponse(
                veterinarian.getId(),
                veterinarian.getFirstName(),
                veterinarian.getLastName(),
                veterinarian.getBirthDate(),
                veterinarian.getCpf(),
                veterinarian.getEmploymentDate(),
                veterinarian.getFunction(),
                veterinarian.getCrmv(),
                veterinarian.getVeterinarianType(),
                petIds
        );
    }
}
