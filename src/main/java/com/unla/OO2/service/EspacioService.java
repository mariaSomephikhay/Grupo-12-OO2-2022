package com.unla.OO2.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.unla.OO2.entity.Espacio;

public interface EspacioService {
	public List<Espacio> getAll();
	public List<Espacio> traerConAulaTradicionalPorTurnoYFechaLibres(char turno, String fecha);
	public List<Espacio> traerConAulaLaboratorioPorTurnoYFechaLibres(char turno, String fecha);
	public List<Espacio> traerConAulaTradicionalPorTurnoYEntreFechasLibres(char turno, String fechaI, String fechaF);
	public List<int[]> traerSumaYIdAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres( char turno, String fechaI,  String fechaF);
	public Espacio traerConAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres( int aulaId, char turno, String fechaI, String fechaF);
	public List<int[]> traerSumaYIdAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres( char turno, String fechaI,  String fechaF);
	public Espacio traerConAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(int aulaId,char turno, String fechaI, String fechaF);
	public Espacio traerPorAulaFechaTurnoLibre(int idAula, char turno, String fecha);
	public List<Espacio> listAll(String idAula, String turno, String fecha);
	public boolean insertOrUpdate(Espacio espacio);
	public boolean cambiarLibre(Espacio espacio);
	public Espacio findById (int id);
}
