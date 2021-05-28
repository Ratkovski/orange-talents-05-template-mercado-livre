package br.com.zupacademy.ratkovski.mercadolivre.config.validation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.ratkovski.mercadolivre.dto.CategoriaDto;

@Component  //para ser gerenciada pelo spring
public class ExisteCategoriaValidator implements Validator{


@PersistenceContext
private EntityManager em;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CategoriaDto.class.isAssignableFrom(clazz);// é a mesma ou é filha do autor request
		
	}
	@Override
	public void validate(Object target, Errors errors) {
		/**Verifica se já ocorreu algum outro erro de validação, caso sim, já não passa para a execucao da nossa validação.**/
		if(errors.hasErrors()) {
			return;
		}
		CategoriaDto request = (CategoriaDto) target;
	Query consultaCategoria =	em.createQuery("select 1 from Categoria  where categoria_mae_id = :idCategoriaMae" )
			
		.setParameter("idCategoriaMae", request.getIdCategoriaMae());
	List<?> resultList = consultaCategoria.getResultList();
	if(!resultList.isEmpty()) {
		errors.rejectValue("idCategoriaMae", null, "Já existe esta categoria ");
	
		
		
	}
	}


}


