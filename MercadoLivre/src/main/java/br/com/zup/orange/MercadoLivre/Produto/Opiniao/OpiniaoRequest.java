package br.com.zup.orange.MercadoLivre.Produto.Opiniao;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zup.orange.MercadoLivre.Produto.Produto;
import br.com.zup.orange.MercadoLivre.Usuario.Usuario;

public class OpiniaoRequest {

	@Min(1) @Max(5)
	private int nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank @Length(max = 500)
	private String descricao;

	public OpiniaoRequest(@Min(1) @Max(5) int nota, @NotBlank String titulo,
			@NotBlank @Length(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuarioLogado) {
		return new Opiniao(nota, titulo, descricao,usuarioLogado,produto);
	}
}
