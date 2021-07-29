package br.com.zup.orange.MercadoLivre.Produto.Pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import br.com.zup.orange.MercadoLivre.Compra.Compra;

@Component
public interface Email {

	public OrganizaDadosEmail enviaEmail(@NotBlank @Valid Pergunta pergunta);

	public void enviaEmailCompra(@Valid Compra compra);

}
