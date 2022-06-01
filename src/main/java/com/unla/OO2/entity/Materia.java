package com.unla.OO2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity 
@Data
public class Materia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="codMateria")
	private int codMateria;
	@Column(name="nombreMateria", nullable=false)
	private String nombreMateria;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="carrera_id", nullable=false)
	private Carrera carrera;
}