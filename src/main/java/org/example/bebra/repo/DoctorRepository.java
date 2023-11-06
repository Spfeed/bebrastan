package org.example.bebra.repo;

import org.example.bebra.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findBySurname(String surname);
}
