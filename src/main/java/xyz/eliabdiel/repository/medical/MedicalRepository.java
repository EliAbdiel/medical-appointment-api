package xyz.eliabdiel.repository.medical;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.eliabdiel.model.medical.MedicalEntity;

@Repository
public interface MedicalRepository extends JpaRepository<MedicalEntity, Long> {

    Page<MedicalEntity> findByActiveTrue(Pageable pageable);

    @Query("""
            SELECT m.active
            FROM MedicalEntity m
            WHERE m.id = :medicalId
            """)
    boolean findActiveById(Long medicalId);
}