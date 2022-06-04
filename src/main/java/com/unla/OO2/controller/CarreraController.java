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

import com.unla.OO2.entity.Carrera;
import com.unla.OO2.service.ICarreraService;
import com.unla.OO2.service.IDepartamentoService;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
	
	@Autowired
	private ICarreraService carreraService;
	
	@Autowired
	private IDepartamentoService departamentoService;

	private void baseAttributerForUserForm(Model model, Carrera carrera,String activeTab) {
		model.addAttribute("index", carrera);
		model.addAttribute(activeTab,"active");
	}

	@GetMapping("/index")
	public String index(Model model) {
		baseAttributerForUserForm(model, new Carrera(), "listTab" );
		List<Carrera> lst = carreraService.getAll();
		model.addAttribute("carreras",lst);
		return "carrera/index";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("carrera", new Carrera());
		model.addAttribute("departamentos", departamentoService.getAll());
		return "carrera/new";
	}
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("carrera") Carrera carrera) {
		carreraService.insertOrUpdate(carrera);
		return "redirect:index";
	}

	@GetMapping("/{id}")
	public String get(Model model, @PathVariable("id") int id) {
		model.addAttribute("carrera", carreraService.findById(id));
		model.addAttribute("departamentos", departamentoService.getAll());
		return "carrera/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("carrera") Carrera carrera) {
		carreraService.insertOrUpdate(carrera);
		return "redirect:index";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		carreraService.remove(id);
		return "redirect:/carrera/index";
	}

}
