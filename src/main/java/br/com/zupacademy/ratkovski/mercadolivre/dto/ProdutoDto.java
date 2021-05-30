package br.com.zupacademy.ratkovski.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.ratkovski.mercadolivre.config.security.Autenticacao;
import br.com.zupacademy.ratkovski.mercadolivre.config.validation.ExistsId;
import br.com.zupacademy.ratkovski.mercadolivre.config.validation.UniqueValue;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Categoria;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Produto;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;

public class ProdutoDto {
	@NotBlank(message = "o nome é obrigatório")
   // @UniqueValue(domainClass = Produto.class, fieldName = "nome")
	private String nome;
	
	@Positive
	private int quantidade;
	
	@NotBlank(message = "a descrição é obrigatória")
	@Length(max =1000)
	private String descricao;
	
	@Positive
	private BigDecimal valor;
	@NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;
	
	@Size(min=3)
	@Valid
	private List<CaracteristicaProdutoDto> caracteristicas = new ArrayList<>();
	

	public ProdutoDto(@NotBlank String nome, @Positive int quantidade, 
			@NotBlank @Length(max = 1000) String descricao,
			@NotNull @Positive BigDecimal valor, @NotNull Long idCategoria,
			
			@Size(min=3)@Valid List<CaracteristicaProdutoDto> caracteristicas ) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = idCategoria;
	
		
		this.caracteristicas.addAll(caracteristicas);
	}

	public Produto toModel(EntityManager em,Usuario usuario) {	
	Categoria categoria = em.find(Categoria.class, idCategoria);
		return new Produto(nome,quantidade,descricao,valor,categoria,usuario,caracteristicas);
	}


	//public boolean temCarateristicasIguais() {
		public Set<String> buscaCarateristicasIguais() {
		/**HashSet não suporta elementos iguais "String" já tem ums metodos de comparação implementado**/
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for(CaracteristicaProdutoDto caracteristica : caracteristicas) {
			String nome = caracteristica.getNome();
			if(!nomesIguais.add(nome)) {
				resultados.add(nome);
				
			}
		}
		
		return resultados;
	}

	/*
	 * public String getNome() { return nome; }
	 * 
	 * public int getQuantidade() { return quantidade; }
	 * 
	 * public String getDescricao() { return descricao; }
	 * 
	 * public BigDecimal getValor() { return valor; }
	 * 
	 * public Long getIdCategoria() { return idCategoria; }
	 * 
	 * public List<CaracteristicaProdutoDto> getCaracteristicas() { return
	 * caracteristicas; }
	 */
		@Override
		public String toString() {
			return "ProdutoDto [nome=" + nome + ", quantidade=" + quantidade + ", descricao=" + descricao + ", valor="
					+ valor + ", idCategoria=" + idCategoria + ", caracteristicas=" + caracteristicas + "]";
		}

	

}
