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
import com.unla.OO2.entity.Edificio;
import com.unla.OO2.service.EdificioService;

@Controller
@RequestMapping("/edificio")
public class EdificioController {

	@Autowired
	private EdificioService edificioService;

	private void baseAttributerForUserForm(Model model, Edificio edificio,String activeTab) {
		model.addAttribute("index", edificio);
		model.addAttribute(activeTab,"active");
	}

	@GetMapping("/index")
	public String index(Model model) {
		baseAttributerForUserForm(model, new Edificio(), "listTab" );
		List<Edificio> lst = edificioService.getAll();
		model.addAttribute("edificios",lst);
		return "edificio/index";
	}

	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("edificio", new Edificio());
		return "edificio/new";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("aula") Edificio edificio, Model model) {
		edificioService.insertOrUpdate(edificio);
		return "redirect:index";
	}

	@GetMapping("/{id}")
	public String get(Model model, @PathVariable("id") int id) {
		model.addAttribute("edificio", edificioService.findById(id));
		return "edificio/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("edificio") Edificio edificio) {
		edificioService.insertOrUpdate(edificio);
		return "redirect:index";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		edificioService.remove(id);
		return "redirect:/edificio/index";
	}
}