package br.com.zupacademy.ratkovski.mercadolivre.config.emails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.ratkovski.mercadolivre.modelo.Pergunta;

@Component
public class Emails {

	@Autowired
	private Mailer mailer;
	
	public void pergunta(@NotNull @Valid Pergunta pergunta) {
		
mailer.send("<html>Caixa de emails</html>",
		pergunta.getTitulo(),
		pergunta.getPerguntador().getEmail(),
		 "perguntas@gmail.com",pergunta.getVendedor().getEmail());	
	}

}
