package br.com.zup.orange.MercadoLivre.Seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zup.orange.MercadoLivre.Usuario.Usuario;


@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

	@Override
	public UserDetails map(Object usuario) {						
		return new UsuarioLogado((Usuario)usuario);
	}
}
