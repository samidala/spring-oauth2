package project.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project.model.Patient;
import project.model.PatientPayload;
import project.services.PatientService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PatientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public Page<Patient> index(Pageable pageable) {
        return patientService.fetchAllPatients(pageable);
    }

    @GetMapping("/patient/{id}")
    public Patient show(@PathVariable String id) {
        int patientId = Integer.parseInt(id);
        return patientService.getPatientById(patientId).get();
    }

    @PostMapping("/patient")
    public Patient create(@RequestBody Map<String, Object> body) {
        Gson gson = new Gson();
        System.out.println(body.get("payload"));
        PatientPayload patientPayload = gson.fromJson(body.get("payload").toString(), PatientPayload.class);
        Optional<Patient> newPatient = patientService.createPatient(patientPayload);
        return newPatient.get();
    }

    @PutMapping("/patient/{id}")
    public Patient update(@RequestBody Map<String, String> body, @PathVariable String id) {
        int patientId = Integer.parseInt(id);
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String phoneNumber = body.get("phoneNumber");
        String bloodGroup = body.get("bloodGroup");
        String gender = body.get("gender");
        return patientService.updatePatient(patientId, firstName, lastName, gender, bloodGroup);
    }

    @DeleteMapping("/patient/{id}")
    public boolean delete(@PathVariable String id) {
        int patientId = Integer.parseInt(id);
        return patientService.deletePatient(patientId);
    }

    @GetMapping("/patient/search")
    public List<Patient> search(@RequestParam(name = "query") String query) {
        return patientService.searchPatient(query);
    }
}
