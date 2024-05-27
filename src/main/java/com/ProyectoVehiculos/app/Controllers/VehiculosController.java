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

import com.ProyectoVehiculos.app.Entity.Vehiculo;
import com.ProyectoVehiculos.app.Repository.VehiculoRepository;


@RestController
@RequestMapping(value="api/vehiculo")
public class VehiculosController {

	@Autowired
    private VehiculoRepository vehiculoRepository;

    
    @PostMapping
    public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoRepository.save(vehiculo);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los Usuarios
    @GetMapping("/")
    public ResponseEntity<List<Vehiculo>> obtenerTodosLosVehiculos() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }

    // Endpoint para obtener un Usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorId(@PathVariable("id") String id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
        if (vehiculo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehiculo, HttpStatus.OK);
    }

    // Endpoint para actualizar un Usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable("id") String id, @RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoExistente = vehiculoRepository.findById(id).orElse(null);
        if (vehiculoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        vehiculo.setId(id);
        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);
        return new ResponseEntity<>(vehiculoActualizado, HttpStatus.OK);
    }

    /*Endpoint para eliminar un Usuario*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Vehiculo> eliminarVehiculo(@PathVariable("id") String id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
        if (vehiculo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vehiculoRepository.delete(vehiculo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	
}
