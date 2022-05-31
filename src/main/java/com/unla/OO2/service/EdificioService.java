package com.unla.OO2.service;

import java.util.List;
import com.unla.OO2.entity.Edificio;

public interface EdificioService {
	public List<Edificio> getAll();
	public boolean insertOrUpdate(Edificio edificio);
	public Edificio findById(int id);
	public boolean remove(int id);
}