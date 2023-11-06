package org.example.bebra.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MedicalHistoryControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        // Логируйте ошибку
        ex.printStackTrace();

        // Создайте объект ModelAndView
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex.getMessage());

        return modelAndView;
    }
}
