package com.unla.OO2.service;

import java.util.List;
import com.unla.OO2.entity.Aula;

public interface AulaService {
	public List<Aula> getAll();
	public boolean insertOrUpdate(Aula aula);
	public Aula findById(int id);
	public boolean remove(int id);
}