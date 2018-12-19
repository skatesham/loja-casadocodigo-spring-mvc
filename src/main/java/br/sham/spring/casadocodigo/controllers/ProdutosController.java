package br.sham.spring.casadocodigo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.sham.spring.casadocodigo.models.Produto;
import br.sham.spring.casadocodigo.models.TipoPreco;

@Controller
public class ProdutosController {

	@RequestMapping("/produtos/form")
	public ModelAndView form() {
		
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipo", TipoPreco.values());
		
		return modelAndView;
	}
	
	@RequestMapping(name="/produtos/form", method=RequestMethod.POST)
	public String gravar(Produto produto) {
		
		System.out.println(produto);
		
		return "/produtos/ok";
	}
	
}
