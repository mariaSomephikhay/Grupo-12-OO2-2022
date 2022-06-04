package com.unla.OO2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.OO2.entity.Materia;
import com.unla.OO2.service.ICarreraService;
import com.unla.OO2.service.IMateriaService;

@Controller
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private IMateriaService materiaService;
	
	@Autowired
	private ICarreraService carreraService;

	private void baseAttributerForUserForm(Model model, Materia materia,String activeTab) {
		model.addAttribute("index", materia);
		model.addAttribute(activeTab,"active");
	}

	@GetMapping("/index")
	public String index(Model model) {
		baseAttributerForUserForm(model, new Materia(), "listTab" );
		List<Materia> lst = materiaService.getAll();
		model.addAttribute("materias",lst);
		return "materia/index";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("materia", new Materia());
		model.addAttribute("carreras", carreraService.getAll());
		return "materia/new";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("materia") Materia materia) {
		materiaService.insertOrUpdate(materia);
		return "redirect:index";
	}

	@GetMapping("/{id}")
	public String get(Model model, @PathVariable("id") int id) {
		model.addAttribute("materia", materiaService.findById(id));
		model.addAttribute("carreras", carreraService.getAll());
		return "materia/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("materia") Materia materia) {
		materiaService.insertOrUpdate(materia);
		return "redirect:index";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		materiaService.remove(id);
		return "redirect:/materia/index";
	}
}
