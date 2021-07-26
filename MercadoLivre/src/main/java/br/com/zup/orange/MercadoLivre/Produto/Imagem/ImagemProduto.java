package br.com.zup.orange.MercadoLivre.Produto.Imagem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import br.com.zup.orange.MercadoLivre.Produto.Produto;

@Entity
public class ImagemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@URL
	private String link;
	
	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;


	public ImagemProduto(@NotBlank @URL String link, @NotNull @Valid Produto produto) {
		super();
		this.link = link;
		this.produto = produto;
	}

	@Deprecated
	public ImagemProduto() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "ImagemProduto [id=" + id + ", link=" + link + "]";
	}

	public String getLink() {
		return link;
	}
}
