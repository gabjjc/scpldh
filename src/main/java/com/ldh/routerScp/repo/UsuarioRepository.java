package com.ldh.routerScp.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ldh.routerScp.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	Usuario findById(long id);
	
	List<Usuario> findAll();
	
	Usuario save(Usuario s);
	
	void deleteById(Long id); 
		
}
