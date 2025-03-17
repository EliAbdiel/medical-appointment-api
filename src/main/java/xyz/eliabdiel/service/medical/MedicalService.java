package xyz.eliabdiel.service.medical;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.eliabdiel.model.medical.dto.RegisterMedical;

public interface MedicalService {

    ResponseEntity<?> getAllMedical(Pageable pageable);
    ResponseEntity<?> registerMedical(RegisterMedical medical, UriComponentsBuilder componentsBuilder);
}
