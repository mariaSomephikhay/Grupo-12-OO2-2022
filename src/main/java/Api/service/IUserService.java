package Api.service;

import java.util.List;


import Api.entities.Usuario;

public interface IUserService {
	
	public List<Usuario> getAll();

	public boolean insertOrUpdate(Usuario usuario);
	
	public Usuario findById(int id);

	public boolean remove(int id);

	
}
