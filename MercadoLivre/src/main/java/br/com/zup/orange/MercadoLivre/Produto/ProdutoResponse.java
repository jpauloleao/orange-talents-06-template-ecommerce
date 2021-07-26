package br.com.zup.orange.MercadoLivre.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zup.orange.MercadoLivre.Categoria.Categoria;
import br.com.zup.orange.MercadoLivre.Produto.Caracteristicas.CaracteristicaProduto;
import br.com.zup.orange.MercadoLivre.Produto.Caracteristicas.CaracteristicaRequest;
import br.com.zup.orange.MercadoLivre.Produto.Imagem.ImagemProduto;
import br.com.zup.orange.MercadoLivre.Produto.Opiniao.Opiniao;
import br.com.zup.orange.MercadoLivre.Produto.Pergunta.Pergunta;
import br.com.zup.orange.MercadoLivre.Valicacoes.ObjectExists;
import br.com.zup.orange.MercadoLivre.Valicacoes.UniqueValue;

public class ProdutoResponse {

	private String nome;

	private String descricao;

	private BigDecimal valor;

	private Produto produto;

	private Double mediaNotasProduto = 0.0;

	private Integer totalNotasProduto;
	
	private Set<CaracteristicaProduto> caracteristicas;
	
	private List<Opiniao> opiniao = new ArrayList<>();

	private List<Pergunta> perguntas = new ArrayList<>();
	
	private List<ImagemProduto> imagens = new ArrayList<>();

	public ProdutoResponse(Produto prd, EntityManager em) {
		nome = prd.getNome();
		descricao = prd.getDescricao();
		valor = prd.getValor();
		caracteristicas = prd.getCaracteristicas();
		imagens = prd.getImagens();
		perguntaProduto(em, prd);
		operacoesOpiniaoProduto(em, prd);

	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Double getMediaNotasProduto() {
		return mediaNotasProduto;
	}

	public Integer getTotalNotasProduto() {
		return totalNotasProduto;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public List<Opiniao> getOpiniao() {
		return opiniao;
	}
	
	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public List<ImagemProduto> getImagens() {
		return imagens;
	}

	public void perguntaProduto(EntityManager em, Produto prod) {
		Query query = em.createQuery("SELECT p FROM Pergunta p WHERE p.produto = :teste");
		query.setParameter("teste", prod);
		List<Pergunta> todasPerguntas = query.getResultList();
		System.out.println(todasPerguntas.size());
		this.perguntas.addAll(todasPerguntas);
	}
	
	public void operacoesOpiniaoProduto(EntityManager em, Produto prod) {
		Query query = em.createQuery("SELECT p FROM Opiniao p WHERE p.produto = :teste");
		query.setParameter("teste", prod);
		List<Opiniao> todasOpinioes = query.getResultList();
		
		for (Opiniao opiniao : todasOpinioes) {
			mediaNotasProduto = mediaNotasProduto + opiniao.getNota();
		}

		mediaNotasProduto = mediaNotasProduto / todasOpinioes.size();
		totalNotasProduto = todasOpinioes.size();
		opiniao.addAll(todasOpinioes);
	}

}
