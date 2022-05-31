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
@PrimaryKeyJoinColumn(referencedColumnName="id", name = "idFinal")
@Table(name="final")
public class Final extends NotaPedido{
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechaExamen")
	private LocalDate fechaExamen;
}