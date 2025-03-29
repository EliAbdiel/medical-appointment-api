package xyz.eliabdiel.service.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.eliabdiel.model.patient.PatientEntity;
import xyz.eliabdiel.model.patient.dto.ListOfPatient;
import xyz.eliabdiel.model.patient.dto.PatientDetails;
import xyz.eliabdiel.model.patient.dto.RegisterPatient;
import xyz.eliabdiel.model.patient.dto.UpdatePatientDto;
import xyz.eliabdiel.repository.patient.PatientRepository;

import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getAllPatient(Pageable pageable) {
        try {
            var page = patientRepository.findAllByActiveTrue(pageable).map(ListOfPatient::new);
            return ResponseEntity.ok(page);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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

    @Override
    @Transactional
    public ResponseEntity<?> deletePatient(UUID patientId) {
        try {
            var patient = patientRepository.getReferenceById(patientId);
            patient.deactivatePatient();

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> updatePatient(UpdatePatientDto updatePatient) {
        try {
            var patient = patientRepository.getReferenceById(updatePatient.patientId());
            patient.updatePatient(updatePatient);
            return ResponseEntity.ok(new PatientDetails(patient));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }
}