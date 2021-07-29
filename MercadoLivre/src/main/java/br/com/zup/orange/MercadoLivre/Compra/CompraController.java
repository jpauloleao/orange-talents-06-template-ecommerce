package br.com.zup.orange.MercadoLivre.Compra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.orange.MercadoLivre.Produto.Produto;
import br.com.zup.orange.MercadoLivre.Produto.Pergunta.Email;
import br.com.zup.orange.MercadoLivre.Seguranca.UsuarioLogado;

@RestController
@RequestMapping("/compra")
public class CompraController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private Email enviaEmail;

	@PostMapping
	@Transactional
	public ResponseEntity<?> requisicaoCompra(@RequestBody @Valid CompraRequest request,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Produto produto = em.find(Produto.class, request.getIdProduto());

		if (produto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Invalido");
		}

		
		Compra compra = request.toModel(usuarioLogado, em);

		em.persist(compra);
		enviaEmail.enviaEmailCompra(compra);

		return ResponseEntity.status(302).body(compra.getFormaPagamento().gerarUrlCompra(compra));

	}

}
