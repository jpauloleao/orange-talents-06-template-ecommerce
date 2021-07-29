package br.com.zup.orange.MercadoLivre.Compra;

import javax.validation.Valid;

public enum FormasPagamento {

	PAYPAL {
		@Override
		public String gerarUrlCompra(@Valid Compra compra) {
			Long idComprador = compra.getComprador().getId();
			Long idCompra = compra.getId();
			return "paypal.com?buyerId=" + idComprador + "&redirectUrl=" + "https://paypal/compra/" + idCompra;
		}
	},
	PAGSEGURO {
		@Override
		public String gerarUrlCompra(@Valid Compra compra) {
			Long idComprador = compra.getComprador().getId();
			Long idCompra = compra.getId();
			return "pagseguro.com?buyerId=" + idComprador + "&redirectUrl=" + "https://pagseguro/compra/" + idCompra;
		}
	};

	public abstract String gerarUrlCompra(Compra compra);
}
