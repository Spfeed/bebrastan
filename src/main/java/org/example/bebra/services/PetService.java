package org.example.bebra.services;
import jakarta.persistence.EntityNotFoundException;
import org.example.bebra.sochniy.Pet;
import org.example.bebra.repo.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository){
        this.petRepository=petRepository;
    }

    public void addNewPet(Pet pet){
        if (pet.getName() == null || pet.getName().isEmpty()) {
            throw new IllegalArgumentException("Имя питомца обязательное поле");
        }

        if (pet.getAge() <= 0) {
            throw new IllegalArgumentException("Возраст питомца должен быть положительным числом");
        }

        petRepository.save(pet);
    }

    public Pet findPetByName(String name) {
        return petRepository.findByName(name);
    }

    public void updatePet(Pet pet) {
        petRepository.save(pet);
    }

    public void deletePetById(Long id) {
        // Проверяем, существует ли питомец с указанным ID
        if (petRepository.existsById(id)) {
            // Если питомец существует, удаляем его
            petRepository.deleteById(id);
        } else {
            // Если питомец не существует, можно бросить исключение или выполнить другую логику обработки
            throw new EntityNotFoundException("Питомец с ID " + id + " не найден");
        }
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll(); // Этот метод вернет список всех питомцев из базы данных
    }
}
