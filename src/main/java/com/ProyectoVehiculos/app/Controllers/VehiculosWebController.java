package com.ProyectoVehiculos.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ProyectoVehiculos.app.Entity.Vehiculo;
import com.ProyectoVehiculos.app.Exception.NotFoundException;

import com.ProyectoVehiculos.app.Repository.VehiculoRepository;

@Controller
@RequestMapping(value="vehiculo")
public class VehiculosWebController {

	@Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping("/")
    public String vehiculosListTemplate(Model model) {
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "vehiculo-list";
    }

    @GetMapping("/new")
    public String vehiculosNewTemplate(Model model) {
        model.addAttribute("vehiculos", new Vehiculo());
        return "vehiculo-form";
    }

    @GetMapping("/edit/{id}")
    public String vehiculosEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("vehiculos", vehiculoRepository.findById(id).orElseThrow(() -> new NotFoundException("vehiculo no encontrado")));
        return "vehiculo-form";
    }

    @PostMapping("/save")
    public String vehiculoSaveProcess(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        if (vehiculo.getId() == null || vehiculo.getId().isEmpty()) {
            vehiculo.setId(null);
        }
        vehiculoRepository.save(vehiculo);
        return "redirect:/vehiculo/";
    }

    @GetMapping("/delete/{id}")
    public String vehiculoDeleteProcess(@PathVariable("id") String id) {
        vehiculoRepository.deleteById(id);
        return "redirect:/vehiculo/";
    }
	
	
	
}
