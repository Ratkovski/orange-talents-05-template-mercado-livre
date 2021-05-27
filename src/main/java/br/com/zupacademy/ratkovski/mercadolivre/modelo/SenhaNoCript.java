package br.com.zupacademy.ratkovski.mercadolivre.modelo;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**representa uma senha limpa no sistema**/
public class SenhaNoCript {
	
	private String senha;

	public SenhaNoCript(@NotBlank @Length(min = 6)String senha) {
		Assert.isTrue(StringUtils.hasLength(senha),"senha não pode ser branco");
	    Assert.isTrue(senha.length()>= 6,"senha precisa no minimo 6 caracteres");
		this.senha = senha;
	}

	public  String hash() {
		return  new BCryptPasswordEncoder().encode(senha);
	}
	

}
