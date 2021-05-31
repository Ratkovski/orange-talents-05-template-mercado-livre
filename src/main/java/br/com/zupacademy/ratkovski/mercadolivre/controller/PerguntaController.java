package br.com.zupacademy.ratkovski.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.ratkovski.mercadolivre.config.emails.Emails;
import br.com.zupacademy.ratkovski.mercadolivre.dto.PerguntaDto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Pergunta;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Produto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;
import br.com.zupacademy.ratkovski.mercadolivre.repository.UsuarioRepository;

@RestController
public class PerguntaController {

	
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
 @Autowired
	private Emails emails;
	
	@PostMapping(value="/produtos/{id}/perguntas")
	@Transactional
	public String cadastrar(@RequestBody @Valid PerguntaDto perguntaDto,@PathVariable("id")Long id) {
		Produto produto = em.find(Produto.class, id);
		
		Usuario usuariopergunta = usuarioRepository.findByEmail("testepergunta@gmail.com").get();
		Pergunta pergunta = perguntaDto.toModel(usuariopergunta,produto);
	
		em.persist(pergunta);
		
		/*
		 * RestTemplate restTemplate= new RestTemplate(); MandrilMessage message = new
		 * MandrilMessage(produto.getDono().getNome()); MandrilMail informacoesEmail =
		 * new MandrilMail(message);
		 * restTemplate.postForEntity("http://mandrillapp.com/api/messages/send.json",
		 * informacoesEmail, String.class);
		 */
		
		emails.pergunta(pergunta);
		return pergunta.toString();
		
	}
}
