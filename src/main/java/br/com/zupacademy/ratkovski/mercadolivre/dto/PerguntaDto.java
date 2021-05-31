package br.com.zupacademy.ratkovski.mercadolivre.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.ratkovski.mercadolivre.modelo.Pergunta;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Produto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;

public class PerguntaDto {
	
	private String titulo;

	
	

	  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public PerguntaDto(String titulo) {
		super();
		this.titulo = titulo;
	}



	@Override
	public String toString() {
		return "PerguntaDto [titulo=" + titulo + "]";
	}



	public Pergunta toModel(@NotNull @Valid Usuario usuariopergunta,
			                @NotNull @Valid Produto produto) {
		
		return new Pergunta(titulo,usuariopergunta,produto);
	}



	
	

}
