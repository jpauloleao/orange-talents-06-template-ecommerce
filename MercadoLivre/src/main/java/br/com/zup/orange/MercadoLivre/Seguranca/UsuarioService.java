package br.com.zup.orange.MercadoLivre.Seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.orange.MercadoLivre.Usuario.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UserDetailsMapper user;
    
    @PersistenceContext
    private EntityManager entityManager;

    //Sobrescrevendo o loadUserByUsername para receber um username e devolver usuario logado
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TypedQuery<Usuario> query = entityManager
                .createQuery("select u from Usuario u where u.login = :username", Usuario.class)
                .setParameter("username", username);

        List<?> usuarios = query.getResultList();
        
        if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Não foi possível encontrar usuário com email: " + username);
		}

		return user.map(usuarios.get(0));
    }
}
