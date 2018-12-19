package br.sham.spring.casadocodigo.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
public @Data class Preco {
	private BigDecimal valor;
	private TipoPreco tipo;
}
