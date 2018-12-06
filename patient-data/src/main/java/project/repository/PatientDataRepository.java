package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.Patient;

import java.util.List;

@Repository
public interface PatientDataRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByFirstNameContaining(String query);
}
