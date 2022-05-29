package com.unla.OO2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.OO2.entity.Profesor;
import com.unla.OO2.service.IProfesorService;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {
	
	@Autowired()
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

}
