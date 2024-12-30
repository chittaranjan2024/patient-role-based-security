package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.test.entity.Patient;
import org.test.entity.Role;
import org.test.repositories.PatientRepository;
import org.test.repositories.RoleRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password) {
        Patient patient = new Patient();
        patient.setUsername(username);
        patient.setPassword(passwordEncoder.encode(password));

        Role patientRole = roleRepository.findByName("ROLE_PATIENT")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        patient.getRoles().add(patientRole);

        patientRepository.save(patient);
    }
}
