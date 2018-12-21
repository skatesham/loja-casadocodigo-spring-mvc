package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
public @Data class Preco implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal valor;
    private TipoPreco tipo;
    
}
