package com.ProyectoVehiculos.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoVehiculos.app.Entity.Mecanico;

import com.ProyectoVehiculos.app.Repository.MecanicoRepository;

@RestController
@RequestMapping(value="api/mecanico")
public class MecanicoController {

		@Autowired
	    private MecanicoRepository mecanicoRepository;

	    
	    @PostMapping("/")
	    public ResponseEntity<Mecanico> crearMecanico(@RequestBody Mecanico mecanico) {
	        Mecanico nuevoMecanico = mecanicoRepository.save(mecanico);
	        return new ResponseEntity<>(nuevoMecanico, HttpStatus.CREATED);
	    }

	    // Endpoint para obtener todos los Usuarios
	    @GetMapping("/")
	    public ResponseEntity<List<Mecanico>> obtenerTodosLosMecanicos() {
	        List<Mecanico> mecanicos = mecanicoRepository.findAll();
	        return new ResponseEntity<>(mecanicos, HttpStatus.OK);
	    }

	    // Endpoint para obtener un Usuario por su ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Mecanico> obtenerMecanicoPorId(@PathVariable("id") String id) {
	        Mecanico mecanico = mecanicoRepository.findById(id).orElse(null);
	        if (mecanico == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(mecanico, HttpStatus.OK);
	    }

	    // Endpoint para actualizar un Usuario existente
	    @PutMapping("/{id}")
	    public ResponseEntity<Mecanico> actualizarMecanico(@PathVariable("id") String id, @RequestBody Mecanico mecanico) {
	        Mecanico mecanicoExistente = mecanicoRepository.findById(id).orElse(null);
	        if (mecanicoExistente == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        
	        mecanico.setId(id);
	        Mecanico mecanicoActualizado = mecanicoRepository.save(mecanico);
	        return new ResponseEntity<>(mecanicoActualizado, HttpStatus.OK);
	    }

	    /*Endpoint para eliminar un Usuario*/
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Mecanico> eliminarMecanico(@PathVariable("id") String id) {
	        Mecanico mecanico= mecanicoRepository.findById(id).orElse(null);
	        if (mecanico == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        mecanicoRepository.delete(mecanico);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	
	
}
