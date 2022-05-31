package com.unla.OO2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName="id", name = "idTradicional")
@Table(name="tradicional")
public class Tradicional extends Aula{
	@Column(name="cantBanco")
	private int cantBanco;
	@Column(name="pizarron")
	private String pizarron;
	@Column(name="tieneProyector")
	private boolean tieneProyector;
}