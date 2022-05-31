package com.unla.OO2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.unla.OO2.repository.RoleRepository;
import com.unla.OO2.service.UserService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	public String index() {
		return "../index";
	}
	@GetMapping("/index")
	public String index(Model model) {
		return "menu/index";
	}

	@GetMapping("/userForm")
	public String userForm(Model model) {
		return "../user-form/user-view";
	}

	@GetMapping("/aula/index")
	public String aulaindex(Model model) {
		return "..aula/index";
	}

	@GetMapping("/edificio/index")
	public String edificioindex(Model model) {
		return "..edificio/index";
	}

}