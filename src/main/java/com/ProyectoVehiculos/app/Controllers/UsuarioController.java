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

import com.ProyectoVehiculos.app.Entity.Usuario;

import com.ProyectoVehiculos.app.Repository.UsuarioRepository;


@RestController
@RequestMapping(value="api/usuario")
public class UsuarioController {
	@Autowired
    private UsuarioRepository usuarioRepository;

    
    @PostMapping("/")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los Usuarios
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Endpoint para obtener un Usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable("id") String id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    // Endpoint para actualizar un Usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("id") String id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        usuario.setId(id);
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    /*Endpoint para eliminar un Usuario*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("id") String id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.delete(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
}
