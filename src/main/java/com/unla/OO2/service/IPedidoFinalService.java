package com.unla.OO2.service;

import java.util.List;
import com.unla.OO2.entity.PedidoFinal;

public interface IPedidoFinalService {
	public List<PedidoFinal> getAll();
	public boolean insertOrUpdate(PedidoFinal pedidoFinal);
	public PedidoFinal findById(int id);
	public boolean remove(int id);
	public int traerCantidadDeEspacioAsginado(int idNotaPedido);
}