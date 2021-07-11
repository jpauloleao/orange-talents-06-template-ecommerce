package br.com.zup.orange.MercadoLivre.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zup.orange.MercadoLivre.Categoria.Categoria;
import br.com.zup.orange.MercadoLivre.Produto.Caracteristicas.CaracteristicaProduto;
import br.com.zup.orange.MercadoLivre.Produto.Caracteristicas.CaracteristicaRequest;
import br.com.zup.orange.MercadoLivre.Produto.Imagem.ImagemProduto;
import br.com.zup.orange.MercadoLivre.Seguranca.UsuarioLogado;
import br.com.zup.orange.MercadoLivre.Usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Positive @NotNull
	private int quantidade;
	
	@NotBlank @Length(max = 1000)
	private String descricao;
	
	@NotNull @Positive 
	private BigDecimal valor;
	
	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;
	
	@NotNull
	@Valid
	@ManyToOne
	private Usuario dono;
	
	@PastOrPresent
	@NotNull
	private LocalDateTime instanteCadastro;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private List<ImagemProduto> imagens = new ArrayList<>();

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @Positive @NotNull int quantidade,
			@NotBlank @Length(max = 1000) String descricao,
			@NotNull @Valid Categoria categoria, @NotNull @Valid Usuario dono,
			@Size(min = 3) @Valid Collection<CaracteristicaRequest> caracteristicas) {

		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.dono = dono;
		this.instanteCadastro = LocalDateTime.now();
		
		Set<CaracteristicaProduto> todasCaracteristicas = caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet());
		
		this.caracteristicas.addAll(todasCaracteristicas);
	}
	
	@Deprecated
	public Produto() {
		super();
	}

	public void associaImagens(List<String> links) {
		List<ImagemProduto> imagens = links.stream()
				.map(link -> new ImagemProduto(link, this))
				.collect(Collectors.toList());
		this.imagens.addAll(imagens);
	}

	public boolean verificaDono(UsuarioLogado usuarioLogado) {
		return this.dono.getLogin().equals(usuarioLogado.getUsername());
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", quantidade="
				+ quantidade + ", descricao=" + descricao + ", valor=" + valor
				+ ", categoria=" + categoria + ", dono=" + dono
				+ ", caracteristicas=" + caracteristicas + ", imagens="
				+ imagens.toString() + "]";
	}
	
}
