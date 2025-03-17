package xyz.eliabdiel.model.medical.dto;

import xyz.eliabdiel.model.address.Address;
import xyz.eliabdiel.model.medical.MedicalEntity;

public record MedicalDetails(
        Long id,
        String name,
        String email,
        String telephone,
        String specialization,
        Address address) {

    public MedicalDetails(MedicalEntity medical) {
        this(
                medical.getId(),
                medical.getName(),
                medical.getEmail(),
                medical.getTelephone(),
                medical.getSpecialization().toString(),
                medical.getAddress()
        );
    }
}