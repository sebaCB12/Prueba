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

import com.ProyectoVehiculos.app.Entity.Mantenimiento;

import com.ProyectoVehiculos.app.Repository.MantenimientoRepository;

@RestController
@RequestMapping(value="api/mantenimiento")
public class MantenimientoController {
	
	@Autowired
    private MantenimientoRepository manteRepository;

    
    @PostMapping("/")
    public ResponseEntity<Mantenimiento> crearMantenimiento(@RequestBody Mantenimiento mantenimiento) {
        Mantenimiento nuevoMantenimiento = manteRepository.save(mantenimiento);
        return new ResponseEntity<>(nuevoMantenimiento, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los Usuarios
    @GetMapping("/")
    public ResponseEntity<List<Mantenimiento>> obtenerTodosLosMantenimientos() {
        List<Mantenimiento> mantenimiento = manteRepository.findAll();
        return new ResponseEntity<>(mantenimiento, HttpStatus.OK);
    }

    // Endpoint para obtener un Usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento> obtenerMantenimientoPorId(@PathVariable("id") String id) {
        Mantenimiento mantenimiento = manteRepository.findById(id).orElse(null);
        if (mantenimiento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mantenimiento, HttpStatus.OK);
    }

    // Endpoint para actualizar un Usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Mantenimiento> actualizarMantenimiento(@PathVariable("id") String id, @RequestBody Mantenimiento mantenimiento) {
        Mantenimiento manteExistente = manteRepository.findById(id).orElse(null);
        if (manteExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        mantenimiento.setId(id);
        Mantenimiento manteActualizado = manteRepository.save(mantenimiento);
        return new ResponseEntity<>( manteActualizado, HttpStatus.OK);
    }

    /*Endpoint para eliminar un Usuario*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Mantenimiento> eliminarMantenimiento(@PathVariable("id") String id) {
        Mantenimiento mantenimiento = manteRepository.findById(id).orElse(null);
        if (mantenimiento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        manteRepository.delete(mantenimiento);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	
	
	
	
	
}
