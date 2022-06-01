package com.unla.OO2.service;

import java.util.List;
import com.unla.OO2.entity.Espacio;

public interface EspacioService {
	public List<Espacio> getAll();
	public List<Espacio> traer(String idAula, String turno, String fecha);
	public List<Espacio> listAll(String idAula, String turno, String fecha);
	public boolean insertOrUpdate(Espacio espacio);
	public boolean cambiarLibre(Espacio espacio);
}
