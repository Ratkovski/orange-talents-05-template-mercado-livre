package br.com.zupacademy.ratkovski.mercadolivre.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;


//precisa dizer para o spring security que é um usuario
@Entity
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	
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


	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	
	@Deprecated
	public Usuario() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}




	/**
	 * sempre deixar dica para outros entenderem
	 * 
	 * @params email string no formato de email
	 * @params senha string em texto limpo
	 * 
	 */
	
	//as linhas do codigo comentadas são para lembrar oo que estava fazendo e que não estava certo

	public Usuario(@NotNull @Email String email, 
			@NotNull @Valid SenhaNoCript senhaNoCript) {
		// principio da programação defensiva designbycontract
		Assert.isTrue(StringUtils.hasLength(email), "email não pode ser branco");
		Assert.notNull(senhaNoCript, "o objeto senha no encrit nao pode ser nulo");
		// Assert.isTrue(StringUtils.hasLength(senha),"senha não pode ser branco");
		// Assert.isTrue(senha.length()>= 6,"senha precisa no minimo 6 caracteres");

		this.email = email;
		// this.senha = new BCryptPasswordEncoder().encode(senha);
		this.senha = senhaNoCript.hash();

	}


	public Long getId() {
		return id;
	}



	/**aqui ja passa para a parte de autenticacao**/
	/** método para devolver uma colecao com os perfil do usuario**/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
	return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	
	
	/**controles mais refinados mudei tudo pra true pq nao estou usando**/
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", instante=" + instante + ", perfis="
				+ perfis + "]";
	}





	


	

}
