package xyz.eliabdiel.service.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.eliabdiel.model.patient.PatientEntity;
import xyz.eliabdiel.model.patient.dto.PatientDetails;
import xyz.eliabdiel.model.patient.dto.RegisterPatient;
import xyz.eliabdiel.repository.PatientRepository;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public ResponseEntity<?> registerPatient(RegisterPatient registerPatient, UriComponentsBuilder uriBuilder) {
        try {
            var patient = new PatientEntity(registerPatient);
            patientRepository.save(patient);
            URI uri = uriBuilder.path("/patients/{patientId}").buildAndExpand(patient.getId()).toUri();

            return ResponseEntity.created(uri).body(new PatientDetails(patient));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }
}
