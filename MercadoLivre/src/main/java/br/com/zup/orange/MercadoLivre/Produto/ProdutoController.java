package br.com.zup.orange.MercadoLivre.Produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.orange.MercadoLivre.Seguranca.UsuarioLogado;
import br.com.zup.orange.MercadoLivre.Valicacoes.ProibeDuplicidadeCaracteristicaValidator;

@RequestMapping("/produto")
@RestController
public class ProdutoController {

	@PersistenceContext
	EntityManager em;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeDuplicidadeCaracteristicaValidator());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastraProduto(@AuthenticationPrincipal UsuarioLogado usuarioLogado,
			@RequestBody @Valid ProdutoRequest produtoRequest) {

		Produto produto = produtoRequest.toModel(em, usuarioLogado);

		em.persist(produto);
		
		return ResponseEntity.ok().build();
	}
}
