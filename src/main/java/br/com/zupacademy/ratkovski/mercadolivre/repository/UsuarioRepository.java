package br.com.zupacademy.ratkovski.mercadolivre.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	//Optional<Usuario> findByEmail(String username);
	Optional<Usuario> findByEmail(String email);

	//Usuario findByEmail(Usuario user);



	

	
	
	}


