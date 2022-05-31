package com.unla.OO2.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.unla.OO2.entity.Curso;
import com.unla.OO2.entity.Final;
import com.unla.OO2.entity.NotaPedido;
import com.unla.OO2.service.ICatedraService;
import com.unla.OO2.service.INotaPedidoService;
import lombok.var;

@Controller
@RequestMapping("/notaPedido")
public class NotaPedidoController {

	@Autowired
	private INotaPedidoService notaPedidoService;

	@Autowired
	private ICatedraService catedraService;

	private void baseAttributerForUserForm(Model model, NotaPedido notaPedido,String activeTab) {
		model.addAttribute("index", notaPedido);
		model.addAttribute(activeTab,"active");
	}

	@GetMapping("/index")
	public String index(Model model) {
		baseAttributerForUserForm(model, new NotaPedido(), "listTab");
		List<NotaPedido> lst = notaPedidoService.getAll();		
		model.addAttribute("notas",lst);
		return "notaPedido/index";
	}

	@GetMapping("/newFinal")
	public String createFinal(Model model) {
		model.addAttribute("final", new Final());
		model.addAttribute("catedras", catedraService.getAll());
		return "notaPedido/newFinal";
	}

	@PostMapping("/createFinal")
	public String createFinal(@Valid @ModelAttribute("final") Final finall) {
		finall.setAsignado(false);
		notaPedidoService.insertOrUpdate(finall);
		return "redirect:index";
	}

	@GetMapping("/newCurso")
	public String createCurso(Model model) {
		model.addAttribute("curso", new Curso());
		model.addAttribute("catedras", catedraService.getAll());
		return "notaPedido/newCurso";
	}

	@PostMapping("/createCurso")
	public String createCurso(@Valid @ModelAttribute("curso") Curso curso) {
		curso.setAsignado(false);
		notaPedidoService.insertOrUpdate(curso);
		return "redirect:index";
	}

	@GetMapping("/{id}")
	public String get(Model model, @PathVariable("id") int id) {
		var verificar = notaPedidoService.findById(id);
		model.addAttribute("catedras", catedraService.getAll());
		if(verificar instanceof Final) {
			model.addAttribute("final", (Final)verificar);
			return "notaPedido/updateFinal";
		}else {
			model.addAttribute("curso", (Curso)verificar);
			return "notaPedido/updateCurso";
		}
	}

	@PostMapping("/updateFinal")
	public String updateFinal(@ModelAttribute("final") Final finall) {
		if(finall.getId() > 0) {
			NotaPedido finalViejo = notaPedidoService.findById(finall.getId());
			finall.setFechaCreacion(finalViejo.getFechaCreacion());
			finall.setAsignado(finalViejo.isAsignado());
		}
		notaPedidoService.insertOrUpdate(finall);
		return "redirect:index";
	}

	@PostMapping("/updateCurso")
	public String updateCurso(@ModelAttribute("curso") Curso curso) {
		if(curso.getId() > 0) {
			NotaPedido cursoViejo = notaPedidoService.findById(curso.getId());
			curso.setFechaCreacion(cursoViejo.getFechaCreacion());
			curso.setAsignado(cursoViejo.isAsignado());
		}
		notaPedidoService.insertOrUpdate(curso);
		return "redirect:index";
	}

	@PostMapping("/deleteFinal/{id}")
	public String deleteFinal(@PathVariable("id") int id) {
		notaPedidoService.remove(id);
		return "redirect:/notaPedido/index";
	}

	@PostMapping("/deleteCurso/{id}")
	public String deleteCurso(@PathVariable("id") int id) {
		notaPedidoService.remove(id);
		return "redirect:/notaPedido/index";
	}

}