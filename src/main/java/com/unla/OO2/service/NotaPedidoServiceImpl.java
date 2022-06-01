package com.unla.OO2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unla.OO2.entity.NotaPedido;
import com.unla.OO2.repository.NotaPedidoRepository;

@Service("notaPedidoService")
public class NotaPedidoServiceImpl implements INotaPedidoService{

	@Autowired
	private NotaPedidoRepository notaPedidoRepository;

	@Override
	public List<NotaPedido> getAll() {
		return notaPedidoRepository.findAll();
	}

	@Override
	public boolean insertOrUpdate(NotaPedido notaPedido) {
		try {
			notaPedidoRepository.save(notaPedido);
			return true;
		}catch (Exception he) {
			return false;
		}
	}

	@Override
	public NotaPedido findById(int id) {
		return notaPedidoRepository.findById(id).orElse(null);
	}

	@Override
	public boolean remove(int id) {
		try {
			notaPedidoRepository.deleteById(id);
			return true;
		}catch (Exception he) {
			return false;
		}
	}

	@Override
	public boolean cambiarAsignado(NotaPedido notaPedido) {
		notaPedido.setAsignado(true);
		return insertOrUpdate(notaPedido);
	}

}