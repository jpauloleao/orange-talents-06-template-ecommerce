package br.com.zup.orange.MercadoLivre.Produto.Imagem;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface EnviaImagemServidor {

	List<String> envia(List<MultipartFile> imagens);
}
