package br.com.mhj.mega;

public class Numero {
	private Integer numero;
	private Integer qtde;

	public Numero(Integer numero, Integer qtde) {
		super();
		this.numero = numero;
		this.qtde = qtde;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

}
