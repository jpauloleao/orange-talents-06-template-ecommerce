package br.com.zup.orange.MercadoLivre.Produto.Pergunta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class OrganizaDadosEmail {

	public OrganizaDadosEmail(@NotBlank String assunto, @NotBlank String produto,
			@NotBlank @Email String usuarioInteressado, @NotBlank @Email String dono, String pergunta) {
		System.out.println("Assunto: " + assunto);
		System.out.println("Produto: " + produto);
		System.out.println("Interessado: " + usuarioInteressado);
		System.out.println("Dono: " + dono);
		System.out.println("Pergunta: " + pergunta);
	}

}
