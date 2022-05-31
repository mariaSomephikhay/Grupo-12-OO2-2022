package com.unla.OO2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unla.OO2.entity.Profesor;
import com.unla.OO2.repository.ProfesorRepository;

@Service("profesorService")
public class ProfesorServiceImpl implements IProfesorService{

	@Autowired
	private ProfesorRepository profesorRepository;

	@Override
	public List<Profesor> getAll() {
		return profesorRepository.findAll();
	}

	@Override
	public boolean insertOrUpdate(Profesor profesor) {
		try {
			profesorRepository.save(profesor);
			return true;
		}catch (Exception he){
			return false;
		}
	}

	@Override
	public Profesor findById(int id) {
		return profesorRepository.findById(id).orElse(null);
	}

	@Override
	public boolean remove(int id) {
		try {
			profesorRepository.deleteById(id);
			return true;
		}catch(Exception he) {
			return false;
		}
	}

}