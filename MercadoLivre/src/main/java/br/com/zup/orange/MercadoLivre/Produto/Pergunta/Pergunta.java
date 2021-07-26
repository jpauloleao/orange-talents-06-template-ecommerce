package br.com.zup.orange.MercadoLivre.Produto.Pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.zup.orange.MercadoLivre.Produto.Produto;
import br.com.zup.orange.MercadoLivre.Usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@PastOrPresent
	@NotNull
	private LocalDateTime instanteCadastro;
	
	@Valid
	@NotNull 
	@ManyToOne	
	@JsonIgnore
	private Usuario usuario;
	
	@Valid
	@NotNull 
	@ManyToOne
	@JsonIgnore
	private Produto produto;

	public Pergunta(@NotBlank String titulo, @Valid @NotNull Usuario usuario, @Valid @NotNull Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
		this.instanteCadastro = LocalDateTime.now();
	}
	
	@Deprecated
	public Pergunta() {
		// TODO Auto-generated constructor stub
	}

	public String getTitulo() {
		return titulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
	
	

}
