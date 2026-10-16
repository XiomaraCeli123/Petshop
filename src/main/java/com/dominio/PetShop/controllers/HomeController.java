package com.dominio.PetShop.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Método para la página de inicio
    @GetMapping({"", "/"})
    public String home(Model model) { 
        // Verificar si el usuario está autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && !(authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken);
        
        // Pasar el estado de autenticación al modelo
        model.addAttribute("isAuthenticated", isAuthenticated);
        
        return "index";  // La vista que se va a mostrar
    }

    // Método para la página de contacto
    @GetMapping("/contact")
    public String contact(Model model) { 
        // Similar al método de home, puedes pasar el estado de autenticación si lo deseas
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && !(authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken);
        
        model.addAttribute("isAuthenticated", isAuthenticated);
        
        return "contact";  // La vista de contacto
    
  }
}