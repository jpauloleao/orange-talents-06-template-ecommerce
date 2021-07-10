package br.com.zup.orange.MercadoLivre.Valicacoes;


import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.orange.MercadoLivre.Produto.ProdutoRequest;

public class ProibeDuplicidadeCaracteristicaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return ;
		}
		
		ProdutoRequest request =  (ProdutoRequest) target;
		
		Set<String> nomesIguais = request.CaracteristicasIguais();
	
		
		if(!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "Nome de caracteristicas iguais, e devem ser unicos" + nomesIguais);
		}

	}

}
