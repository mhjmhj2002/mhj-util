package br.com.mhj.pdf;

import java.util.ArrayList;
import java.util.List;

public class DadosExtratoCC {

	private List<DadoExtratoCC> dados;
	private Saldo saldoInicio;
	private Saldo saldoFim;
	private boolean inicioValidado;
	private boolean fimValidado;

	public DadosExtratoCC() {
		super();
		dados = new ArrayList<>();
	}

	protected List<DadoExtratoCC> getDados() {
		return dados;
	}

	protected void setDados(List<DadoExtratoCC> dados) {
		this.dados = dados;
	}

	protected Saldo getSaldoInicio() {
		return saldoInicio;
	}

	protected void setSaldoInicio(Saldo saldoInicio) {
		this.saldoInicio = saldoInicio;
	}

	protected Saldo getSaldoFim() {
		return saldoFim;
	}

	protected void setSaldoFim(Saldo saldoFim) {
		this.saldoFim = saldoFim;
	}

	protected boolean isInicioValidado() {
		return inicioValidado;
	}

	protected void setInicioValidado(boolean validouInicio) {
		this.inicioValidado = validouInicio;
	}

	protected boolean isFimValidado() {
		return fimValidado;
	}

	protected void setFimValidado(boolean fimValidado) {
		this.fimValidado = fimValidado;
	}

}
