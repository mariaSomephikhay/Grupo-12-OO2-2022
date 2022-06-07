package com.unla.OO2.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter @Setter @NoArgsConstructor
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="nombreCarrera", nullable=false)
	private String nombreCarrera;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="carrera")
	private Set<User> users;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="departamento_id", nullable=false)
	private Departamento departamento;
}