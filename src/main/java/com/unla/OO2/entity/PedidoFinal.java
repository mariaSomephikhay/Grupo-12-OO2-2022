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
import javax.persistence.Table;
import javax.validation.constraints.Size;
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
	@Size(min=1)
	@ManyToMany(fetch = FetchType.LAZY)	
	@JoinTable(name = "pedidoFinal_notaPedidos_espacios", joinColumns=@JoinColumn(name="notaPedido_id"),
	inverseJoinColumns=@JoinColumn(name="pedidoFinal_id"))
	private Set<NotaPedido> notaPedidos;
	@Size(min=1)
	@ManyToMany(fetch = FetchType.LAZY)	
	@JoinTable(name = "pedidoFinal_notaPedidos_espacios", joinColumns=@JoinColumn(name="espacio_id"),
	inverseJoinColumns=@JoinColumn(name="pedidoFinal_id"))
	private Set<Espacio> espacios;
}