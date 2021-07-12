package br.com.zup.orange.MercadoLivre.Produto.Opiniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.orange.MercadoLivre.Produto.Produto;
import br.com.zup.orange.MercadoLivre.Seguranca.UsuarioLogado;

@RestController
@RequestMapping("/produto")
public class OpiniaoController {
	
	@PersistenceContext
	private EntityManager em;
	
	@PostMapping(value = "/{id}/opiniao")
	@Transactional
	public ResponseEntity<?> adicionaOpiniaoProduto(@AuthenticationPrincipal UsuarioLogado usuarioLogado, @RequestBody @Valid OpiniaoRequest request, @PathVariable("id") Long id) {
		
		Produto produto = em.find(Produto.class,id);
		
		Opiniao opiniao =  request.toModel(produto,usuarioLogado.get());
		
		em.persist(opiniao);		

		return ResponseEntity.ok().build();
	}

}
