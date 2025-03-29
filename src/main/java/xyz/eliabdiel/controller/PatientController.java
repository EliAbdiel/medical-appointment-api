package xyz.eliabdiel.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.eliabdiel.model.patient.dto.RegisterPatient;
import xyz.eliabdiel.model.patient.dto.UpdatePatientDto;
import xyz.eliabdiel.service.patient.PatientService;

import java.util.UUID;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<?> getAllPatient(@PageableDefault(size = 5, sort = {"name"}) Pageable pageable) {
        return patientService.getAllPatient(pageable);
    }

    @PostMapping
    public ResponseEntity<?> registerPatient(@RequestBody @Valid RegisterPatient registerPatient,
                                             UriComponentsBuilder uriBuilder) {
        return patientService.registerPatient(registerPatient, uriBuilder);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid UpdatePatientDto updatePatient) {
        return patientService.updatePatient(updatePatient);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<?> delete(@PathVariable UUID patientId) {
        return patientService.deletePatient(patientId);
    }
}