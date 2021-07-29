package br.com.zup.orange.MercadoLivre.Compra;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.orange.MercadoLivre.Produto.Produto;
import br.com.zup.orange.MercadoLivre.Usuario.Usuario;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Positive
	private Integer quantidade;

	@Enumerated(EnumType.STRING)
	private FormasPagamento formaPagamento;

	@NotNull
	@ManyToOne
	private Produto produto;

	@NotNull
	@ManyToOne
	private Usuario comprador;

	@NotNull
	@Positive
	private BigDecimal valorAtual;


	@Enumerated(EnumType.STRING)
	private StatusCompra status = StatusCompra.INICIADA;


	public Compra(@NotNull @Positive Integer quantidade, FormasPagamento formaPagamento,
			@NotNull Produto produto, @NotNull Usuario comprador, StatusCompra statusCompra) {
		super();
		this.quantidade = quantidade;
		this.formaPagamento = formaPagamento;
		this.produto = produto;
		this.comprador = comprador;
		this.valorAtual = produto.getValor();
		this.status = statusCompra;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public Long getId() {
		return id;
	}

	public FormasPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public Produto getProduto() {
		return produto;
	}
}
