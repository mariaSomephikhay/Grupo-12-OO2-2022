package com.unla.OO2.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.unla.OO2.entity.Espacio;
import com.unla.OO2.service.AulaService;
import com.unla.OO2.service.EspacioService;

@Controller
@RequestMapping("/espacio")
public class EspacioController {

	@Autowired
	EspacioService service;
	@Autowired
	AulaService aservice;

	@RequestMapping("/index")
	public String home(Espacio espacio, Model model, @Param("idAula") String idAula, @Param("turno") String turno, @Param("fecha") String fecha) {
		List<Espacio> lstEspacio=service.listAll(idAula, turno, fecha);
		model.addAttribute("lstEspacio", lstEspacio);
		model.addAttribute("aulas", aservice.getAll());
		model.addAttribute("idAula", idAula);
		model.addAttribute("turno", turno);
		model.addAttribute("fecha", fecha);
		return "espacio/index";
	}
	
	@GetMapping("/{id}")
	public String get(Model model, @PathVariable("id") int id) {
		model.addAttribute("espacio", service.findById(id));
		model.addAttribute("aulas", aservice.getAll());
		return "espacio/update";
	}

	@PostMapping("/createEspacio")
	public String createEspacio(@Valid @ModelAttribute("espacio") Espacio espacio) {
		espacio.setLibre(true);
		service.insertOrUpdate(espacio);
		return "redirect:index";
	}

	@PostMapping("/createEspacioMes")
	public String createEspacioMes(@Valid @ModelAttribute("espacio") Espacio espacio) {
		LocalDate date1 = LocalDate.of(espacio.getFecha().getYear(), espacio.getFecha().getMonthValue(), 1);
		YearMonth yearMonthObject = YearMonth.of(date1.getYear(),date1.getMonthValue());
		int daysInMonth = yearMonthObject.lengthOfMonth(); 
		int days = 0;
		espacio.setFecha(date1);
		while(days<daysInMonth) {
			Espacio espacio2 = new Espacio();
			espacio2.setAula(espacio.getAula());
			espacio2.setLibre(true);
			espacio2.setTurno(espacio.getTurno());
			espacio2.setFecha(espacio.getFecha().plusDays(days));
			service.insertOrUpdate(espacio2);
			days=days+1;
		}
		return "redirect:index";
	}

	@PostMapping("/createEspacioCuatrimestre")
	public String createEspacioCuatrimestre(@Valid @ModelAttribute("espacio") Espacio espacio, @RequestParam String fechaFin) {
		LocalDate date1 = espacio.getFecha();
		LocalDate finCuatrimestre = LocalDate.parse(fechaFin);
		while(espacio.getFecha().isBefore(finCuatrimestre)) {
			createEspacioMes(espacio);
			date1= LocalDate.of(espacio.getFecha().getYear(), espacio.getFecha().getMonthValue()+1, 1);
			espacio.setFecha(date1);
		}
		return "redirect:index";
	}

}