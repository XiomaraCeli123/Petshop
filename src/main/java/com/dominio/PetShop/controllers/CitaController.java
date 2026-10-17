package com.dominio.PetShop.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.validation.Valid;

import com.dominio.PetShop.models.Cita;
import com.dominio.PetShop.repository.CitaRepository;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository repo;

    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<Cita> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        Cita cita = new Cita();
        model.addAttribute("productDto", cita);
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute("productDto") Cita cita,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "products/CreateProduct";
        }

        repo.save(cita);
        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam long id) {
        try {
            Cita cita = repo.findById(id).orElseThrow();
            model.addAttribute("product", cita);
            model.addAttribute("CitaDto", cita);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }
        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(
            Model model,
            @RequestParam long id,
            @Valid @ModelAttribute("productDto") Cita citaActualizada,
            BindingResult result
    ) {
        try {
            Cita cita = repo.findById(id).orElseThrow();
            model.addAttribute("product", cita);

            if (result.hasErrors()) {
                return "products/EditProduct";
            }

            // Actualizar campos
            cita.setNombreMascota(citaActualizada.getNombreMascota());
            cita.setNombreDueno(citaActualizada.getNombreDueno());
            cita.setCorreo(citaActualizada.getCorreo());
            cita.setTelefono(citaActualizada.getTelefono());
            cita.setDireccion(citaActualizada.getDireccion());
            cita.setFechaCita(citaActualizada.getFechaCita());
            cita.setHoraCita(citaActualizada.getHoraCita());
            cita.setServicio(citaActualizada.getServicio());
            cita.setObservaciones(citaActualizada.getObservaciones());

            repo.save(cita);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam long id) {
        try {
            Cita cita = repo.findById(id).orElseThrow();
            repo.delete(cita);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/ver")
    public String verProducto(@RequestParam long id, Model model) {
        try {
            Cita cita = repo.findById(id).orElseThrow();
            model.addAttribute("product", cita);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }
        return "products/VerProduct";
    }


    @PostMapping("/guardar")
    public String guardarCita(
    @RequestParam String nombreDueno,
    @RequestParam String nombreMascota,
    @RequestParam String correo,
    @RequestParam String telefono,
    @RequestParam String direccion,
    @RequestParam String fechaCita,
    @RequestParam String horaCita,
    @RequestParam String servicio,
    @RequestParam(required = false) String observaciones
) {
    Cita nuevaCita = new Cita();
    nuevaCita.setNombreDueno(nombreDueno);
    nuevaCita.setNombreMascota(nombreMascota);
    nuevaCita.setCorreo(correo);
    nuevaCita.setTelefono(telefono);
    nuevaCita.setDireccion(direccion);
    nuevaCita.setFechaCita(LocalDate.parse(fechaCita)); // convertir String a LocalDate
    nuevaCita.setHoraCita(LocalTime.parse(horaCita));   // convertir String a LocalTime
    nuevaCita.setServicio(servicio);
    nuevaCita.setObservaciones(observaciones);

    repo.save(nuevaCita);

    return "redirect:/products"; // redirige a la lista o donde prefieras
}

}
