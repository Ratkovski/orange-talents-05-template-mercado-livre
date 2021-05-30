package br.com.zupacademy.ratkovski.mercadolivre.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.zupacademy.ratkovski.mercadolivre.controller.Uploader;

@Component
@Primary
public class UploaderFake implements Uploader {
	
	
	/**
	 * 
	 * @param imagens
	 * @return links para imagens que foram upladadas
	 */
	
public Set<String> envia(List<MultipartFile>imagens){
	return  imagens.stream()
			.map(imagem -> "http://bucket.io/"
            +imagem.getOriginalFilename())
            //+"_"
			//+UUID.randomUUID().toString())
			.collect(Collectors.toSet());
}
}
