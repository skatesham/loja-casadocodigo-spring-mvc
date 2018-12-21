package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class CarrinhoItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	private TipoPreco tipoPreco;
	
	public BigDecimal getPreco(){
	    return produto.precoPara(tipoPreco);
	}

	public BigDecimal getTotal(int quantidade) {
	    return this.getPreco().multiply(new BigDecimal(quantidade));
	}
}
