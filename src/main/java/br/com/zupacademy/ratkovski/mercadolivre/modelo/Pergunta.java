package br.com.zupacademy.ratkovski.mercadolivre.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
public class Pergunta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	@ManyToOne
	@NotNull
	@Valid
	private Usuario usuariopergunta;
	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;
	@NotNull
	@PastOrPresent // passado ou presente mas n o futuro
	private LocalDateTime instante = LocalDateTime.now();



	public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario usuariopergunta, @NotNull @Valid Produto produto
			) {
		super();
		this.titulo = titulo;
		this.usuariopergunta = usuariopergunta;
		this.produto = produto;
		
	}



	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", usuariopergunta=" + usuariopergunta + ", produto="
				+ produto + ", instante=" + instante + "]";
	}



	public Usuario getPerguntador() {
		return usuariopergunta;
	}



	public Usuario getVendedor() {
		return produto.getUsuario();
	}



	public String getTitulo() {
		
		return titulo;
	}






}
