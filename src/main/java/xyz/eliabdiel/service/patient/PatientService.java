package xyz.eliabdiel.service.patient;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.eliabdiel.model.patient.dto.RegisterPatient;
import xyz.eliabdiel.model.patient.dto.UpdatePatientDto;

import java.util.UUID;

public interface PatientService {

    ResponseEntity<?> getAllPatient(Pageable pageable);
    ResponseEntity<?> registerPatient(RegisterPatient patient, UriComponentsBuilder uriBuilder);
    ResponseEntity<?> deletePatient(UUID patientId);
    ResponseEntity<?> updatePatient(UpdatePatientDto updatePatient);
}