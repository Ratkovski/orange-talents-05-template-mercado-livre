package br.com.zupacademy.ratkovski.mercadolivre.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;
import br.com.zupacademy.ratkovski.mercadolivre.repository.UsuarioRepository;

@Service
public class Autenticacao implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { Usuario usuario =
	 * repository.findByEmail(username) .orElseThrow(()-> new
	 * UsernameNotFoundException("Dados invalidos!"));
	 * 
	 * return new Usuario(usuario); }
	 */


  @Override public UserDetails loadUserByUsername(String username) throws
  UsernameNotFoundException { 
	  Optional<Usuario> usuario = repository.findByEmail(username);
	
	  if(usuario.isPresent()) { 
		  return (UserDetails)usuario.get();
  }
  
  throw new UsernameNotFoundException("Dados invalidos!"); }
}


