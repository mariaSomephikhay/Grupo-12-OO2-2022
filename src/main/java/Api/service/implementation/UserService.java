package Api.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import Api.repositories.IUserRepository;
import Api.service.IUserService;
import Api.entities.Usuario;

@Service("userService")
public class UserService implements IUserService{
	
	@Autowired()
	@Qualifier("IUserRepository")
	private IUserRepository userRepository;

	@Override
	public List<Usuario> getAll() {
		return userRepository.findAll();
	}
	
	@Override
	public boolean insertOrUpdate(Usuario usuario) {
		try {
			userRepository.save(usuario);
			return true;
		} catch(Exception he) {
			return false;
		}
	}

	@Override
	public Usuario findById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public boolean remove(int id) {
		try {
			userRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	
	
}
