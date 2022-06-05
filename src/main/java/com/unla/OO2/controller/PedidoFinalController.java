package com.unla.OO2.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.unla.OO2.entity.Final;
import com.unla.OO2.entity.Aula;
import com.unla.OO2.entity.Curso;
import com.unla.OO2.entity.Espacio;
import com.unla.OO2.entity.NotaPedido;
import com.unla.OO2.entity.PedidoFinal;
import com.unla.OO2.service.EspacioService;
import com.unla.OO2.service.INotaPedidoService;
import com.unla.OO2.service.IPedidoFinalService;

@Controller
@RequestMapping("/pedidoFinal")
public class PedidoFinalController {
	
	@Autowired
	private IPedidoFinalService pedidoFinalService;
	
	@Autowired
	private INotaPedidoService notaPedidoService;
	
	@Autowired
	private EspacioService espacioService;
	
	private void baseAttributerForUserForm(Model model, PedidoFinal pedidoFinal,String activeTab) {
		model.addAttribute("index", pedidoFinal);
		model.addAttribute(activeTab,"active");
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		baseAttributerForUserForm(model, new PedidoFinal(), "listTab");
		List<PedidoFinal> lst = pedidoFinalService.getAll();		
		model.addAttribute("pedidosFinales",lst);
		return "pedidoFinal/index";
	}
	
	@GetMapping("/newPedidoFinal/{id}")
	public String createFinal(Model model, @PathVariable("id") int id) {
		model.addAttribute("pedidoFinal", new PedidoFinal());
		NotaPedido np = notaPedidoService.findById(id);
		model.addAttribute("notaPedido",np);
		List<Espacio> espacioT = new ArrayList<Espacio>();
		List<Espacio> espacioL = new ArrayList<Espacio>();
		if (np instanceof Final) {
			espacioT = espacioService.traerConAulaTradicionalPorTurnoYFechaLibres(np.getTurno(), ((Final)np).getFechaExamen().toString());
			espacioL = espacioService.traerConAulaLaboratorioPorTurnoYFechaLibres(np.getTurno(), ((Final)np).getFechaExamen().toString());
			//model.addAttribute("espaciosT", espacioT);
			//model.addAttribute("espaciosL", espacioService.traerConAulaLaboratorioPorTurnoYFechaLibres(np.getTurno(), ((Final)np).getFechaExamen().toString()));
		}else {
			Curso c = (Curso)np;
			LocalDate fechaI = c.getFechaInicio();
			LocalDate fechaF = c.getFechaFin();
			//calculo para saber la cantidad de clases de un curso;
			int semanas = 0;
			LocalDate fechaAux = fechaI;
			while (fechaAux.isBefore(fechaF.plusDays(1))) {
				fechaAux = fechaAux.plusWeeks(1);
				semanas++;
			}
			if (c.getPorcPresencialidad()==100) {
				List<int[]> lista = espacioService.traerSumaYIdAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(np.getTurno(), fechaI.toString(), fechaF.toString());
				for (int i=0; i<lista.size(); i++) {
					if (lista.get(i)[0]==semanas) {
						espacioT.add(espacioService.traerConAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(lista.get(i)[1], np.getTurno(), fechaI.toString(), fechaF.toString()));
					}
				}
				
				List<int[]> lista2 = espacioService.traerSumaYIdAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(np.getTurno(), fechaI.toString(), fechaF.toString());
				for (int i=0; i<lista2.size(); i++) {
					if (lista2.get(i)[0]==semanas) {
						espacioL.add(espacioService.traerConAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(lista2.get(i)[1], np.getTurno(), fechaI.toString(), fechaF.toString()));
					}
				}
			}
			if (c.getPorcPresencialidad()==50) {
				semanas = semanas/2;	
				List<int[]> lista = espacioService.traerSumaYIdAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(np.getTurno(), fechaI.toString(), fechaF.toString());
				for (int i=0; i<lista.size(); i++) {
					if (lista.get(i)[0]>=semanas) {
						espacioT.add(espacioService.traerConAulaTradicionalPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(lista.get(i)[1], np.getTurno(), fechaI.toString(), fechaF.toString()));
					}
				}				
				List<int[]> lista2 = espacioService.traerSumaYIdAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(np.getTurno(), fechaI.toString(), fechaF.toString());
				for (int i=0; i<lista2.size(); i++) {
					if (lista2.get(i)[0]>=semanas) {
						espacioL.add(espacioService.traerConAulaLaboratorioPorTurnoYEntreFechasUnDiaDeLaSemanaLibres(lista2.get(i)[1], np.getTurno(), fechaI.toString(), fechaF.toString()));
					}
				}
				
			}
			
			if (c.getPorcPresencialidad()==25) {
				espacioT = espacioService.traerConAulaTradicionalPorTurnoYEntreFechasLibres(np.getTurno(), fechaI.toString(), fechaF.toString());
				espacioL = espacioService.traerConAulaTradicionalPorTurnoYEntreFechasLibres(np.getTurno(), fechaI.toString(), fechaF.toString());
			}
			
		}
		if (espacioT.size()!=0) {
			model.addAttribute("espaciosT", espacioT);
		}else {
			model.addAttribute("espaciosT", null);
		}
		if (espacioL.size()!=0) {
			model.addAttribute("espaciosL", espacioL);
		}else {
			model.addAttribute("espaciosL", null);
		}	
		return "pedidoFinal/newPedidoFinal";
	}

	@GetMapping("/createPedidoFinal/{id}")
	public String createFinal(@Valid @ModelAttribute("pedidoFinal") PedidoFinal pedidoFinal, @PathVariable("id") int id) {
		NotaPedido np = notaPedidoService.findById(id);
		
		if (np instanceof Final) {
			pedidoFinal.setNotaPedido(np);
			pedidoFinalService.insertOrUpdate(pedidoFinal);
			notaPedidoService.cambiarAsignado(np);
			espacioService.cambiarLibre(pedidoFinal.getEspacio());
		}else {
			Aula seleccionada = pedidoFinal.getEspacio().getAula();
			Curso c = (Curso)np;
			char turno = np.getTurno();
			if (c.getPorcPresencialidad()==100) {
				LocalDate fechaAux = c.getFechaInicio();
				while (fechaAux.isBefore(c.getFechaFin().plusDays(1))) {
					 Espacio e = espacioService.traerPorAulaFechaTurnoLibre(seleccionada.getId(), turno, fechaAux.toString());
					 PedidoFinal pedidoGuardar = new PedidoFinal();
					 pedidoGuardar.setEspacio(e);
					 pedidoGuardar.setNotaPedido(np);
					 pedidoFinalService.insertOrUpdate(pedidoGuardar);
					 espacioService.cambiarLibre(pedidoGuardar.getEspacio());
					 fechaAux = fechaAux.plusDays(7);
				}
				notaPedidoService.cambiarAsignado(np);
			} 
			if (c.getPorcPresencialidad()==50) {
				LocalDate fechaAux = c.getFechaInicio();
				while (fechaAux.isBefore(c.getFechaFin().plusDays(1))) {
					 Espacio e = espacioService.traerPorAulaFechaTurnoLibre(seleccionada.getId(), turno, fechaAux.toString());
					 if (e!=null) {
						 PedidoFinal pedidoGuardar = new PedidoFinal();
						 pedidoGuardar.setEspacio(e);
						 pedidoGuardar.setNotaPedido(np);
						 pedidoFinalService.insertOrUpdate(pedidoGuardar);
						 espacioService.cambiarLibre(pedidoGuardar.getEspacio());
						 fechaAux = fechaAux.plusDays(14);
					 }else {
						 fechaAux = fechaAux.plusDays(7);
					 }
				}
				notaPedidoService.cambiarAsignado(np);
			}
			
			if(c.getPorcPresencialidad()==25) {
				 PedidoFinal pedidoGuardar = new PedidoFinal();
				 pedidoGuardar.setEspacio(pedidoFinal.getEspacio());
				 pedidoGuardar.setNotaPedido(np);
				 pedidoFinalService.insertOrUpdate(pedidoGuardar);
				 espacioService.cambiarLibre(pedidoGuardar.getEspacio());
				 if(pedidoFinalService.traerCantidadDeEspacioAsginado(np.getId()) >= 2) {
					notaPedidoService.cambiarAsignado(np); 
				 }	 
			}
		}
		return "redirect:/notaPedido/index";
	}
	
}
