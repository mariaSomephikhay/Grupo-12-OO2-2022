package com.unla.OO2.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Entity
@Data
@Table(name="espacio")
public class Espacio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	@Column(name="turno")
	private char turno;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="aula_id", nullable=false)
	private Aula aula;
	@Column(name="libre")
	private boolean libre;
}