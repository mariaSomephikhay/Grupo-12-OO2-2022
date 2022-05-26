package com.unla.OO2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.OO2.entity.Catedra;
import com.unla.OO2.service.ICatedraService;


@Controller
@RequestMapping("/catedra")
public class CatedraController {
	
	@Autowired()
	private ICatedraService catedraService;
	
	
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
}

