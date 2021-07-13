package br.com.zup.orange.MercadoLivre.Produto.Pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.zup.orange.MercadoLivre.Produto.Produto;
import br.com.zup.orange.MercadoLivre.Usuario.Usuario;

public class PerguntaRequest {

	@NotBlank
	private String titulo;

	@JsonCreator(mode = Mode.PROPERTIES)
	public PerguntaRequest(String titulo) {
		this.titulo = titulo;
	}

	public Pergunta toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
		return new Pergunta(titulo, usuario, produto);
	}

}
