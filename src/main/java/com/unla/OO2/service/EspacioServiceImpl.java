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
	public List<Espacio> traer(String numeroAula, String turno, String fecha){
		return espacioRepository.traer(numeroAula, turno, fecha);
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
	
}