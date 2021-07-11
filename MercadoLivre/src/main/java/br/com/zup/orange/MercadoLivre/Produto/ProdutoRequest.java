package br.com.zup.orange.MercadoLivre.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zup.orange.MercadoLivre.Categoria.Categoria;
import br.com.zup.orange.MercadoLivre.Produto.Caracteristicas.CaracteristicaRequest;
import br.com.zup.orange.MercadoLivre.Seguranca.UsuarioLogado;
import br.com.zup.orange.MercadoLivre.Valicacoes.ObjectExists;
import br.com.zup.orange.MercadoLivre.Valicacoes.UniqueValue;

public class ProdutoRequest {

	@NotBlank @UniqueValue(domainClass = Produto.class,fieldName = "nome")
	private String nome;
	
	@Positive @NotNull
	private Integer quantidade;
	
	@NotBlank @Length(max = 1000)
	private String descricao;
	
	@NotNull @Positive
	private BigDecimal valor;
	
	@ObjectExists(domainClass = Categoria.class, fieldName = "id") @NotNull 
	private Long idCategoria;
	
	@Size(min = 3) @Valid
	private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();
	

	public ProdutoRequest(@NotBlank String nome, @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao,
			@NotNull @Positive BigDecimal valor, @NotNull Long idCategoria,
			List<CaracteristicaRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);		
	}

	
	public Produto toModel(EntityManager em, UsuarioLogado dono) {
		Categoria categoria = em.find(Categoria.class, idCategoria);

		return new Produto(nome, valor, quantidade, descricao, categoria, dono.get(), caracteristicas);
	}


	public List<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}


	public Set<String> CaracteristicasIguais() {
		Set<String> nomesIguais = new HashSet<>();
		Set<String> resultados = new HashSet<>();
		
		for(CaracteristicaRequest caracteristica : caracteristicas) {
			
			
			if (!nomesIguais.add(caracteristica.getNome())) {
				resultados.add(caracteristica.getNome());
			}
		}
		
		return resultados;
	}
	
}
