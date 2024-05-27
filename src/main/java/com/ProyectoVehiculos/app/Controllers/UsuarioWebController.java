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


import com.ProyectoVehiculos.app.Entity.Usuario;
import com.ProyectoVehiculos.app.Exception.NotFoundException;
import com.ProyectoVehiculos.app.Repository.UsuarioRepository;


@Controller
@RequestMapping(value="/usuario")
public class UsuarioWebController {

	
	@Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String usuariosListTemplate(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios-list";
    }

    @GetMapping("/new")
    public String usuariosNewTemplate(Model model) {
        model.addAttribute("usuarios", new Usuario());
        return "usuario-form";
    }

    @GetMapping("/edit/{id}")
    public String usuariosEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("usuario", usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("usuario no encontrado")));
        return "usuario-form";
    }

    @PostMapping("/save")
    public String usuariosSaveProcess(@ModelAttribute("usuario") Usuario usuario) {
        if (usuario.getId() == null || usuario.getId().isEmpty()) {
            usuario.setId(null);
        }
        usuarioRepository.save(usuario);
        return "redirect:/usuario/";
    }

    @GetMapping("/delete/{id}")
    public String usuariosDeleteProcess(@PathVariable("id") String id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuario/";
    }
    
    @PostMapping("/ingresar")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		// Verificar las credenciales
		System.out.println("Usuario: " + email + " Password:" + password);

		List<Usuario> usuarioList = usuarioRepository.findAll();
		System.out.println(usuarioList.get(0).getEmail());
		 Usuario usuario = usuarioRepository.findByEmailAndPassword(email, password);
		if (usuario != null) {
			// Inicio de sesión exitoso, redirigir a la página de inicio
			System.out.println("Usuario: " + usuario.getEmail() + " Password:" + usuario.getPassword());
			return "home"; // Nombre de la página de inicio (por ejemplo, "inicio.html")
		} else {
			// Inicio de sesión fallido, mostrar mensaje de error en la página de inicio
			model.addAttribute("authenticationFailed", true);
			model.addAttribute("errorMessage", "Usuario o contraseña incorrectos");
			return "redirect:/login";
		}
	
    }
	
}
