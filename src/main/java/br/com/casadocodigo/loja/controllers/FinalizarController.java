package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@RequestMapping("/pagamento")
public class FinalizarController {

	@Autowired
	CarrinhoCompras carrinho;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MailSender sender;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model) {
		return () -> {
			String uri = "http://book-payment.herokuapp.com/payment";
			try {
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()),
						String.class);
				System.out.println(response);

				// envia email para o usuário
				enviaEmailCompraProduto(usuario);

				model.addFlashAttribute("sucesso", response);
				
				carrinho.limpar();
				
				return new ModelAndView("redirect:/");

			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				model.addFlashAttribute("falha", "Valor maior que o permitido");
				return new ModelAndView("redirect:/");
			}
		};
	}

	/**
	 * Envio de Email Com Java
	 * @param usuario
	 */
	private void enviaEmailCompraProduto(Usuario usuario) {
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra Finalizada com sucesso");
		email.setTo(usuario.getEmail());
		email.setText(String.format("Compra aprovada com sucesso no valor de R$ %.2f", carrinho.getTotal()));
		email.setFrom("java.mail.sender.gsw@gmail.com");
		
		sender.send(email);
		
		System.out.println("Email Enviado Com Sucesso!");
		
	};

}
