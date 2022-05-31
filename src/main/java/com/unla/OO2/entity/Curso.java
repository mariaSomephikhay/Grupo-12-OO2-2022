package com.unla.OO2.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName="id", name = "idCurso")
@Table(name="curso")
public class Curso extends NotaPedido {
	@Column(name="codCurso")
	private String codCurso;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechaInicio")
	private LocalDate fechaInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechaFin")
	private LocalDate fechaFin;
	//25% 50% 100%
	@Column(name="porcPresencialidad")
	private int porcPresencialidad;
}