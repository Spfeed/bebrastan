package org.example.bebra.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.bebra.model.Owner;
import org.example.bebra.repo.OwnerRepository;
import org.example.bebra.sochniy.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public void addOwner(Owner owner){
        if (owner.getName() == null || owner.getName().isEmpty()) {
            throw new IllegalArgumentException("Имя хозяина обязательное поле");
        }
        if (owner.getSurname() == null || owner.getSurname().isEmpty()) {
            throw new IllegalArgumentException("Фамилия хозяина обязательное поле");
        }
        ownerRepository.save(owner);
    }

    public Owner findBySurname(String surname){
        Owner owner = ownerRepository.findBySurname(surname);
        if (owner==null){
            return null;
        }
        return owner;
    }

    public void deleteOwnerById(Long id) {
        // Проверяем, существует ли питомец с указанным ID
        if (ownerRepository.existsById(id)) {
            // Если питомец существует, удаляем его
            ownerRepository.deleteById(id);
        } else {
            // Если питомец не существует, можно бросить исключение или выполнить другую логику обработки
            throw new EntityNotFoundException("Хозяин с ID " + id + " не найден");
        }
    }

    public void updateOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll(); // Этот метод вернет список всех хозяев из базы данных
    }
}
