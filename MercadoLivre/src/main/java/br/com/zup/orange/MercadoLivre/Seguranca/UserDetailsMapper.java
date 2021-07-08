package br.com.zup.orange.MercadoLivre.Seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserDetailsMapper {
	
	@Autowired
	UserDetails map(Object usuario);
}
