package xyz.eliabdiel.model.medical;

import jakarta.persistence.*;
import lombok.*;
import xyz.eliabdiel.model.address.Address;
import xyz.eliabdiel.model.medical.dto.RegisterMedical;

@Entity
@Table(name = "medics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class MedicalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String document;

    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @Embedded
    private Address address;

    public MedicalEntity(RegisterMedical data) {
        this.name = data.name();
        this.email = data.email();
        this.telephone = data.telephone();
        this.document = data.document();
        this.specialization = data.specialization();
        this.address = new Address(data.address());
        this.active = true;
    }

    void deactivateMedical() {
        this.active = false;
    }
}