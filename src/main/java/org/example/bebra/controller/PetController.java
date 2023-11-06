package org.example.bebra.controller;
import org.example.bebra.model.Doctor;
import org.example.bebra.model.MedicalHistory;
import org.example.bebra.model.Owner;
import org.example.bebra.services.DoctorService;
import org.example.bebra.services.MedicalHistoryService;
import org.example.bebra.services.OwnerService;
import org.example.bebra.services.PetService;
import org.example.bebra.sochniy.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;

@Controller
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @GetMapping("/addPet")
    public String showAddPetForm(Model model) {
        // Создаем объект питомца для формы
        Pet pet = new Pet();
        model.addAttribute("pet", pet);
        return "addPetForm"; // Это представление (шаблон HTML), которое будет отображаться для добавления питомца
    }

    @GetMapping("/pets")
    public String showPets(Model model){
        List<Pet> pets = petService.getAllPets();
        model.addAttribute("pets", pets);
        return "petsList";
    }

    @PostMapping("/addPet")
    public String addPet(@ModelAttribute("pet") @Valid Pet pet, BindingResult bindingResult, @RequestParam("ownerSurname") String ownerSurname, @RequestParam("doctorSurname") String doctorSurname, @RequestParam("medicalHistoryId") Long medicalHistoryId) {
        if (bindingResult.hasErrors()) {
            return "addPetForm"; // Если есть ошибки валидации, вернуть обратно к форме
        }

        Owner owner = ownerService.findBySurname(ownerSurname);
        Doctor doctor = doctorService.findBySurname(doctorSurname);
        MedicalHistory medicalHistory = medicalHistoryService.findById(medicalHistoryId);

        pet.setOwner(owner);
        pet.setDoctor(doctor);
        pet.setMedicalHistory(medicalHistory);

        medicalHistory.setPet(pet);
        owner.getPets().add(pet);

        // Вызываем сервис для добавления питомца
        petService.addNewPet(pet);
        medicalHistoryService.addMedicalHistory(medicalHistory);
        ownerService.addOwner(owner);

        return "redirect:/pets"; // Перенаправляем на страницу со списком питомцев или другую нужную страницу
    }

    @GetMapping("/editPet/{name}")
    public String showEditPetForm(@PathVariable String name, Model model) {
        Pet pet = petService.findPetByName(name);
        model.addAttribute("pet", pet);
        return "editPetForm";
    }

    @PostMapping("/editPet/{name}")
    public String editPet(@PathVariable String name, @ModelAttribute("pet") @Valid Pet pet, BindingResult bindingResult, Owner owner, Doctor doctor) {
        if (bindingResult.hasErrors()) {
            return "editPetForm"; // Вернуть обратно к форме редактирования, если есть ошибки
        }
        System.out.print(name);
        Pet existingPet = petService.findPetByName(name);
        existingPet.setName(pet.getName());
        existingPet.setKind(pet.getKind());
        existingPet.setAge(pet.getAge());
        existingPet.setOwner(pet.getOwner());
        existingPet.setDoctor(pet.getDoctor());
        petService.updatePet(pet);
        return "redirect:/pets"; // Перенаправить на страницу со списком питомцев или другую нужную страницу
    }

    @GetMapping("/deletePet/{id}")
    public String deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
        return "redirect:/pets"; // Перенаправить на страницу со списком питомцев или другую нужную страницу
    }


}

