package Api.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import Api.entities.Usuario;

@Repository("IUserRepository")
public interface IUserRepository extends JpaRepository<Usuario, Serializable>{
	
	
}
