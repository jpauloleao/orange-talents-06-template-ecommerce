package br.com.zup.orange.MercadoLivre.Usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email
	@Column(nullable = false)
	private String login;
	
	@NotBlank
	@NotNull
	@Size(min = 6)
	@Column(nullable = false)
	private String senha;
	
	@PastOrPresent
	@NotNull
	private LocalDateTime registroCriacao;

	public Usuario(@NotBlank @Email String login, @NotBlank @NotNull @Size(min = 6) String senha) {
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
		this.registroCriacao =  LocalDateTime.now();
	}

	@Deprecated
	public Usuario() {
		
	}
	
	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}
