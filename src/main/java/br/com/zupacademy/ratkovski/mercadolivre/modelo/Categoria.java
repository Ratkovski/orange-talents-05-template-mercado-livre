package br.com.zupacademy.ratkovski.mercadolivre.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;
	
	@ManyToOne
	private Categoria categoriaMae;

	@Deprecated
	public Categoria() {
		
	}
	public Categoria(String nome) {
		this.nome = nome;
	
	}

	public void setCatMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
		
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", categoriaMae=" + categoriaMae + "]";
	}

	
}


	

