package com.unla.OO2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unla.OO2.entity.Carrera;
import com.unla.OO2.repository.CarreraRepository;

@Service("carreraService")
public class CarreraServiceImpl implements ICarreraService{

	@Autowired
	private CarreraRepository carreraRepository;

	@Override
	public List<Carrera> getAll() {
		return carreraRepository.findAll();
	}

	@Override
	public boolean insertOrUpdate(Carrera carrera) {
		try {
			carreraRepository.save(carrera);
			return true;
		}catch (Exception he){
			return false;
		}
	}

	@Override
	public Carrera findById(int id) {
		return carreraRepository.findById(id).orElse(null);
	}

	@Override
	public boolean remove(int id) {
		try {
			carreraRepository.deleteById(id);
			return true;
		}catch(Exception he) {
			return false;
		}
	}

}