package com.unla.OO2.service;

import com.unla.OO2.Exception.UsernameOrIdNotFound;
import com.unla.OO2.entity.User;

public interface UserService {
	public Iterable<User> getAllUsers();
	public void pdfGeneratorAdmins();
	public void pdfGeneratorAuditores();
	public User createUser(User user) throws Exception;
	public User getUserById(Integer id) throws Exception;
	public User updateUser(User user) throws Exception;
	public void deleteUser(Integer id) throws UsernameOrIdNotFound;
}