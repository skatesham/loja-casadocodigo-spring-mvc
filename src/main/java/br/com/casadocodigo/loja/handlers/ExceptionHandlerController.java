package br.com.casadocodigo.loja.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
	
	//@ExceptionHandler(Exception.class)
	public String trataDetalheNaoEncontrado() {
		return "error";
	}
}
