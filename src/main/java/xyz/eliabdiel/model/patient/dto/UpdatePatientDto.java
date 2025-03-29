package xyz.eliabdiel.model.patient.dto;

import jakarta.validation.constraints.NotNull;
import xyz.eliabdiel.model.address.dto.RegisterAddress;

import java.util.UUID;

public record UpdatePatientDto(

        @NotNull
        UUID patientId,
        String name,
        String telephone,
        RegisterAddress address) {
}
