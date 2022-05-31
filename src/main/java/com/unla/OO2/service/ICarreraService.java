package com.unla.OO2.service;

import java.util.List;
import com.unla.OO2.entity.Carrera;

public interface ICarreraService {
	public List<Carrera> getAll();
	public boolean insertOrUpdate(Carrera carrera);
	public Carrera findById(int id);
	public boolean remove(int id);
}