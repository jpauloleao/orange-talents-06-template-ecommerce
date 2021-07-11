package br.com.zup.orange.MercadoLivre.Produto.Imagem;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagemRequest {
	
	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<>();
	
	public ImagemRequest(@Size(min = 1) @NotNull List<MultipartFile> imagens) {
		super();
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}

}
