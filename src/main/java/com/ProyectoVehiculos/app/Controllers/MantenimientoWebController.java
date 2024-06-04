package com.ProyectoVehiculos.app.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProyectoVehiculos.app.Entity.Mantenimiento;
import com.ProyectoVehiculos.app.Entity.Usuario;
import com.ProyectoVehiculos.app.Exception.NotFoundException;
import com.ProyectoVehiculos.app.Repository.MantenimientoRepository;
import com.ProyectoVehiculos.app.Repository.MecanicoRepository;
import com.ProyectoVehiculos.app.Repository.VehiculoRepository;


@Controller
@RequestMapping(value="mantenimiento")
public class MantenimientoWebController {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private MecanicoRepository mecanicoRepository;

    @GetMapping("/")
    public String manteListTemplate(Model model) {
        model.addAttribute("mantenimientos", mantenimientoRepository.findAll());
        return "mantenimiento-list";
    }

    @GetMapping("/new")
    public String manteNewTemplate(Model model) {
        model.addAttribute("mantenimientos", new Mantenimiento());
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        model.addAttribute("mecanicos", mecanicoRepository.findAll());
        return "mantenimiento-form";
    }

    @GetMapping("/edit/{id}")
    public String manteEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("mantenimientos", mantenimientoRepository.findById(id).orElseThrow(() -> new NotFoundException("mantenimiento no encontrado")));
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        model.addAttribute("mecanicos", mecanicoRepository.findAll());
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
    
  
  

