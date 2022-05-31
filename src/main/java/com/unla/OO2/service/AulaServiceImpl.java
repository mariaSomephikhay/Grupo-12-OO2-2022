package com.unla.OO2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unla.OO2.repository.AulaRepository;
import com.unla.OO2.entity.Aula;

@Service("aulaService")
public class AulaServiceImpl implements AulaService{

	@Autowired
	private AulaRepository aulaRepository;

	@Override
	public List<Aula> getAll(){
		return aulaRepository.findAll();
	}

	@Override
	public boolean insertOrUpdate(Aula aula) {
		try {
			aulaRepository.save(aula);
			return true;
		}catch (Exception he){
			return false;
		}
	}

	@Override
	public Aula findById(int id) {
		return aulaRepository.findById(id).orElse(null);
	}

	@Override
	public boolean remove(int id) {
		try {
			aulaRepository.deleteById(id);
			return true;
		}catch(Exception he) {
			return false;
		}
	}
}