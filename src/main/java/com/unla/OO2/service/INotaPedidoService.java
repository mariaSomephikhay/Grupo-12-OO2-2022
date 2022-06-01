package com.unla.OO2.service;

import java.util.List;
import com.unla.OO2.entity.NotaPedido;

public interface INotaPedidoService{
	public List<NotaPedido> getAll();
	public boolean insertOrUpdate(NotaPedido notaPedido);
	public NotaPedido findById(int id);
	public boolean remove(int id);
	public boolean cambiarAsignado(NotaPedido notaPedido);
}