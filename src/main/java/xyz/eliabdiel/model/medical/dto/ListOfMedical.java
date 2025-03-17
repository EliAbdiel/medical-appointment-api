package xyz.eliabdiel.model.medical.dto;

import xyz.eliabdiel.model.medical.MedicalEntity;

public record ListOfMedical(
        Long id,
        String name,
        String email,
        String document,
        String specialization) {

    public ListOfMedical(MedicalEntity medical) {
        this(
                medical.getId(),
                medical.getName(),
                medical.getEmail(),
                medical.getDocument(),
                medical.getSpecialization().toString()

        );
    }
}
