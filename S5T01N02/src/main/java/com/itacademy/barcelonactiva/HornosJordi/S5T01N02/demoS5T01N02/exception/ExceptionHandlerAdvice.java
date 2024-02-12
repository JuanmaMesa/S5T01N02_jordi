package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(GameNotFoundException.class)
    public String handleGameNotFoundException(GameNotFoundException ex, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("ErrorMessage", ex.getMessage());
        return "redirect:/diceapi/v1/players";
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public String handlePlayerNotFoundException(PlayerNotFoundException ex, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("ErrorMessage", ex.getMessage());
        return "redirect:/diceapi/v1/players";
    }
}
