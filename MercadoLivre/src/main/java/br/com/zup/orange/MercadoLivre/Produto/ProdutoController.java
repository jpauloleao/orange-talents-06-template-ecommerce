package br.com.zup.orange.MercadoLivre.Produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.orange.MercadoLivre.Produto.Imagem.EnviaImagemServidor;
import br.com.zup.orange.MercadoLivre.Produto.Imagem.ImagemRequest;
import br.com.zup.orange.MercadoLivre.Seguranca.UsuarioLogado;
import br.com.zup.orange.MercadoLivre.Valicacoes.ProibeDuplicidadeCaracteristicaValidator;

@RequestMapping("/produto")
@RestController
public class ProdutoController {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	EnviaImagemServidor enviaImagemServidor;
	
	@InitBinder(value = "ProdutoRequest")
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
	
	@GetMapping(value = "/{id}/detalhes")
	@Transactional
	public ResponseEntity<?> detalhesProduto(@AuthenticationPrincipal UsuarioLogado usuarioLogado, @PathVariable("id") Long id) {
		Produto produto = em.find(Produto.class, id);
		
		ProdutoResponse prd = new ProdutoResponse(produto, em);
		
		return ResponseEntity.ok(prd);

	}
	
	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public ResponseEntity<?> adicionaImagensProduto(@AuthenticationPrincipal UsuarioLogado usuarioLogado, @PathVariable("id") Long id , @Valid ImagemRequest request) {
		Produto produto = em.find(Produto.class, id);
		
		//Verificar se usuario logado é dono do produto
		if(!produto.verificaDono(usuarioLogado)){
			throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
		}
		
		//Cria o link das imagens
		List<String> links = enviaImagemServidor.envia(request.getImagens());
		
		//Associa as imagens ao Produto
		produto.associaImagens(links);

		em.merge(produto);

		return ResponseEntity.ok(produto.toString());

	}
}
