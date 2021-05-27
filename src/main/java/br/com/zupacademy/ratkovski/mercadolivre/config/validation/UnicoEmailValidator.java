package br.com.zupacademy.ratkovski.mercadolivre.config.validation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.ratkovski.mercadolivre.dto.UsuarioDto;

@Component  //para ser gerenciada pelo spring
public class UnicoEmailValidator implements Validator{


@PersistenceContext
private EntityManager em;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioDto.class.isAssignableFrom(clazz);// é a mesma ou é filha do autor request
		
	}
	@Override
	public void validate(Object target, Errors errors) {
		/**Verifica se já ocorreu algum outro erro de validação, caso sim, já não passa para a execucao da nossa validação.**/
		if(errors.hasErrors()) {
			return;
		}
		UsuarioDto request = (UsuarioDto) target;
	Query consultaEmail =	em.createQuery("select 1 from Usuario u where u.email = :email" )
		.setParameter("email", request.getEmail());
	List<?> resultList = consultaEmail.getResultList();
	if(!resultList.isEmpty()) {
		errors.rejectValue("email", null, "Já existe este email cadastrado no sistema");
	
		
		
	}
	}


}


