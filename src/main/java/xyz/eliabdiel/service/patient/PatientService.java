package xyz.eliabdiel.service.patient;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.eliabdiel.model.patient.dto.RegisterPatient;

public interface PatientService {

    ResponseEntity<?> getAllPatient(Pageable pageable);
    ResponseEntity<?> registerPatient(RegisterPatient patient, UriComponentsBuilder uriBuilder);

}
