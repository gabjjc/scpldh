package com.ldh.routerScp.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldh.routerScp.model.Usuario;
import com.ldh.routerScp.repo.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class RouterController {

	@Autowired
	Environment environment;
	
	@Autowired
	UsuarioRepository repository;
 
	@GetMapping("/usuario/{id}")
	public Usuario getUsuarioById(@PathVariable("id") long id) {
		return repository.findById(id);
	}
	
	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios() {
		return repository.findAll();
	}
	
	@GetMapping("/getip")
	public String getIp() {
		System.out.println("Your server is up and running at port: " + environment.getProperty("local.server.ip"));
		return environment.getProperty("local.server.ip");
		
	}
	
	@PostMapping("/usuario")
	public long addUser(@RequestBody Usuario usuario) {
		Usuario nuevo = repository.save(usuario);
		return ( nuevo != null ) ? nuevo.getId() : 500;
	}
	
	@DeleteMapping("/usuario/{id}")
	public boolean deleteUser(@PathVariable("id") long id) {
		boolean state = true;
		try{
			repository.deleteById(id);
		}catch(Exception e) {
			state = false;
			System.out.println("Error al eliminar usuario: "+ e.getMessage());
		}
		return state;
	}
	
}
