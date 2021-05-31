package br.com.zupacademy.ratkovski.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.ratkovski.mercadolivre.dto.OpiniaoDto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Opiniao;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Produto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;
import br.com.zupacademy.ratkovski.mercadolivre.repository.UsuarioRepository;

@RestController

public class OpiniaoCrontroller {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**mais pra frente colocar o tipo de perfil para pegar mais certo usuario=usuario ou usuario=consumidor **/
	
	
	@PostMapping(value="/produtos/{id}/opiniao")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid OpiniaoDto opiniaoDto, @PathVariable("id") Long id,@AuthenticationPrincipal Usuario usuario) {
    Produto produto = em.find(Produto.class,id);
   // Usuario consumidor = usuarioRepository.findByEmail("teste@gmail.com").get();
    Opiniao opiniao = opiniaoDto.toModel(produto,usuario);
    em.persist(opiniao);
    //return opiniao.toString();
    return ResponseEntity.ok().build();
		
	
}
}
