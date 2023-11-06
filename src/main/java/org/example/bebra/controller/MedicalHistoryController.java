package org.example.bebra.controller;

import org.example.bebra.model.Doctor;
import org.example.bebra.model.MedicalHistory;
import org.example.bebra.services.MedicalHistoryService;
import org.example.bebra.services.PetService;
import org.example.bebra.sochniy.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MedicalHistoryController {
    @Autowired
    private MedicalHistoryService medicalHistoryService;
    @Autowired
    private PetService petService;

    @GetMapping("/addMedicalHistory")
    public String showAddMedicalHistoryForm(Model model){
        MedicalHistory medicalHistory = new MedicalHistory();
        model.addAttribute("medicalHistory",medicalHistory);
        return "addMedicalHistoryForm";
    }

    @PostMapping("/addMedicalHistory")
    public String addMedicalHistory(@ModelAttribute("medicalHistory") @Valid MedicalHistory medicalHistory, BindingResult bindingResult, Model model){
        System.out.println("Вызан метод добавления медицинской истории");
        if (bindingResult.hasErrors()){
            System.out.println("Ошибки при добавлении");
            model.addAttribute("errors", bindingResult.getAllErrors());
            System.out.print("Ошибки: " + model.getAttribute("errors"));
            return "addMedicalHistoryForm";
        }

        medicalHistoryService.addMedicalHistory(medicalHistory);

        return "redirect:/medicalHistories";
    }

    @GetMapping("/medicalHistories")
    public String showMedicalHistories(Model model){
        List<MedicalHistory> medicalHistories = medicalHistoryService.getAllMedicalHistories();
        model.addAttribute("medicalHistories",medicalHistories);
        return "medicalHistoriesList";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        // Логируйте ошибку
        ex.printStackTrace();
        // Возвращайте страницу с ошибкой
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }

    @GetMapping("/editMedicalHistory/{id}")
    public String showEditMedicalHistoryForm (@PathVariable Long id, Model model){
        MedicalHistory medicalHistory = medicalHistoryService.findById(id);
        model.addAttribute("medicalHistory",medicalHistory);
        return "editMedicalHistoryForm";
    }

    @PostMapping("/editMedicalHistory/{id}")
    public String editMedicalHistory (@PathVariable Long id, @ModelAttribute("medicalHistory") @Valid MedicalHistory medicalHistory, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "editMedicalHistoryForm"; // Вернуть обратно к форме редактирования, если есть ошибки
        }
        MedicalHistory existingMedicalHistory = medicalHistoryService.findById(id);
        existingMedicalHistory.setDate(medicalHistory.getDate());
        existingMedicalHistory.setType(medicalHistory.getType());
        existingMedicalHistory.setDescription(medicalHistory.getDescription());
        existingMedicalHistory.setPet(medicalHistory.getPet());

        medicalHistoryService.updateMedicalHistory(existingMedicalHistory);
        return "redirect:/medicalHistories"; //
    }

    @GetMapping("/deleteMedicalHistory/{id}")
    public String deleteMedicalHistory(@PathVariable Long id) {
        medicalHistoryService.deleteMedicalHistoryById(id);
        return "redirect:/medicalHistories"; // Перенаправить на страницу со списком питомцев или другую нужную страницу
    }

}
