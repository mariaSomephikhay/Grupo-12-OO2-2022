package com.unla.OO2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.OO2.entity.PedidoFinal;
import com.unla.OO2.repository.PedidoFinalRepository;

@Service("pedidoFinalService")
public class PedidoFinalServiceImple implements IPedidoFinalService{
	
	@Autowired
	private PedidoFinalRepository pedidoFinalRepository;

	@Override
	public boolean insertOrUpdate(PedidoFinal pedidoFinal) {
		try {
			pedidoFinalRepository.save(pedidoFinal);
			return true;
		}catch (Exception he) {
			return false;
		}
	}

	@Override
	public PedidoFinal findById(int id) {
		return pedidoFinalRepository.findById(id).orElse(null);
	}

	@Override
	public List<PedidoFinal> getAll() {
		return pedidoFinalRepository.findAll();
	}

	@Override
	public boolean remove(int id) {
		try {
			pedidoFinalRepository.deleteById(id);
			return true;
		}catch (Exception he) {
			return false;
		}
	}

	@Override
	public int traerCantidadDeEspacioAsginado(int idNotaPedido) {
		return pedidoFinalRepository.traerCantidadDeEspacioAsginado(idNotaPedido);
	}

}