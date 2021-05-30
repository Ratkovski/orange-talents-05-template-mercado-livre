package br.com.zupacademy.ratkovski.mercadolivre.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.ratkovski.mercadolivre.modelo.CaracteristicaProduto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Produto;

public class CaracteristicaProdutoDto {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	
	public CaracteristicaProdutoDto(@NotBlank String nome, @NotBlank String descricao) {
	
		this.nome = nome;
		this.descricao = descricao;
	}


	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}


	@Override
	public String toString() {
		return "NovaCaracteristicaDto [nome=" + nome + ", descricao=" + descricao + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		CaracteristicaProdutoDto other = (CaracteristicaProdutoDto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
		return new CaracteristicaProduto(nome,descricao,produto);
	}
	
}
