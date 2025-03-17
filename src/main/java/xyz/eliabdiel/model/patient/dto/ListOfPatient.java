package xyz.eliabdiel.model.patient.dto;

import xyz.eliabdiel.model.patient.PatientEntity;

import java.util.UUID;

public record ListOfPatient(
    UUID id,
    String name,
    String email,
    String document) {

    public ListOfPatient(PatientEntity patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getDocument()
        );
    }
}
