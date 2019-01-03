package br.com.casadocodigo.loja.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.controllers.FinalizarController;
import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.handlers.ExceptionHandlerController;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.FileSaver;

@EnableWebMvc
@EnableCaching
@ComponentScan(basePackageClasses = { HomeController.class, ExceptionHandlerController.class, ProdutoDAO.class, FileSaver.class, CarrinhoCompras.class,
		FinalizarController.class })
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	// Configurar local para busca dos arquivos View
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		// resolver.setExposeContextBeansAsAttributes(true);
		resolver.setExposedContextBeanNames("carrinhoCompras");
		return resolver;
	}

	// Configuração de local para busca das menságens de erro do sistema
	// Neste caso necessita de uma arquivo messages.properties
	// Inserir UTF-8 ao arquivo
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}

	// Configuração do conversor automático de Data do sistema
	// Necessita da anotation @DateTimeFormat na Entidade
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService formattingConversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
		formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		formatterRegistrar.registerFormatters(formattingConversionService);

		return formattingConversionService;
	}

	// Configuração do receptor de arquivos
	// Necessita de configurar o Anotation Servlet Initializer
	// Com CustomizeRegistration: registration.setMultiPartConfig(new
	// MultipartConfigElement(""))
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	// Ativar o acesso a dados staticos do sistema como "/resources"
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// Configurando para reqisições Rest
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// Enable Caching Bean com o GUAVA
	@Bean
	public CacheManager cacheManager() {
		// return new ConcurrentMapCacheManager();
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5,
				TimeUnit.MINUTES);
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(builder);
		return manager;

	}

	// Ativando a busca de view
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> viewResolvers = new ArrayList<>();
		viewResolvers.add(internalResourceViewResolver());
		viewResolvers.add(new JsonViewResolver());

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(viewResolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}

	// INTERCEPTOR PARA TROCA DE CONFIGURAÇÃO DE LOCALIZAÇÃO
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}

	// Bean para configurar localização no cookie
	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}

	// Configurando Envio de Email
	@Bean
	public MailSender mailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("smtp.gmail.com");
		mailSender.setUsername("java.mail.sender.gsw@gmail.com");
		mailSender.setPassword("javamailgsw");
		mailSender.setPort(587);

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);

		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;

	}

}
