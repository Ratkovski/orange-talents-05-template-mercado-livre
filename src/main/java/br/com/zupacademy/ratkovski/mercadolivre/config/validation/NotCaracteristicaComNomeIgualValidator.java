package br.com.zupacademy.ratkovski.mercadolivre.config.validation;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.ratkovski.mercadolivre.dto.ProdutoDto;

public class NotCaracteristicaComNomeIgualValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoDto.class.isAssignableFrom(clazz) ;
	}

	@Override
	public void validate(Object target, Errors errors) {
if(errors.hasErrors()) {
	return;
}


/*
 * primeiro metodo usado vou deixar de exemplo 
 * ProdutoDto request = (ProdutoDto)target;
 * if(request.temCarateristicasIguais()) { errors.rejectValue("caracteristicas",
 * null, "Tem caracteristicas iguais");
 * 
 * }
 */
ProdutoDto request = (ProdutoDto)target;
boolean buscaCarateristicasIguais;
Set<String> nomesIguais =  request.buscaCarateristicasIguais();
if(!nomesIguais.isEmpty()) {
	errors.rejectValue("caracteristicas", null, "Tem caracteristicas com nomes iguais" +nomesIguais);
	
}
	}

}
