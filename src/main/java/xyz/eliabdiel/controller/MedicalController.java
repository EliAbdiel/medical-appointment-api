package xyz.eliabdiel.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.eliabdiel.model.medical.dto.RegisterMedical;
import xyz.eliabdiel.service.medical.MedicalService;

@RestController
@RequestMapping("/medical")
@RequiredArgsConstructor
public class MedicalController {

    private final MedicalService medicalService;

    @GetMapping
    public ResponseEntity<?> getAllMedical(@PageableDefault(size = 5) Pageable pageable) {
        return medicalService.getAllMedical(pageable);
    }

    @PostMapping
    public ResponseEntity<?> registerMedical(@RequestBody @Valid RegisterMedical medical, UriComponentsBuilder componentsBuilder) {
        return medicalService.registerMedical(medical, componentsBuilder);
    }
}
