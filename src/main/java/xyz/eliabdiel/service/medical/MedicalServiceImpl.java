package xyz.eliabdiel.service.medical;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.eliabdiel.model.medical.MedicalEntity;
import xyz.eliabdiel.model.medical.dto.ListOfMedical;
import xyz.eliabdiel.model.medical.dto.MedicalDetails;
import xyz.eliabdiel.model.medical.dto.RegisterMedical;
import xyz.eliabdiel.repository.medical.MedicalRepository;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class MedicalServiceImpl implements MedicalService{

    private final MedicalRepository medicalRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getAllMedical(Pageable pageable) {
        try {
            var page = medicalRepository.findByActiveTrue(pageable).map(ListOfMedical::new);
            return ResponseEntity.ok(page);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> registerMedical(RegisterMedical medical, UriComponentsBuilder componentsBuilder) {
        try {
            MedicalEntity medic = new MedicalEntity(medical);
            medicalRepository.save(medic);
            URI uri = componentsBuilder.path("/medical/{id}").buildAndExpand(medic.getId()).toUri();
            return ResponseEntity.created(uri).body(new MedicalDetails(medic));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
