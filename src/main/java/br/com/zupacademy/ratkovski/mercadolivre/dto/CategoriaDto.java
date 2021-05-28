package br.com.zupacademy.ratkovski.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.ratkovski.mercadolivre.config.validation.ExistsId;
import br.com.zupacademy.ratkovski.mercadolivre.config.validation.UniqueValue;
import br.com.zupacademy.ratkovski.mercadolivre.modelo.Categoria;

public class CategoriaDto {
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
    @Positive
    //fiz pelo validator personalizado
 //   @ExistsId(domainClass = Categoria.class, fieldName = "id")
    @Nullable
	private Long idCategoriaMae;

    
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public CategoriaDto(String nome) {
		this.nome = nome;
		
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}


	public Categoria toModel(EntityManager em) {
		Categoria categoria = new Categoria(nome);
		if(idCategoriaMae != null) {
			Categoria categoriaMae = em.find(Categoria.class, idCategoriaMae);			
			categoria.setCatMae(categoriaMae);
			Assert.notNull(categoriaMae, "o id da categoria mãe precisa ser válido");
		}
		return categoria;
	}
	

	public String getNome() {
		return nome;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}
	

}
