package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfiguration.class };
	}

	// classes de configuração do sistema
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { AppWebConfiguration.class, JPAConfiguration.class };
	}

	// Configurando a raiz do sistema
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	// Ativivando filtros no Sistema
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] { encodingFilter };
	}

	// Ativando recebimento de multipart em formalários
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
