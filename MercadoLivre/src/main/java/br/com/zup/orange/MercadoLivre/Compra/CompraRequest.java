package br.com.zup.orange.MercadoLivre.Compra;


import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zup.orange.MercadoLivre.Produto.Produto;
import br.com.zup.orange.MercadoLivre.Seguranca.UsuarioLogado;

public class CompraRequest {

	@NotNull
	@Enumerated(EnumType.STRING)
	private FormasPagamento formaPagamento;

	@NotNull
	@Positive
	private Integer quantidade;

	@NotNull
	private Long idProduto;

	public CompraRequest(@NotNull FormasPagamento formaPagamento, @NotNull @Positive Integer quantidade,
			@NotNull Long idProduto) {
		super();
		this.formaPagamento = formaPagamento;
		this.quantidade = quantidade;
		this.idProduto = idProduto;
	}

	public Compra toModel(UsuarioLogado usuarioLogado, EntityManager em) {
		Produto produto = em.find(Produto.class, idProduto);
		Assert.notNull(produto, "Produto não encontrado");
		
		produto.diminuiEstoque(this);
		
		return new Compra(quantidade, formaPagamento, produto , usuarioLogado.get(), StatusCompra.FINALIZADA);
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
}
