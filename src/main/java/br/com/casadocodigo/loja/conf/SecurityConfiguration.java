package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.dao.UsuarioDAO;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UsuarioDAO usuarioDAO;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
//		.antMatchers("/resources/**").permitAll()
		.antMatchers("/produtos/form").hasRole("ADMIN")
		.antMatchers("/produtos").hasRole("ADMIN")
		.antMatchers("/produtos/").hasRole("ADMIN")
		.antMatchers("/produtos/**").permitAll()
		.antMatchers("/carrinho/**").permitAll()	
		.antMatchers("/pagamento/**").authenticated()	
		.antMatchers("/").permitAll()
		
		.antMatchers("/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn").permitAll()
		
		.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/produtos").permitAll()
		.and()
			.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll() 
            .logoutSuccessUrl("/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDAO).passwordEncoder(new BCryptPasswordEncoder());
	}

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
}
	
	// Forma recomendada de ignorar no filtro de segurança as requisições para
	// recursos estáticos
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true);
		web.ignoring().antMatchers("/resources/**");
	}

}
