package br.com.zup.orange.MercadoLivre.Produto.Imagem;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EnviaImagemServidorDev implements EnviaImagemServidor{ 

	//Sobrescreve o método da interface, para que a imagem vire um link ficticio.  
	@Override
	public List<String> envia(List<MultipartFile> imagens) {

		return imagens.stream()
				.map(imagem -> "http://servidor/"
						+ imagem.getOriginalFilename())
				.collect(Collectors.toList());
	}
	
}
