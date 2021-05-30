package br.com.zupacademy.ratkovski.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.ratkovski.mercadolivre.config.security.Autenticacao;
import br.com.zupacademy.ratkovski.mercadolivre.config.validation.NotCaracteristicaComNomeIgualValidator;
import br.com.zupacademy.ratkovski.mercadolivre.dto.ProdutoDto;
import br.com.zupacademy.ratkovski.mercadolivre.dto.UsuarioDto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Produto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;
import br.com.zupacademy.ratkovski.mercadolivre.repository.UsuarioRepository;


@RestController
public class ProdutoController {
	
	
	@PersistenceContext
	private EntityManager em;
	
	//@Autowired
	//private UsuarioRepository usuarioRepository ;
	
	
	  @InitBinder public void init(WebDataBinder binder) { 
		  binder.addValidators(new
	  NotCaracteristicaComNomeIgualValidator());
	  
	  }
	 

	
	@PostMapping(value = "/produtos")
	@Transactional
	
	public String cadastra(@RequestBody @Valid ProdutoDto produtoDto,
			@AuthenticationPrincipal Usuario usuario) {
		
        Produto produto = produtoDto.toModel(em, usuario);
		em.persist(produto);
		return produtoDto.toString();

}
	
}