package com.unla.OO2.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="notaPedido")
public class NotaPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@CreationTimestamp
	@Column(name="fechaCreacion")
	private LocalDate fechaCreacion;
	@Column(name="turno")
	private char turno;
	@Column(name="aula")
	private String aula;
	@Column(name="cantEstudiantes")
	private int cantEstudiantes;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="catedra_id", nullable=true)
	private Catedra catedra;
	@Column(name="observaciones")
	private String observaciones;
	//al crearse una nota pedido se guarda false
	@Column(name="asignado")
	private boolean asignado;
}