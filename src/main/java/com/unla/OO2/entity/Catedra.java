package com.unla.OO2.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
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
	@Size(min=1)
	@ManyToMany(fetch = FetchType.LAZY)	
	@JoinTable(name = "catedra_profesores", joinColumns=@JoinColumn(name="catedra_id"),
	inverseJoinColumns=@JoinColumn(name="profesores_id"))
	private Set<Profesor> profesores;
	public Set<Profesor> getProfesores() {
		return profesores;
	}
}