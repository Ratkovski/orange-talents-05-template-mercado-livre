package br.com.zupacademy.ratkovski.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.ratkovski.mercadolivre.config.validation.UniqueValue;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.SenhaNoCript;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;

public class UsuarioDto {
	@NotBlank(message = "O email é obrigatório")
	@NotNull
	@Email	
	//poderia ser com a classe genérica mas acabei colocando uma personalizada
	//@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	
	private String email;
	@NotBlank(message = "A senha é obrigatório")
	@NotNull
	@Length(min=6,message = "A senha precisa ter mais de 6 digitos")
	private String senha;

	
	
	public UsuarioDto(@NotBlank(message = "O email é obrigatório") @NotNull @Email String email,
			@NotBlank(message = "A senha é obrigatório") @NotNull @Length(min = 6, message = "A senha precisa ter mais de 6 digitos") String senha) {
	
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario toModel() {
			return new Usuario(email,new SenhaNoCript(senha));
		}

	public String getEmail() {
		return email;
	}


	}
	
	
	
	
	
	
	

