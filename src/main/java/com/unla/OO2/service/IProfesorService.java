package com.unla.OO2.service;

import java.util.List;
import com.unla.OO2.entity.Profesor;

public interface IProfesorService {
	public List<Profesor> getAll();
	public boolean insertOrUpdate(Profesor profesor);
	public Profesor findById(int id);
	public boolean remove(int id);
}