package br.com.zupacademy.ratkovski.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.ratkovski.mercadolivre.dto.DetalhesProdutoDto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Produto;

@RestController
public class DetalhesProdutoController {
	
	@PersistenceContext
	private EntityManager em;
	
	@GetMapping(value="/produtos/{id}")
	public DetalhesProdutoDto Detalhes(@PathVariable("id")Long id) {
		Produto produto = em.find(Produto.class, id);
		return new  DetalhesProdutoDto(produto);
	}
	}
	


