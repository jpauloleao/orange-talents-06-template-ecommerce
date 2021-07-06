package br.com.zup.orange.MercadoLivre.Usuario;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastraUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){
		Usuario usuario = usuarioRequest.toModel();
		manager.persist(usuario);
		
		return ResponseEntity.ok("Cadastrado");
	}
}
