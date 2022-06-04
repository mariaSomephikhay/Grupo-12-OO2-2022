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
import com.unla.OO2.entity.Catedra;
import com.unla.OO2.service.ICatedraService;
import com.unla.OO2.service.IMateriaService;
import com.unla.OO2.service.IProfesorService;

@Controller
@RequestMapping("/catedra")
public class CatedraController {

	@Autowired
	private ICatedraService catedraService;
	
	@Autowired
	private IMateriaService materiaService;
	
	@Autowired
	private IProfesorService profesorService;

	private void baseAttributerForUserForm(Model model, Catedra catedra,String activeTab) {
		model.addAttribute("index", catedra);
		model.addAttribute(activeTab,"active");
	}

	@GetMapping("/index")
	public String index(Model model) {
		baseAttributerForUserForm(model, new Catedra(), "listTab" );
		List<Catedra> lst = catedraService.getAll();
		model.addAttribute("catedras",lst);
		return "catedra/index";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("catedra", new Catedra());
		model.addAttribute("materias", materiaService.getAll());
		model.addAttribute("profesores", profesorService.getAll());
		return "catedra/new";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("catedra") Catedra catedra) {
		catedraService.insertOrUpdate(catedra);
		return "redirect:index";
	}

	@GetMapping("/{id}")
	public String get(Model model, @PathVariable("id") int id) {
		model.addAttribute("catedra", catedraService.findById(id));
		model.addAttribute("materias", materiaService.getAll());
		model.addAttribute("profesores", profesorService.getAll());
		return "catedra/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("catedra") Catedra catedra) {
		catedraService.insertOrUpdate(catedra);
		return "redirect:index";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		catedraService.remove(id);
		return "redirect:/catedra/index";
	}
	
}