package com.unla.OO2.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unla.OO2.entity.Espacio;

public interface EspacioRepository extends JpaRepository<Espacio, Integer>{
	@Query(value = "select * from espacio inner join aula on espacio.aula_id=aula.id where aula.id= :idAula and espacio.turno=:turno and espacio.fecha=:fecha", nativeQuery = true)
	List<Espacio> traer(@Param("idAula") String idAula, @Param("turno") String turno, @Param("fecha") String fecha);
}
