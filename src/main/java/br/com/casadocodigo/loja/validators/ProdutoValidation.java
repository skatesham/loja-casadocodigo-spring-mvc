package br.com.casadocodigo.loja.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

/**
 * Classe validar os produtos criados pelos formulários
 * @author Sham
 *
 */
public class ProdutoValidation implements Validator {

	/**
	 * Configurar o tipo da Classe
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	/**
	 * Configurar a Validação dos atributos da classe
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// Set atributos da classe a ser testados
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");

		Produto produto = (Produto) target;
		if(produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.required");
		}		
	}
}
