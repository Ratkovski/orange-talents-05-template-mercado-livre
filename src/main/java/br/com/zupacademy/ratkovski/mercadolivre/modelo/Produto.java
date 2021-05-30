package br.com.zupacademy.ratkovski.mercadolivre.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import br.com.zupacademy.ratkovski.mercadolivre.dto.CaracteristicaProdutoDto;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank 
	private String nome;
	
	
	@Positive
	private int quantidade; 
	@NotBlank 
	@Length(max = 1000) 
	private String descricao;
	
	@Positive 
	private BigDecimal valor; 
	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;
	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;
	@Valid
    @OneToMany(mappedBy = "produto" ,cascade =CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	
	
	@NotNull
	@PastOrPresent //passado ou presente mas n o futuro
	private LocalDateTime instante = LocalDateTime.now();
	@Deprecated
	public Produto(){
		
	}

	public Produto(@NotBlank String nome,
			@Positive int quantidade, 
			@NotBlank @Length(max = 1000) String descricao,
			@NotNull @Positive BigDecimal valor,
			@NotNull @Valid Categoria categoria,
			@NotNull @Valid Usuario usuario,
			@Size(min = 3) @Valid Collection<CaracteristicaProdutoDto> caracteristicas) {

				this.nome = nome;
				this.quantidade = quantidade;
				this.descricao = descricao;
				this.valor = valor;
				this.categoria = categoria;
				this.usuario = usuario;
				this.caracteristicas.addAll(caracteristicas
						.stream().map(caracteristica -> caracteristica.toModel(this))
						.collect(Collectors.toSet()) );
				
				/*faz inline
				 * Set<CaracteristicaProdutoDto> novasCaracteristicas = caracteristicas
				 * .stream().map(caracteristica -> caracteristica.toModel(this))
				 * .collect(Collectors.toSet());
				 * this.caracteristicas.addAll(novasCaracteristicas );
				 */
	Assert.isTrue(this.caracteristicas.size()>=3, "Todo produto precisa ter no minimo três caracteristicas");
	}


	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", quantidade=" + quantidade + ", descricao=" + descricao
				+ ", valor=" + valor + ", categoria=" + categoria + ", usuario=" + usuario + ", caracteristicas="
				+ caracteristicas + "]";
	}


	

}
