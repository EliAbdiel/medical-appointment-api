package xyz.eliabdiel.repository.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.eliabdiel.model.patient.PatientEntity;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {

    Page<PatientEntity> findAllByActiveTrue(Pageable pageable);

    @Query("""
            SELECT p.active
            FROM PatientEntity p
            WHERE p.id = :patientId
            """)
    boolean findActiveById(UUID patientId);
}