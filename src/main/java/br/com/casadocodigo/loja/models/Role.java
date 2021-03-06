package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
public @Data class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	private String nome;

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
