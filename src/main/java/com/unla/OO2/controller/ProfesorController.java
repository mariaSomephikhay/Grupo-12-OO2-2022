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

import com.unla.OO2.entity.Profesor;
import com.unla.OO2.service.IProfesorService;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {

	@Autowired
	private IProfesorService profesorService;

	private void baseAttributerForUserForm(Model model, Profesor profesor,String activeTab) {
		model.addAttribute("index", profesor);
		model.addAttribute(activeTab,"active");
	}

	@GetMapping("/index")
	public String index(Model model) {
		baseAttributerForUserForm(model, new Profesor(), "listTab" );
		List<Profesor> lst = profesorService.getAll();
		model.addAttribute("profesores",lst);
		return "profesor/index";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("profesor", new Profesor());
		return "profesor/new";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("profesor") Profesor profesor, Model model) {
		profesorService.insertOrUpdate(profesor);
		return "redirect:index";
	}

	@GetMapping("/{id}")
	public String get(Model model, @PathVariable("id") int id) {
		model.addAttribute("profesor", profesorService.findById(id));
		return "profesor/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("profesor") Profesor profesor) {
		profesorService.insertOrUpdate(profesor);
		return "redirect:index";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		profesorService.remove(id);
		return "redirect:/profesor/index";
	}

}