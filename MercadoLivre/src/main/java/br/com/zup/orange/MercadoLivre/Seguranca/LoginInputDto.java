package br.com.zup.orange.MercadoLivre.Seguranca;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginInputDto {

    private String login;
    private String senha;

    public LoginInputDto(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken build() {
        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }
}
