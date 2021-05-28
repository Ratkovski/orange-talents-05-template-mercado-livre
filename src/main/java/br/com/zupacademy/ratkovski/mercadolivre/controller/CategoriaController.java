package br.com.zupacademy.ratkovski.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.ratkovski.mercadolivre.config.validation.ExisteCategoriaValidator;
import br.com.zupacademy.ratkovski.mercadolivre.dto.CategoriaDto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Categoria;

@RestController

public class CategoriaController {

	@PersistenceContext
	private EntityManager em;
	
	
	//na precisou do validador pois estava validando duas vezes e ocasionando erro
	//@Autowired
	//private ExisteCategoriaValidator existeCategoriaValidator;

	
	//@InitBinder
	//public void init(WebDataBinder binder) {
		//binder.addValidators(existeCategoriaValidator);
	//}
	
	@PostMapping(value = "/categorias")
	@Transactional
	
	public ResponseEntity<CategoriaDto> cadastra(@RequestBody @Valid CategoriaDto categoriaDto) {

		Categoria categoria = categoriaDto.toModel(em);
		em.persist(categoria);
		return ResponseEntity.ok().build();
		
		
	}
}
