package br.com.zup.orange.MercadoLivre.Produto.Pergunta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.orange.MercadoLivre.Produto.Produto;
import br.com.zup.orange.MercadoLivre.Seguranca.UsuarioLogado;

@RequestMapping("/produto/")
@RestController
public class PerguntaController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private Email email;

	@Transactional
	@PostMapping(value = "/{id}/pergunta")
	public ResponseEntity<?> adicionaPerguntaProduto(@RequestBody @Valid PerguntaRequest perguntaRequest,
			@PathVariable("id") Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {

		Produto produto = em.find(Produto.class, id);

		Pergunta pergunta = perguntaRequest.toModel(produto, usuarioLogado.get());

		em.persist(pergunta);

		email.enviaEmail(pergunta);

		return ResponseEntity.ok().build();
	}
}
