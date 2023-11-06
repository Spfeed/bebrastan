package org.example.bebra.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.bebra.model.Doctor;
import org.example.bebra.model.MedicalHistory;
import org.example.bebra.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public void addDoctor(Doctor doctor){
        if (doctor.getName() == null || doctor.getName().isEmpty()) {
            throw new IllegalArgumentException("Наименование визита это обязательное поле");
        }
        if (doctor.getJob() == null || doctor.getJob().isEmpty()) {
            throw new IllegalArgumentException("Описание это обязательное поле");
        }
        doctorRepository.save(doctor);
    }

    public Doctor findBySurname(String surname){
        Doctor doctor = doctorRepository.findBySurname(surname);
        if (doctor==null){
            return null;
        }
        return doctor;
    }

    public void updateDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public void deleteDoctorById(Long id) {
        // Проверяем, существует ли питомец с указанным ID
        if (doctorRepository.existsById(id)) {
            // Если питомец существует, удаляем его
            doctorRepository.deleteById(id);
        } else {
            // Если питомец не существует, можно бросить исключение или выполнить другую логику обработки
            throw new EntityNotFoundException("Доктор с ID " + id + " не найден");
        }
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll(); // Этот метод вернет список всех докторов из базы данных
    }
}
