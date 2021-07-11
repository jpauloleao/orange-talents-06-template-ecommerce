package br.com.zup.orange.MercadoLivre.Produto.Caracteristicas;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.orange.MercadoLivre.Produto.Produto;

public class CaracteristicaRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	
	public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	
	
	public String getNome() {
		return nome;
	}



	public String getDescricao() {
		return descricao;
	}


	public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
		return new CaracteristicaProduto(nome,descricao,produto);
	}
	
}
