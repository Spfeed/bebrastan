package org.example.bebra.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.bebra.model.MedicalHistory;
import org.example.bebra.model.Owner;
import org.example.bebra.repo.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryService {
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    public void addMedicalHistory(MedicalHistory medicalHistory){
        if (medicalHistory.getType() == null || medicalHistory.getType().isEmpty()) {
            throw new IllegalArgumentException("Наименование визита это обязательное поле");
        }
        if (medicalHistory.getDescription() == null || medicalHistory.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Описание это обязательное поле");
        }
        medicalHistoryRepository.save(medicalHistory);
    }

    public MedicalHistory findById(Long id){
        return medicalHistoryRepository.findById(id).orElse(null);
    }

    public void updateMedicalHistory(MedicalHistory medicalHistory){
        medicalHistoryRepository.save(medicalHistory);
    }

    public void deleteMedicalHistoryById(Long id) {
        // Проверяем, существует ли питомец с указанным ID
        if (medicalHistoryRepository.existsById(id)) {
            // Если питомец существует, удаляем его
            medicalHistoryRepository.deleteById(id);
        } else {
            // Если питомец не существует, можно бросить исключение или выполнить другую логику обработки
            throw new EntityNotFoundException("Медицинская история с ID " + id + " не найдена");
        }
    }

    public List<MedicalHistory> getAllMedicalHistories() {
        return medicalHistoryRepository.findAll(); // Этот метод вернет список всех историй из базы данных
    }
}
