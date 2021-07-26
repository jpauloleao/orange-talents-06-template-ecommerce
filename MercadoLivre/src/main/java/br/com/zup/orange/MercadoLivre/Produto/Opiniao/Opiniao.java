package br.com.zup.orange.MercadoLivre.Produto.Opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.zup.orange.MercadoLivre.Produto.Produto;
import br.com.zup.orange.MercadoLivre.Usuario.Usuario;

@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Min(1) 
	@Max(5)
	private int nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank 
	@Length(max = 500)
	private String descricao;
	
	@ManyToOne
	@NotNull
	@Valid
	private Usuario usuario;
	
	@ManyToOne
	@NotNull
	@Valid
	@JsonIgnore
	private Produto produto;
	
	@Deprecated
	public Opiniao() {
		// TODO Auto-generated constructor stub
	}

	public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Length(max = 500) String descricao,
			@NotNull @Valid Usuario usuario, @Valid @NotNull Produto produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getNota() {
		return nota;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
}
