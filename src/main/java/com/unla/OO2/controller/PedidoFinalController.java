package com.unla.OO2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.OO2.entity.Final;
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
	
	@GetMapping("/newPedidoFinal/{id}")
	//public String createFinal(Model model, @Valid @ModelAttribute("final") Final finall) {
	public String createFinal(Model model, @PathVariable("id") int id) {
		model.addAttribute("pedidoFinal", new PedidoFinal());
		NotaPedido np = notaPedidoService.findById(id);
		model.addAttribute("final",np);
		model.addAttribute("espacios", espacioService.getAll());
		return "pedidoFinal/newPedidoFinal";
	}

	@GetMapping("/createPedidoFinal/{id}")
	public String createFinal(@Valid @ModelAttribute("pedidoFinal") PedidoFinal pedidoFinal, @PathVariable("id") int id) {
		NotaPedido np = notaPedidoService.findById(id);
		pedidoFinal.setNotaPedido(np);
		pedidoFinalService.insertOrUpdate(pedidoFinal);
		notaPedidoService.cambiarAsignado(np);
		espacioService.cambiarLibre(pedidoFinal.getEspacio());
		return "redirect:/notaPedido/index";
	}
	
	/*@GetMapping("/createPedidoFinal/{id}")
	public void createFinal(@Valid @ModelAttribute("pedidoFinal") PedidoFinal pedidoFinal, @PathVariable("id") int id) {
		NotaPedido np = notaPedidoService.findById(id);
		pedidoFinal.setNotaPedido(np);
		pedidoFinalService.insertOrUpdate(pedidoFinal);
		createInsertarFinal(pedidoFinal);
	}
	
	@PostMapping("/createPedidoFinal/create")
	public String createInsertarFinal(PedidoFinal p) {
		pedidoFinalService.insertOrUpdate(p);
		return "redirect:/index";
	}*/
}
