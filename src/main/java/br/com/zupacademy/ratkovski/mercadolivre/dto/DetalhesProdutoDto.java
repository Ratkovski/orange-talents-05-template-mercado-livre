package br.com.zupacademy.ratkovski.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.IntStream;

import br.com.zupacademy.ratkovski.mercadolivre.modelo.Produto;

public class DetalhesProdutoDto {

	private String nome;
	private String descricao;
	private BigDecimal valor;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;
	private Set<Map<String,String>> opinioes;
	private Set<Integer> mapeiaOpinioes;
	private IntStream mapToInt;
	private double mediaNotas;
	private int total;

	public DetalhesProdutoDto(Produto produto) {

		this.nome = produto.getNome();
		this.descricao = produto.getDescicao();
		this.valor = produto.getValor();
		/**duas formas de fazer**/
		//f1
		/*
		 * this.caracteristicas = produto .mapCaracteristicas(caracteristica -> new
		 * DetalheProdutoCaracteristica(caracteristica));
		 */	
		//f1 melhorada
		this.caracteristicas = produto
				  .mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
		
		//f2
		/*
		 * this.caracteristicas = produto.getCaracteristicas().stream()
		 * .map(caracteristica -> new DetalheProdutoCaracteristica(caracteristica))
		 * .collect(Collectors.toSet());
		 */		
	
		
		this.linksImagens = produto.mapeiaImagens(imagem ->imagem.getLink());
		this.perguntas = produto.mapeiaPerguntas(pergunta ->pergunta.getTitulo());
		this.perguntas = produto.mapeiaPerguntas(pergunta ->pergunta.getTitulo());
		//f3
		
	/*	this.opinioes = produto.mapeiaOpinioes(opiniao ->{
			return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());*/
			/**modo2**/
			Opinioes opinioes = produto.getOpinioes();
			this.opinioes =opinioes.mapeiaOpinioes(opiniao ->{
				return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
		});
	
		
		/**modo 1**/
		//com o uso do modo 2 manda esta linha para opinioes
		//Set<Integer>notas = produto.mapeiaOpinioes(opiniao -> opiniao.getNota());
		
		/*
		 * IntStream mapToInt = notas.stream().mapToInt(nota -> nota); OptionalDouble
		 * average = mapToInt.average(); if(average.isPresent()) { this.mediaNotas =
		 * average.getAsDouble(); }
		 */
			//com o uso do modo 2 manda esta linha para opinioes
		//OptionalDouble possivelMedia  = notas.stream().mapToInt(nota -> nota).average();
		/*
		 * //pode usar uma das duas if(possivelMedia.isPresent()) { this.mediaNotas =
		 * possivelMedia.getAsDouble(); }
		 */
			
			//com o uso do modo 2 altera esta linha para
		//this.mediaNotas= possivelMedia.orElseGet(()->0.0);
		this.mediaNotas= opinioes.media();
		this.total= opinioes.total();
		
		/** end modo 1**/
		
		
		
		
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}
public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
	return caracteristicas;
}

public Set<String> getLinksImagens() {
	return linksImagens;
}

public SortedSet<String> getPerguntas() {
	return perguntas;
}

public Set<Map<String, String>> getOpinioes() {
	return opinioes;
}

public double getMediaNotas() {
	return mediaNotas;
}
public int getTotal() {
	return total;
}
}



