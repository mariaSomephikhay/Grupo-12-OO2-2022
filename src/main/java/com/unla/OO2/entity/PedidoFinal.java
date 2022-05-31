package com.unla.OO2.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="pedidoFinal")
public class PedidoFinal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPedidoFinal;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="espacio_id", nullable=true)
	private Espacio espacio;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="notaPedido_id", nullable=true)
	private NotaPedido notaPedido;
}