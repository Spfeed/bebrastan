package org.example.bebra.controller;

import org.example.bebra.services.OwnerService;
import org.example.bebra.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.example.bebra.model.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.*;
import java.util.List;

@Controller
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    @GetMapping("/addOwner")
    public String showAddOwnerForm(Model model) {
        // Создайте объект Owner для формы
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return "addOwnerForm"; // Вернуть представление для добавления владельца
    }

    @PostMapping("/addOwner")
    public String addOwner(@ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addOwnerForm"; // Если есть ошибки валидации, вернуть обратно к форме
        }

        // Вызовите сервис для добавления владельца
        ownerService.addOwner(owner);

        return "redirect:/owners"; // Перенаправьте на страницу со списком владельцев или другую нужную страницу
    }

    @GetMapping("/owners")
    public String showOwners(Model model) {
        List<Owner> owners = ownerService.getAllOwners();
        model.addAttribute("owners", owners);
        return "owners"; // Представление со списком владельцев
    }

    @GetMapping("/editOwner/{surname}")
    public String showEditOwnerForm(@PathVariable String surname, Model model){
        Owner owner = ownerService.findBySurname(surname);
        model.addAttribute("owner",owner);
        return "editOwnerForm";
    }

    @PostMapping("/editOwner/{surname}")
    public String editOwner(@PathVariable String surname, @ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "editOwnerForm"; // Вернуть обратно к форме редактирования, если есть ошибки
        }
        Owner existingOwner = ownerService.findBySurname(surname);
        existingOwner.setName(owner.getName());
        existingOwner.setSurname(owner.getSurname());
        existingOwner.setFathername(owner.getFathername());

        ownerService.updateOwner(existingOwner);
        return "redirect:/owners"; //
    }

    @GetMapping("/deleteOwner/{id}")
    public String deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwnerById(id);
        return "redirect:/owners";
    }

    // Другие методы для обработки действий с владельцами
}

