package xyz.eliabdiel.model.patient.dto;

import xyz.eliabdiel.model.address.Address;
import xyz.eliabdiel.model.patient.PatientEntity;

import java.util.UUID;

public record PatientDetails(
        UUID id,
        String name,
        String email,
        String telephone,
        String document,
        Address address) {

    public PatientDetails(PatientEntity patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getTelephone(),
                patient.getDocument(),
                patient.getAddress()
        );
    }
}
