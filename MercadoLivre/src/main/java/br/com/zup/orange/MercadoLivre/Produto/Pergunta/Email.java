package br.com.zup.orange.MercadoLivre.Produto.Pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
public interface Email {	
	
	public OrganizaDadosEmail enviaEmail(@NotBlank @Valid Pergunta pergunta);

}
