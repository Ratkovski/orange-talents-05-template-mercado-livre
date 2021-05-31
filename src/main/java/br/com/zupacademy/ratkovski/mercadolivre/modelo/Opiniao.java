package br.com.zupacademy.ratkovski.mercadolivre.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
public class Opiniao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Range(min = 1, max = 5)
	private int nota;
	@NotBlank
	private String titulo;
	@NotBlank @Size(max = 10) 
	private String descricao;
	@ManyToOne
	@NotNull 
	@Valid 
	private Produto produto;
	@ManyToOne
	@NotNull 
	@Valid
	private Usuario usuario;

	public Opiniao(@Range(min = 1, max = 5) int nota,
			@NotBlank String titulo, 
			@NotBlank @Size(max = 500) String descricao,
			@NotNull @Valid Produto produto, 
			@NotNull @Valid Usuario usuario) {
				this.nota = nota;
				this.titulo = titulo;
				this.descricao = descricao;
				this.produto = produto;
				this.usuario = usuario;
	
	}


	

}
