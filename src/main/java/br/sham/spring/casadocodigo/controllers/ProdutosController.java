package br.sham.spring.casadocodigo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.sham.spring.casadocodigo.models.Produto;

@Controller
public class ProdutosController {

	@RequestMapping("/produtos/form")
	public String form() {
		
		return "/produtos/form";
	}
	
	@RequestMapping(name="/produtos/form", method=RequestMethod.POST)
	public String gravar(Produto produto) {
		
		System.out.println(produto);
		
		return "/produtos/ok";
	}
	
}
