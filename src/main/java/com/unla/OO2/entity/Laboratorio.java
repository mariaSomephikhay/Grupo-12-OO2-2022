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
@PrimaryKeyJoinColumn(referencedColumnName="id", name = "idLaboratorio")
@Table(name="laboratorio")
public class Laboratorio extends Aula{
	@Column(name="cantPc")
	private int cantPc;
	@Column(name="cantSilla")
	private int cantSilla;
}