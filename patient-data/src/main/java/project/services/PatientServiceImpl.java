package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.model.Address;
import project.model.Contact;
import project.model.Patient;
import project.model.PatientPayload;
import project.repository.AddressRepository;
import project.repository.ContactRepository;
import project.repository.PatientDataRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientDataRepository patientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ContactRepository contactRepository;

    public Optional<Patient> getPatientById(int id) {
        return Optional.ofNullable(patientRepository.findOne(id));
    }

    public Page<Patient> fetchAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    public Optional<Patient> createPatient(PatientPayload patientPayload) {
        if (patientPayload.getPatient() != null) {
            System.out.println(patientPayload.getPatient());
            Patient newPatient = patientRepository.save(patientPayload.getPatient());
            if (patientPayload.getAddress() != null) {
                Address newAddress = patientPayload.getAddress();
                newAddress.setPatientId(newPatient.getId());
                System.out.println(patientPayload.getAddress());
                System.out.println(newAddress);
                addressRepository.save(newAddress);
            }
            if (patientPayload.getContact() != null) {
                Contact newContact = patientPayload.getContact();
                newContact.setPatientId(newPatient.getId());
                contactRepository.save(newContact);
            }
            return Optional.ofNullable(newPatient);
        }
        return Optional.empty();
    }

    public Patient updatePatient(int id, String firstName, String lastName, String gender, String bloodGroup) {
        Patient patient = patientRepository.findOne(id);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setGender(gender);
        patient.setBloodGroup(bloodGroup);
        patient.setUpdatedAt(new Date());
        patientRepository.save(patient);
        return patient;
    }

    public boolean deletePatient(int id) {
        try {
            patientRepository.delete(id);
            return true;
        } catch (Exception e) {
            System.out.printf(String.valueOf(e));
            return true;
        }
    }

    public List<Patient> searchPatient(String query) {
        return patientRepository.findByFirstNameContaining(query);
    }
}
