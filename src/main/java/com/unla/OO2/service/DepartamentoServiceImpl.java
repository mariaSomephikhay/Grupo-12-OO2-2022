package com.unla.OO2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unla.OO2.entity.Departamento;
import com.unla.OO2.repository.DepartamentoRepository;

@Service("departamentoService")
public class DepartamentoServiceImpl implements IDepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Override
	public List<Departamento> getAll() {
		return departamentoRepository.findAll();
	}

	@Override
	public boolean insertOrUpdate(Departamento departamento) {
		try {
			departamentoRepository.save(departamento);
			return true;
		}catch (Exception he){
			return false;
		}
	}

	@Override
	public Departamento findById(int id) {
		return departamentoRepository.findById(id).orElse(null);
	}

	@Override
	public boolean remove(int id) {
		try {
			departamentoRepository.deleteById(id);
			return true;
		}catch(Exception he) {
			return false;
		}
	}

}