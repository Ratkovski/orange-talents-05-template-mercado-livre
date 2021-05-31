package br.com.zupacademy.ratkovski.mercadolivre.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.ratkovski.mercadolivre.modelo.Opiniao;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Produto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;

public class OpiniaoDto {
	@Range(min = 1, max = 5)
	private int nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max=500)
	private String descricao;
	
	
	public OpiniaoDto(@Range(min = 1, max = 5) int nota,
					@NotBlank String titulo, 
					@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}


	public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
		
		return new Opiniao(nota, titulo,descricao,produto,usuario);
	}

}
