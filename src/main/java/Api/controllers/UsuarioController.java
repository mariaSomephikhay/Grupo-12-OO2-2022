package Api.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import Api.service.IUserService;

import Api.entities.Usuario;
import Api.helpers.Helper;
import Api.models.UsuarioModel;

@Controller
@RequestMapping("/user")
public class UsuarioController {
	
	@Autowired()
	@Qualifier("userService")
	private IUserService userService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(Helper.USER_INDEX);
		List<Usuario> lst = userService.getAll();
		mAV.addObject("users", lst);
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(Helper.USER_NEW);
		mAV.addObject("user", new UsuarioModel());
		return mAV;
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("user") UsuarioModel userModel) {
		userService.insertOrUpdate(modelMapper.map(userModel, Usuario.class));
		//return new RedirectView(Helper.USER_ROOT);
		return Helper.USER_ROOT;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(Helper.USER_UPDATE);
		mAV.addObject("user", userService.findById(id));
		return mAV;
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute("user") UsuarioModel userModel) {
		/*Usuario user = modelMapper.map(userModel, Usuario.class);
		if(userModel.getId() > 0) {
			Usuario userOld = userService.findById(userModel.getId());
			user.setPassword("sadassad");
			user.setUser(userOld.getUser());
		}*/
		userService.insertOrUpdate(modelMapper.map(userModel, Usuario.class));
		return Helper.USER_ROOT;
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.remove(id);
		return Helper.USER_ROOT;
	}
}
