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
import com.unla.OO2.entity.Aula;
import com.unla.OO2.entity.Laboratorio;
import com.unla.OO2.entity.Tradicional;
import com.unla.OO2.service.AulaService;
import com.unla.OO2.service.EdificioService;
import lombok.var;

@Controller
@RequestMapping("/aula")
public class AulaController {

	@Autowired
	private AulaService aulaService;

	@Autowired
	private EdificioService edificioService;

	private void baseAttributerForUserForm(Model model, Aula aula,String activeTab) {
		model.addAttribute("index", aula);
		model.addAttribute(activeTab,"active");
	}

	@GetMapping("/index")
	public String index(Model model) {
		baseAttributerForUserForm(model, new Aula(), "listTab" );
		List<Aula> lst = aulaService.getAll();
		model.addAttribute("aulas",lst);
		return "aula/index";
	}

	@GetMapping("/newLaboratorio")
	public String createLaboratorio(Model model) {
		model.addAttribute("laboratorio", new Laboratorio());
		model.addAttribute("edificios", edificioService.getAll());
		return "/aula/newLaboratorio";
	}

	@GetMapping("/newTradicional")
	public String createTradicional(Model model) {
		model.addAttribute("tradicional", new Tradicional());
		model.addAttribute("edificios", edificioService.getAll());
		return "/aula/newTradicional";
	}

	@PostMapping("/createLaboratorio")
	public String createLaboratorio(@Valid @ModelAttribute("laboratorio") Laboratorio laboratorio) {
		aulaService.insertOrUpdate(laboratorio);
		return "redirect:index";
	}

	@PostMapping("/createTradicional")
	public String createTradicional(@Valid @ModelAttribute("tradicional") Tradicional tradicional) {
		aulaService.insertOrUpdate(tradicional);
		return "redirect:index";
	}

	@GetMapping("/{id}")
	public String get(Model model, @PathVariable("id") int id) {
		var verificar = aulaService.findById(id);
		model.addAttribute("edificios", edificioService.getAll());
		if(verificar instanceof Tradicional) {
			model.addAttribute("tradicional", (Tradicional)verificar);
			return "aula/updateTradicional";
		}else {
			model.addAttribute("laboratorio", (Laboratorio)verificar);
			return "aula/updateLaboratorio";
		}
	}

	@PostMapping("/updateLaboratorio")
	public String updateLaboratorio(@ModelAttribute("laboratorio") Laboratorio laboratorio) {
		aulaService.insertOrUpdate(laboratorio);
		return "redirect:index";
	}

	@PostMapping("/updateTradicional")
	public String updateTradicional(@ModelAttribute("tradicional") Tradicional tradicional) {
		aulaService.insertOrUpdate(tradicional);
		return "redirect:index";
	}

	@PostMapping("/deleteLaboratorio/{id}")
	public String deleteLaboratorio(@PathVariable("id") int id) {
		aulaService.remove(id);
		return "redirect:/aula/index";
	}

	@PostMapping("/deleteTradicional/{id}")
	public String deleteTradicional(@PathVariable("id") int id) {
		aulaService.remove(id);
		return "redirect:/aula/index";
	}

}