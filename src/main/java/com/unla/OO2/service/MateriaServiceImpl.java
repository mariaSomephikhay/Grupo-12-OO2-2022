package com.unla.OO2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unla.OO2.entity.Materia;
import com.unla.OO2.repository.MateriaRepository;

@Service("materiaService")
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private MateriaRepository materiaRepository;

	@Override
	public List<Materia> getAll() {
		return materiaRepository.findAll();
	}

	@Override
	public boolean insertOrUpdate(Materia materia) {
		try {
			materiaRepository.save(materia);
			return true;
		}catch (Exception he){
			return false;
		}
	}

	@Override
	public Materia findById(int id) {
		return materiaRepository.findById(id).orElse(null);
	}

	@Override
	public boolean remove(int id) {
		try {
			materiaRepository.deleteById(id);
			return true;
		}catch(Exception he) {
			return false;
		}
	}

}