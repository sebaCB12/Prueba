package com.ProyectoVehiculos.app.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ProyectoVehiculos.app.Entity.Mantenimiento;

import com.ProyectoVehiculos.app.Exception.NotFoundException;
import com.ProyectoVehiculos.app.Repository.MantenimientoRepository;


@Controller
@RequestMapping(value="/mantenimiento")

public class MantenimientoWebController {

	@Autowired
    private MantenimientoRepository mantenimientoRepository;

    @GetMapping("/")
    public String manteListTemplate(Model model) {
        model.addAttribute("mantenimiento", mantenimientoRepository.findAll());
        return "mantenimiento-list";
    }

    @GetMapping("/new")
    public String manteNewTemplate(Model model) {
        model.addAttribute("mantenimiento", new Mantenimiento());
        return "mantenimiento-form";
    }

    @GetMapping("/edit/{id}")
    public String manteEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("mantenimiento", mantenimientoRepository.findById(id).orElseThrow(() -> new NotFoundException("mantenimiento no encontrado")));
        return "mantenimiento-form";
    }

    @PostMapping("/save")
    public String manteSaveProcess(@ModelAttribute("mantenimiento") Mantenimiento mantenimiento) {
        if (mantenimiento.getId() == null || mantenimiento.getId().isEmpty()) {
            mantenimiento.setId(null);
        }
        mantenimientoRepository.save(mantenimiento);
        return "redirect:/mantenimiento/";
    }

    @GetMapping("/delete/{id}")
    public String manteDeleteProcess(@PathVariable("id") String id) {
        mantenimientoRepository.deleteById(id);
        return "redirect:/mantenimiento/";
    }
    
  
  
	
}
