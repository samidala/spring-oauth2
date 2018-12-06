package project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.model.Patient;
import project.model.PatientPayload;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PatientService {

    Optional<Patient> getPatientById(int id);

    Page<Patient> fetchAllPatients(Pageable pageable);

    Optional<Patient> createPatient(PatientPayload patientPayload);

    Patient updatePatient(int id, String firstName, String lastName, String gender, String bloodGroup);

    boolean deletePatient(int id);

    List<Patient> searchPatient(String query);
}
