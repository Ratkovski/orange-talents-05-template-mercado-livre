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
public class Pergunta implements Comparable<Pergunta>{
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

@Deprecated
	public Pergunta() {}
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




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((usuariopergunta == null) ? 0 : usuariopergunta.hashCode());
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
		Pergunta other = (Pergunta) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (usuariopergunta == null) {
			if (other.usuariopergunta != null)
				return false;
		} else if (!usuariopergunta.equals(other.usuariopergunta))
			return false;
		return true;
	}
	@Override
	public int compareTo(Pergunta o) {
		return this.titulo.compareTo(o.titulo );
	}



	public String getTitulo() {	
		return titulo;
	}


}
