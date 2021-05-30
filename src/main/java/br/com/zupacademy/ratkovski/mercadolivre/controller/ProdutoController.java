package br.com.zupacademy.ratkovski.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.ratkovski.mercadolivre.config.validation.NotCaracteristicaComNomeIgualValidator;
import br.com.zupacademy.ratkovski.mercadolivre.dto.ImagemDto;
import br.com.zupacademy.ratkovski.mercadolivre.dto.ProdutoDto;
import br.com.zupacademy.ratkovski.mercadolivre.dto.UploaderFake;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Produto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;


@RestController
public class ProdutoController {
	
	
	@PersistenceContext
	private EntityManager em;
	
	//@Autowired
	//private UsuarioRepository usuarioRepository ;
	
	@Autowired
    private UploaderFake uploaderFake ;
		
	
	  @InitBinder(value="/produtos")
	  public void init(WebDataBinder binder) { 
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
	@PostMapping(value = "/produtos/{id}/imagens")
	@Transactional

public ResponseEntity<?> adicionaImagem(@PathVariable("id") Long id, @Valid ImagemDto imagemDto,@AuthenticationPrincipal Usuario dono) {
		Produto produto = em.find(Produto.class, id);

		if(!produto.pertenceUsuario(dono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links =  uploaderFake.envia(imagemDto.getImagens());


produto.associaImagens(links);
em.merge(produto);

//return produto.toString();
return ResponseEntity.ok().build();
	
	}

}
/*
 * @PostMapping(value = "/produtos/{id}/imagens")
 * 
 * @Transactional
 * 
 * public String adicionaImagem(@PathVariable("id") Long id, @Valid ImagemDto
 * imagemDto,@AuthenticationPrincipal Usuario usuario) { Set<String> links =
 * uploaderFake.envia(imagemDto.getImagens());
 * 
 * Produto produto = em.find(Produto.class, id); produto.associaImagens(links);
 * em.merge(produto);
 * 
 * return produto.toString(); }
 */

