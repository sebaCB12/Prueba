package com.ProyectoVehiculos.app.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ProyectoVehiculos.app.Entity.Mantenimiento;

public interface MantenimientoRepository extends MongoRepository<Mantenimiento, String> {

	   List<Mantenimiento> findByVehiculoId(Long vehiculoId);
	
}
