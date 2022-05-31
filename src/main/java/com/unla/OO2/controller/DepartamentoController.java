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

import com.unla.OO2.entity.Departamento;
import com.unla.OO2.service.IDepartamentoService;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {
	
	@Autowired
	private IDepartamentoService departamentoService;
	
	private void baseAttributerForUserForm(Model model, Departamento departamento,String activeTab) {
		model.addAttribute("index", departamento);
		model.addAttribute(activeTab,"active");
	}

	@GetMapping("/index")
	public String index(Model model) {
		baseAttributerForUserForm(model, new Departamento(), "listTab" );
		List<Departamento> lst = departamentoService.getAll();
		model.addAttribute("departamentos",lst);
		return "departamento/index";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("departamento", new Departamento());
		return "departamento/new";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("aula") Departamento departamento, Model model) {
		departamentoService.insertOrUpdate(departamento);
		return "redirect:index";
	}

	@GetMapping("/{id}")
	public String get(Model model, @PathVariable("id") int id) {
		model.addAttribute("departamento", departamentoService.findById(id));
		return "departamento/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("departamento") Departamento departamento) {
		departamentoService.insertOrUpdate(departamento);
		return "redirect:index";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		departamentoService.remove(id);
		return "redirect:/departamento/index";
	}

}
