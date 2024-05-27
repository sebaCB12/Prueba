package com.ProyectoVehiculos.app.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("")
public class LoginTemplateController {

	
	@GetMapping("/")
	public String HomeTemplate(Model model) {
		return "index";
	}

	@GetMapping("/login")
	public String LoginTemplate(Model model) {
		return "login-general";
	}
	
}
