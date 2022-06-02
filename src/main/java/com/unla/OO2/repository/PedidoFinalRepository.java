package com.unla.OO2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unla.OO2.entity.PedidoFinal;

public interface PedidoFinalRepository extends JpaRepository<PedidoFinal, Integer>{
	
	@Query(value = "select count(*) from pedido_final where nota_pedido_id=:id group by nota_pedido_id", nativeQuery = true)
	int traerCantidadDeEspacioAsginado(@Param("id") int idNotaPedido);
	
}
