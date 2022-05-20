package com.unla.OO2.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity 
@Data
public class Catedra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="materia_id", nullable=false)
	private Materia materia;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="profesor_id", nullable=false)
	private Set<Profesor> profesores;
	public Set<Profesor> getProfesores() {
		return profesores;
	}

}
