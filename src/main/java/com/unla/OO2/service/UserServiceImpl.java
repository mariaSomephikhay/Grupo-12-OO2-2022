package com.unla.OO2.service;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.unla.OO2.Exception.FieldValidation;
import com.unla.OO2.Exception.UsernameOrIdNotFound;
import com.unla.OO2.entity.User;
import com.unla.OO2.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}

	public void pdfGeneratorAdmins() {
		try {
			Document document = new Document();
			String destino = "ListadoDeAdmins.pdf";
			PdfWriter.getInstance(document, new FileOutputStream(destino));
			document.open();
			List<User> lista = Lists.newArrayList(repository.findAll());
			String listString = lista.stream().filter(a -> a.getRoles().stream().anyMatch(c -> c.getName().equals("ADMIN"))).map(Object::toString).collect(Collectors.joining(", "));
			Phrase p = new Phrase(listString);
			document.add(p);
			document.close();
		}catch(Exception e) {
			e.getMessage();
		}
	}

	public void pdfGeneratorAuditores() {
		try {
			Document document = new Document();
			String destino = "ListadoDeAuditores.pdf";
			PdfWriter.getInstance(document, new FileOutputStream(destino));
			document.open();
			List<User> lista = Lists.newArrayList(repository.findAll());
			String listString = lista.stream().filter(a -> a.getRoles().stream().anyMatch(c -> c.getName().equals("USER"))).map(Object::toString).collect(Collectors.joining(", "));
			Phrase p = new Phrase(listString);
			document.add(p);
			document.close();
		}catch(Exception e) {
			e.getMessage();
		}
	}

	private boolean checkUsernameAvailable(User user) throws Exception {
		Optional<User> userFound = repository.findByUsername(user.getUsername());
		if (userFound.isPresent()) {
			throw new FieldValidation("Username no disponible","username");
		}
		return true;
	}

	@Override
	public User createUser(User user) throws Exception {
		if (checkUsernameAvailable(user)) {
			String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			user = repository.save(user);
		}
		return user;
	}

	@Override
	public User getUserById(Integer i) throws UsernameOrIdNotFound {
		return repository.findById(i).orElseThrow(() -> new UsernameOrIdNotFound("El Id del usuario no existe."));
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return repository.save(toUser);
	}

	protected void mapUser(User from,User to) {
		to.setUsername(from.getUsername());
		to.setNombre(from.getNombre());
		to.setApellido(from.getApellido());
		to.setTipo(from.getTipo());
		to.setDni(from.getDni());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
		to.setDepartamento(from.getDepartamento());
		to.setCarrera(from.getCarrera());
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteUser(Integer id) throws UsernameOrIdNotFound {
		User user = getUserById(id);
		repository.delete(user);
	}

}
