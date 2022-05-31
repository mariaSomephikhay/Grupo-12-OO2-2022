package com.unla.OO2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unla.OO2.entity.Catedra;
import com.unla.OO2.repository.CatedraRepository;

@Service("catedraService")
public class CatedraServiceImpl implements ICatedraService {

	@Autowired
	private CatedraRepository catedraRepository;

	@Override
	public List<Catedra> getAll() {
		return catedraRepository.findAll();
	}

	@Override
	public boolean insertOrUpdate(Catedra catedra) {
		try {
			catedraRepository.save(catedra);
			return true;
		}catch (Exception he){
			return false;
		}
	}

	@Override
	public Catedra findById(int id) {
		return catedraRepository.findById(id).orElse(null);
	}

	@Override
	public boolean remove(int id) {
		try {
			catedraRepository.deleteById(id);
			return true;
		}catch(Exception he) {
			return false;
		}
	}

}