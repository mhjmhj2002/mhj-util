package br.com.mhj.pdf;

import java.math.BigDecimal;
import java.util.Date;

public class Saldo {

	private Date data;
	private BigDecimal valor;

	protected Date getData() {
		return data;
	}

	protected void setData(Date data) {
		this.data = data;
	}

	protected BigDecimal getValor() {
		return valor;
	}

	protected void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
