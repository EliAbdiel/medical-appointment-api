package xyz.eliabdiel.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.eliabdiel.model.patient.dto.RegisterPatient;
import xyz.eliabdiel.service.patient.PatientService;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<?> registerPatient(@RequestBody @Valid RegisterPatient registerPatient,
                                             UriComponentsBuilder uriBuilder) {
        return patientService.registerPatient(registerPatient, uriBuilder);
    }
}
