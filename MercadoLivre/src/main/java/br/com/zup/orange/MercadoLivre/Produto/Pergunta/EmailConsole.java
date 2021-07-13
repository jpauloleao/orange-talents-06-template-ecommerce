package br.com.zup.orange.MercadoLivre.Produto.Pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
public class EmailConsole implements Email {

	@Override
	public OrganizaDadosEmail enviaEmail(@NotBlank @Valid Pergunta pergunta) {
		return new OrganizaDadosEmail("Nova Pergunta", pergunta.getProduto().getNome(),
				pergunta.getUsuario().getLogin(), pergunta.getProduto().getDono().getLogin(), pergunta.getTitulo());
	}

}
