package br.com.zup.orange.MercadoLivre.Categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import br.com.zup.orange.MercadoLivre.Valicacoes.UniqueValue;

public class CategoriaRequest {
	
	@NotBlank @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	private Long idCategoriaMae;

	public CategoriaRequest(@NotBlank String nome, Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}
	
	public CategoriaRequest(@NotBlank String nome) {
		this.nome = nome;
	}

	public Categoria toModel(EntityManager em) {
		Categoria categoria = new Categoria(nome);
		
		if(idCategoriaMae != null) {
			//Verificacao para validar se categoria existe
			Categoria categoriaMae = em.find(Categoria.class,idCategoriaMae);
			Assert.notNull(categoriaMae, "A categoria mãe não existe, passe um Id valido!");
			
			categoria.setCategoriaMae(categoriaMae);
		}
		
		return categoria;
	}
	
}
