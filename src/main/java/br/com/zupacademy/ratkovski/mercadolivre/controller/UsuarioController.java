package br.com.zupacademy.ratkovski.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.ratkovski.mercadolivre.config.validation.UnicoEmailValidator;
import br.com.zupacademy.ratkovski.mercadolivre.dto.UsuarioDto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;

@RestController
@RequestMapping
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private UnicoEmailValidator unicoEmailValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(unicoEmailValidator);
	}
	
	@PostMapping(value = "/usuarios")
	@Transactional
	
	public ResponseEntity<UsuarioDto> cadastra(@RequestBody @Valid UsuarioDto usuarioDto) {
		Usuario usuario = usuarioDto.toModel();
		em.persist(usuario);
		return ResponseEntity.ok().build();
		
		
	}
	
	
	
}
	


