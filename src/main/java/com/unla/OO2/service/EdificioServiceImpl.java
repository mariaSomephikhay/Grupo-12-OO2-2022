package com.unla.OO2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unla.OO2.entity.Edificio;
import com.unla.OO2.repository.EdificioRepository;

@Service("edificioService")
public class EdificioServiceImpl implements EdificioService {

	@Autowired
	private EdificioRepository edificioRepository;

	@Override
	public List<Edificio> getAll() {
		return edificioRepository.findAll();
	}

	@Override
	public boolean insertOrUpdate(Edificio edificio) {
		try {
			edificioRepository.save(edificio);
			return true;
		}catch (Exception he){
			return false;
		}
	}

	@Override
	public Edificio findById(int id) {
		return edificioRepository.findById(id).orElse(null);
	}

	@Override
	public boolean remove(int id) {
		try {
			edificioRepository.deleteById(id);
			return true;
		}catch(Exception he) {
			return false;
		}
	}

}