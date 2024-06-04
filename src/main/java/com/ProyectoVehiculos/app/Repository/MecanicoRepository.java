package com.ProyectoVehiculos.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ProyectoVehiculos.app.Entity.Mecanico;


public interface MecanicoRepository extends MongoRepository<Mecanico, String> {
	
	Mecanico findByEmailAndPassword(String email, String password);

}
