package com.unla.OO2.service;

import java.util.List;
import com.unla.OO2.entity.Catedra;

public interface ICatedraService {
	public List<Catedra> getAll();
	public boolean insertOrUpdate(Catedra catedra);
	public Catedra findById(int id);
	public boolean remove(int id);
}