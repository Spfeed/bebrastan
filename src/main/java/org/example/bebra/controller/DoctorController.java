package org.example.bebra.controller;


import org.example.bebra.model.Doctor;
import org.example.bebra.model.MedicalHistory;
import org.example.bebra.model.Owner;
import org.example.bebra.services.DoctorService;
import org.example.bebra.sochniy.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/addDoctor")
    public String showAddDoctorForm(Model model){
        Doctor doctor = new Doctor();
        model.addAttribute("doctor",doctor);
        return "addDoctorForm";
    }

    @PostMapping("/addDoctor")
    public String addDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            System.out.println("Ошибки при добавлении");
            model.addAttribute("errors", bindingResult.getAllErrors());
            System.out.print("Ошибки: " + model.getAttribute("errors"));
            return "addDoctorForm";
        }

        doctorService.addDoctor(doctor);

        return "redirect:/doctors";
    }

    @GetMapping("/doctors")
    public String showDoctors(Model model){
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors",doctors);
        return "doctorsList";
    }

    @GetMapping("/editDoctor/{surname}")
    public String showEditDoctorForm (@PathVariable String surname, Model model){
        Doctor doctor = doctorService.findBySurname(surname);
        model.addAttribute("doctor",doctor);
        return "editDoctorForm";
    }

    @PostMapping("/editDoctor/{surname}")
    public String editDoctor (@PathVariable String surname, @ModelAttribute("doctor") @Valid Doctor doctor, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "editDoctorForm"; // Вернуть обратно к форме редактирования, если есть ошибки
        }
        Doctor existingDoctor = doctorService.findBySurname(surname);
        existingDoctor.setName(doctor.getName());
        existingDoctor.setSurname(doctor.getSurname());
        existingDoctor.setJob(doctor.getJob());

        doctorService.updateDoctor(existingDoctor);
        return "redirect:/doctors"; //
    }

    @GetMapping("/deleteDoctor/{id}")
    public String deleteOwner(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return "redirect:/owners";
    }

}
