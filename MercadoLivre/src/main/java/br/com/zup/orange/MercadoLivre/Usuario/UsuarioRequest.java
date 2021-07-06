package br.com.zup.orange.MercadoLivre.Usuario;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioRequest {

	@NotBlank @Email
	@Column(nullable = false)
	private String login;
	
	@NotBlank @NotNull @Size(min = 6)
	@Column(nullable = false)
	private String senha;

	public UsuarioRequest(@NotBlank @Email String login, @NotBlank @NotNull @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(login, senha); 
	}
}
