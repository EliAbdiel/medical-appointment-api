package xyz.eliabdiel.model.patient;

import jakarta.persistence.*;
import lombok.*;
import xyz.eliabdiel.model.address.Address;
import xyz.eliabdiel.model.patient.dto.RegisterPatient;
import xyz.eliabdiel.model.patient.dto.UpdatePatientDto;

import java.util.UUID;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String email;
    private String telephone;

    private String document;

    @Embedded
    private Address address;

    private boolean active;

    public PatientEntity(RegisterPatient patient) {
        this.name = patient.name();
        this.email = patient.email();
        this.telephone = patient.telephone();
        this.document = patient.document();
        this.address = new Address(patient.address());
        this.active = true;
    }

    public void updatePatient(UpdatePatientDto updatePatient) {

        if (updatePatient.name() != null) {
            this.name = updatePatient.name();
        }
        if (updatePatient.telephone() != null) {
            this.telephone = updatePatient.telephone();
        }
        if (updatePatient.address() != null) {
            this.address.updateAddress(updatePatient.address());
        }
    }

    public void deactivatePatient() {
        this.active = false;
    }
}