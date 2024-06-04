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

import com.ProyectoVehiculos.app.Entity.Mecanico;
import com.ProyectoVehiculos.app.Exception.NotFoundException;
import com.ProyectoVehiculos.app.Repository.MecanicoRepository;


@Controller
@RequestMapping(value="/mecanico")
public class MecanicoWebController {
	@Autowired
    private MecanicoRepository mecanicoRepository;

    @GetMapping("/")
    public String mecanicoListTemplate(Model model) {
        model.addAttribute("mecanicos", mecanicoRepository.findAll());
        return "mecanico-list";
    }

    @GetMapping("/new")
    public String mecanicosNewTemplate(Model model) {
        model.addAttribute("mecanicos", new Mecanico());
        return "mecanico-form";
    }

    @GetMapping("/edit/{id}")
    public String mecanicosEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("mecanicos", mecanicoRepository.findById(id).orElseThrow(() -> new NotFoundException("mecanico no encontrado")));
        return "mecanico-form";
    }

    @PostMapping("/save")
    public String mecanicosSaveProcess(@ModelAttribute("mecanico") Mecanico mecanico) {
        if (mecanico.getId() == null || mecanico.getId().isEmpty()) {
            mecanico.setId(null);
        }
        mecanicoRepository.save(mecanico);
        return "redirect:/mecanico/";
    }

    @GetMapping("/delete/{id}")
    public String mecanicosDeleteProcess(@PathVariable("id") String id) {
        mecanicoRepository.deleteById(id);
        return "redirect:/mecanico/";
    }
    
    @PostMapping("/ingresar")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		// Verificar las credenciales
		System.out.println("Mecanico: " + email + " Password:" + password);

		List<Mecanico> mecanicoList = mecanicoRepository.findAll();
		System.out.println(mecanicoList.get(0).getEmail());
		 Mecanico mecanico = mecanicoRepository.findByEmailAndPassword(email, password);
		if (mecanico != null) {
			// Inicio de sesión exitoso, redirigir a la página de inicio
			System.out.println("Mecanico: " + mecanico.getEmail() + " Password:" + mecanico.getPassword());
			return "homemecanico"; // Nombre de la página de inicio (por ejemplo, "inicio.html")
		} else {
			// Inicio de sesión fallido, mostrar mensaje de error en la página de inicio
			model.addAttribute("authenticationFailed", true);
			model.addAttribute("errorMessage", "Usuario o contraseña incorrectos");
			return "redirect:/login";
		}
	
    }
}
