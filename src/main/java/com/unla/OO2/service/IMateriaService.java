package com.unla.OO2.service;

import java.util.List;
import com.unla.OO2.entity.Materia;

public interface IMateriaService {
	public List<Materia> getAll();
	public boolean insertOrUpdate(Materia materia);
	public Materia findById(int id);
	public boolean remove(int id);
}