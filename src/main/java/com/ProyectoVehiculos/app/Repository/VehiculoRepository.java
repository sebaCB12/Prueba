package com.ProyectoVehiculos.app.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.ProyectoVehiculos.app.Entity.Vehiculo;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String>{
	List<Vehiculo> findByPropietarioId(String propietarioId);
}
