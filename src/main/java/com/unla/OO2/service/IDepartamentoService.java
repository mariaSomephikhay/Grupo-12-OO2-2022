package com.unla.OO2.service;

import java.util.List;
import com.unla.OO2.entity.Departamento;

public interface IDepartamentoService {
	public List<Departamento> getAll();
	public boolean insertOrUpdate(Departamento departamento);
	public Departamento findById(int id);
	public boolean remove(int id);
}