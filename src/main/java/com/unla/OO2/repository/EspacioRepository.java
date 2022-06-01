package com.unla.OO2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.unla.OO2.entity.Espacio;

public interface EspacioRepository extends JpaRepository<Espacio, Integer>{
	@Query(value = "select * from espacio inner join aula on espacio.aula_id=aula.id where aula.numero= :idAula and espacio.turno=:turno and espacio.fecha=:fecha", nativeQuery = true)
	List<Espacio> traer(@Param("idAula") String idAula, @Param("turno") String turno, @Param("fecha") String fecha);
	
	@Query(value = "select * from espacio inner join aula on espacio.aula_id=aula.id inner join tradicional on aula.id=tradicional.id_tradicional where espacio.turno=:turno and espacio.fecha=:fecha and espacio.libre=true", nativeQuery = true)
	List<Espacio> traerConAulaTradicionalPorTurnoYFechaLibres(@Param("turno") char turno, @Param("fecha") String fecha);
	
	@Query(value = "select * from espacio inner join aula on espacio.aula_id=aula.id inner join laboratorio on aula.id=laboratorio.id_laboratorio where espacio.turno=:turno and espacio.fecha=:fecha and espacio.libre=true", nativeQuery = true)
	List<Espacio> traerConAulaLaboratorioPorTurnoYFechaLibres(@Param("turno") char turno, @Param("fecha") String fecha);

	
	@Query(value = "select * from espacio inner join aula on espacio.aula_id=aula.id inner join tradicional on aula.id=tradicional.id_tradicional where espacio.turno=:turno and espacio.fecha between :fechaI and :fechaF and espacio.libre=true", nativeQuery = true)
	List<Espacio> traerConAulaTradicionalPorTurnoYEntreFechasLibres(@Param("turno") char turno, @Param("fechaI") String fechaI, @Param("fechaF") String fechaF);

	@Query(value = "select count(*), aula_id from espacio inner join aula on espacio.aula_id=aula.id inner join tradicional on aula.id=tradicional.id_tradicional where espacio.turno=:turno and dayofweek(espacio.fecha)=dayofweek(:fechaI) and espacio.fecha between :fechaI and :fechaF and espacio.libre=true group by aula.id", nativeQuery = true)
	List<int[]> traerSumaYIdAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(@Param("turno") char turno, @Param("fechaI") String fechaI, @Param("fechaF") String fechaF);
	
	@Query(value = "select * from espacio inner join aula on espacio.aula_id=aula.id inner join tradicional on aula.id=tradicional.id_tradicional where aula.id=:aulaId and espacio.turno=:turno and dayofweek(espacio.fecha)=dayofweek(:fechaI) and espacio.fecha between :fechaI and :fechaF and espacio.libre=true group by aula.id", nativeQuery = true)
	Espacio traerConAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(@Param("aulaId") int aulaId,@Param("turno") char turno, @Param("fechaI") String fechaI, @Param("fechaF") String fechaF);
	
	@Query(value = "select count(*), aula_id from espacio inner join aula on espacio.aula_id=aula.id inner join laboratorio on aula.id=laboratorio.id_laboratorio where espacio.turno=:turno and dayofweek(espacio.fecha)=dayofweek(:fechaI) and espacio.fecha between :fechaI and :fechaF and espacio.libre=true group by aula.id", nativeQuery = true)
	List<int[]> traerSumaYIdAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(@Param("turno") char turno, @Param("fechaI") String fechaI, @Param("fechaF") String fechaF);
	
	@Query(value = "select * from espacio inner join aula on espacio.aula_id=aula.id inner join laboratorio on aula.id=laboratorio.id_laboratorio where aula.id=:aulaId and espacio.turno=:turno and dayofweek(espacio.fecha)=dayofweek(:fechaI) and espacio.fecha between :fechaI and :fechaF and espacio.libre=true group by aula.id", nativeQuery = true)
	Espacio traerConAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(@Param("aulaId") int aulaId,@Param("turno") char turno, @Param("fechaI") String fechaI, @Param("fechaF") String fechaF);

	@Query(value = "select * from espacio inner join aula on espacio.aula_id=aula.id where aula.id= :idAula and espacio.turno=:turno and espacio.fecha=:fecha and espacio.libre=true", nativeQuery = true)
	Espacio traerPorAulaFechaTurnoLibre(@Param("idAula") int idAula, @Param("turno") char turno, @Param("fecha") String fecha);
	
}