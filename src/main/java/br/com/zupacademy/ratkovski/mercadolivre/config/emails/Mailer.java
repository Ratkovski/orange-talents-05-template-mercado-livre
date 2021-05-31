package br.com.zupacademy.ratkovski.mercadolivre.config.emails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {
/*
 * 
 * @param body corpo email
 * @param subject assunto email
 * @param nameFrom nome aparecer provedor
 * @param from origem
 * @param to destino
 */
	void send(@NotBlank String body, @NotBlank String subject, @NotBlank String nameFrom,@NotBlank @Email String from, @NotBlank @Email String to);

}
