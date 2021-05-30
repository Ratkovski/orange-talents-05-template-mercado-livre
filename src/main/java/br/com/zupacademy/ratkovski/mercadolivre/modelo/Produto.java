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
import br.com.zupacademy.ratkovski.mercadolivre.dto.ImagemDto;

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
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE) // quando for atualizar produto atualiza as imagens															// junto
	private Set<ImagemProduto> imagens = new HashSet<>();

	@NotNull
	@PastOrPresent // passado ou presente mas n o futuro
	private LocalDateTime instante = LocalDateTime.now();

	@Deprecated
	public Produto() {

	}

	public Produto(@NotBlank String nome, @Positive int quantidade, @NotBlank @Length(max = 1000) String descricao,
			@NotNull @Positive BigDecimal valor, @NotNull @Valid Categoria categoria, @NotNull @Valid Usuario usuario,
			@Size(min = 3) @Valid Collection<CaracteristicaProdutoDto> caracteristicas) {

		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.usuario = usuario;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));

		/*
		 * faz inline Set<CaracteristicaProdutoDto> novasCaracteristicas =
		 * caracteristicas .stream().map(caracteristica -> caracteristica.toModel(this))
		 * .collect(Collectors.toSet());
		 * this.caracteristicas.addAll(novasCaracteristicas );
		 */
		Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no minimo três caracteristicas");
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream()
				.map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean pertenceUsuario(Usuario dono) {

		return this.usuario.equals(dono);
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

}
