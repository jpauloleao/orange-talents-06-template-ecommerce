package br.com.zup.orange.MercadoLivre.Seguranca;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class TokenManager {

    @Value("${jwt.expiration}")
    private Long tempoExpiracao;

    @Value("${jwt.secret}")
    private String chave;
    

    public String gerarToken(Authentication authentication) {
    	UserDetails logado = (UserDetails) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + this.tempoExpiracao);

        return Jwts.builder()
                .setIssuer("Api do Mercado Livre")
                .setSubject(logado.getUsername())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, chave)
                .compact();
    }

    //Verificação se token é valido
    public boolean isTokenValido(String token) {

        try {
            Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserName(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(this.chave)
				.parseClaimsJws(jwt).getBody();
		
		return claims.getSubject();
	}
}
