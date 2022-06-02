package com.unla.OO2.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unla.OO2.entity.Espacio;
import com.unla.OO2.repository.EspacioRepository;

@Service
public class EspacioServiceImpl implements EspacioService {

	@Autowired
	private EspacioRepository espacioRepository;

	@Override
	public List<Espacio> getAll(){
		return espacioRepository.findAll();
	}

	@Override
	public List<Espacio> listAll(String idAula, String turno, String fecha){
		if(idAula != null && turno !=null && fecha!=null) {
			return espacioRepository.traer(idAula, turno, fecha);
		}else {
			return (List<Espacio>)espacioRepository.findAll();
		}
	}

	@Override
	public boolean insertOrUpdate(Espacio espacio) {
		try {
			espacioRepository.save(espacio);
			return true;
		}catch (Exception he){
			return false;
		}
	}

	@Override
	public boolean cambiarLibre(Espacio espacio) {
		espacio.setLibre(false);
		return insertOrUpdate(espacio);
	}

	@Override
	public List<Espacio> traerConAulaTradicionalPorTurnoYFechaLibres(char turno, String fecha) {
		return espacioRepository.traerConAulaTradicionalPorTurnoYFechaLibres(turno, fecha);
	}

	@Override
	public List<Espacio> traerConAulaLaboratorioPorTurnoYFechaLibres(char turno, String fecha) {
		return espacioRepository.traerConAulaLaboratorioPorTurnoYFechaLibres(turno, fecha);
	}

	@Override
	public List<Espacio> traerConAulaTradicionalPorTurnoYEntreFechasLibres(char turno, String fechaI, String fechaF) {
		return espacioRepository.traerConAulaTradicionalPorTurnoYEntreFechasLibres(turno, fechaI, fechaF);
	}

	@Override
	public List<int[]> traerSumaYIdAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(char turno, String fechaI,
			String fechaF) {
		return espacioRepository.traerSumaYIdAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(turno, fechaI, fechaF);
	}
	
	
	@Override
	public Espacio traerConAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres( int aulaId, char turno, String fechaI,
			String fechaF) {
		return espacioRepository.traerConAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(aulaId, turno, fechaI, fechaF);
	}
	
	@Override
	public List<int[]> traerSumaYIdAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(char turno, String fechaI,
			String fechaF) {
		return espacioRepository.traerSumaYIdAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(turno, fechaI, fechaF);
	}

	@Override
	public Espacio traerConAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(int aulaId, char turno, String fechaI,
			String fechaF) {
		return espacioRepository.traerConAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(aulaId, turno, fechaI, fechaF);
	}

	@Override
	public Espacio traerPorAulaFechaTurnoLibre(int idAula, char turno, String fecha) {
		return espacioRepository.traerPorAulaFechaTurnoLibre(idAula, turno, fecha);
	}

	@Override
	public Espacio findById(int id) {
		return espacioRepository.findById(id).orElse(null);
	}

	

	

	
	
	
}