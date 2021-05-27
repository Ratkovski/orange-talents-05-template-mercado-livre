package br.com.zupacademy.ratkovski.mercadolivre.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@NotNull
	@Email
	private String email;
	@NotBlank
	@NotNull
	@Length(min = 6)
	private String senha;
	@NotNull
	@PastOrPresent //passado ou presente mas n o futuro
	private LocalDateTime instante = LocalDateTime.now();

	/**
	 * sempre deixar dica para outros entenderem
	 * 
	 * @params email string no formato de email
	 * @params senha string em texto limpo
	 * 
	 */
	
	//as linhas do codigo comentadas são para lembrar oo que estava fazendo e que não estava certo

	public Usuario(@NotNull @Email String email, @NotNull @Valid SenhaNoCript senhaNoCript) {
		// principio da programação defensiva designbycontract
		Assert.isTrue(StringUtils.hasLength(email), "email não pode ser branco");
		Assert.notNull(senhaNoCript, "o objeto senha no encrit nao pode ser nulo");
		// Assert.isTrue(StringUtils.hasLength(senha),"senha não pode ser branco");
		// Assert.isTrue(senha.length()>= 6,"senha precisa no minimo 6 caracteres");

		this.email = email;
		// this.senha = new BCryptPasswordEncoder().encode(senha);
		this.senha = senhaNoCript.hash();

	}

	


	

}
