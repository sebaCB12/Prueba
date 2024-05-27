package com.ProyectoVehiculos.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ProyectoVehiculos.app.Entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario,String>{
	Usuario findByEmailAndPassword(String email, String password);
}
