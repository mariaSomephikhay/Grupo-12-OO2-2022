package com.unla.OO2.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.unla.OO2.Exception.FieldValidation;
import com.unla.OO2.Exception.UsernameOrIdNotFound;
import com.unla.OO2.entity.User;
import com.unla.OO2.repository.RoleRepository;
import com.unla.OO2.service.ICarreraService;
import com.unla.OO2.service.IDepartamentoService;
import com.unla.OO2.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private IDepartamentoService departamentoService;

	@Autowired
	private ICarreraService carreraService;
	
	@GetMapping({"/","/login"})
	public String index() {
		return "index";
	}

	private void baseAttributerForUserForm(Model model, User user,String activeTab) {
		model.addAttribute("userForm", user);
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("departamentos",departamentoService.getAll());
		model.addAttribute("carreras",carreraService.getAll());
		model.addAttribute(activeTab,"active");
	}

	@GetMapping("/userForm")
	public String userForm(Model model) {
		baseAttributerForUserForm(model, new User(), "listTab" );
		return "user-form/user-view";
	}

	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {
			baseAttributerForUserForm(model, user, "formTab");
		}else {
			try {
				userService.createUser(user);
				baseAttributerForUserForm(model, new User(), "listTab" );

			} catch (FieldValidation fv) {
				result.rejectValue(fv.getFieldName(), null, fv.getMessage());
				baseAttributerForUserForm(model, user, "formTab" );
			}catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				baseAttributerForUserForm(model, user, "formTab" );
			}
		}
		return "user-form/user-view";
	}

	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name ="id")Integer id)throws Exception{
		baseAttributerForUserForm(model, userService.getUserById(id), "formTab" );
		model.addAttribute("editMode","true");
		return "user-form/user-view";
	}

	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {
			baseAttributerForUserForm(model, user, "formTab" );
			model.addAttribute("editMode","true");
		}else {
			try {
				userService.updateUser(user);
				baseAttributerForUserForm(model, new User(), "listTab" );
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				baseAttributerForUserForm(model, user, "formTab" );
				model.addAttribute("editMode","true");
			}
		}
		return "user-form/user-view";
	}

	@GetMapping("/userForm/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/userForm";
	}

	@GetMapping("/userForm/pdfAdmin")
	public String generarPdfAdmin(ModelMap model) {
		userService.pdfGeneratorAdmins();
		return "redirect:/userForm";
	}

	@GetMapping("/userForm/pdfAuditores")
	public String generarPdfAuditores(ModelMap model) {
		userService.pdfGeneratorAuditores();
		return "redirect:/userForm";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name="id")Integer id) throws UsernameOrIdNotFound {
		userService.deleteUser(id);
		return userForm(model);
	}

}