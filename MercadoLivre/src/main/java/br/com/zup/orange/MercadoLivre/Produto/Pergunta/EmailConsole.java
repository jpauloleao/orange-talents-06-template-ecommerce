package br.com.zup.orange.MercadoLivre.Produto.Pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import br.com.zup.orange.MercadoLivre.Compra.Compra;
import br.com.zup.orange.MercadoLivre.Produto.Produto;
import br.com.zup.orange.MercadoLivre.Usuario.Usuario;

@Component
public class EmailConsole implements Email {

	@Override
	public OrganizaDadosEmail enviaEmail(@Valid Pergunta pergunta) {
		return new OrganizaDadosEmail("Nova Pergunta", pergunta.getProduto().getNome(),
				pergunta.getUsuario().getLogin(), pergunta.getProduto().getDono().getLogin(), pergunta.getTitulo());
	}

	@Override
	public void enviaEmailCompra(@Valid Compra compra) {

		System.out.println(compra.getProduto().getDono().getLogin() + " informamos que o " + compra.getComprador().getLogin()
				+ " deseja comprar seu produto " + compra.getProduto().getNome());
	}

}
