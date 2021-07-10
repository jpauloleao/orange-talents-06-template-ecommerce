package br.com.zup.orange.MercadoLivre.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
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

}
