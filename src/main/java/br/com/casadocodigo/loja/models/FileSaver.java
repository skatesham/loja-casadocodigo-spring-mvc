package br.com.casadocodigo.loja.models;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	@Autowired
	HttpServletRequest request;

	
	/**
	 * Função para enviar imagem a uma pasta e retornar o caminho 
	 * 
	 * @param baseFolder Parta para enviar o arquivo
	 * @param file Arquivo recebido do formulário
	 * @return Caminho do arquivo criado
	 */
	public String write(String baseFolder, MultipartFile file) {
		try {
			String realPath = request.getServletContext().getRealPath("/"+baseFolder);
			String path = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));
			return baseFolder + "/" + file.getOriginalFilename();
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}
