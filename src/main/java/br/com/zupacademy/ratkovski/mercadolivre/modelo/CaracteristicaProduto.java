package br.com.zupacademy.ratkovski.mercadolivre.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class CaracteristicaProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank 
	private String nome;
	@NotBlank
	private  String descricao;
	@NotNull 
	@Valid 
	@ManyToOne
	private Produto produto;

	@Deprecated
	public CaracteristicaProduto() {}
	
	
	public CaracteristicaProduto(@NotBlank String nome, 
			@NotBlank String descricao,
			@NotNull @Valid Produto produto) {
				this.nome = nome;
				this.descricao = descricao;
				this.produto = produto;
		
	}
	
	  @Override public int hashCode() { final int prime = 31; int result = 1;
	  result = prime * result + ((nome == null) ? 0 : nome.hashCode()); return
	  result; }
	  
	  @Override public boolean equals(Object obj) { if (this == obj) return true;
	  if (obj == null) return false; if (getClass() != obj.getClass()) return
	  false; CaracteristicaProduto other = (CaracteristicaProduto) obj; if (nome ==
	  null) { if (other.nome != null) return false; } else if
	  (!nome.equals(other.nome)) return false; return true; }
	  
	  @Override public String toString() { return "CaracteristicaProdutoDto [nome="
	  + nome + ", descricao=" + descricao + "]"; }


	public String getNome() {
		return nome;
	}
	  
	 public String getDescricao() {
		return descricao;
	}

}
