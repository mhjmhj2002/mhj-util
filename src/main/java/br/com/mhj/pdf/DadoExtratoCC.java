package br.com.mhj.pdf;

import java.math.BigDecimal;
import java.util.Date;

import br.com.mhj.enums.EnumTipoLancamento;

public class DadoExtratoCC {

	private Date dataLancamento;
	private EnumTipoLancamento tipoCompra;
	private Date dataCompra;
	private String lugarCompra;
	private Long numeroDocumento;
	private BigDecimal valorCompra;

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public EnumTipoLancamento getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(EnumTipoLancamento tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getLugarCompra() {
		return lugarCompra;
	}

	public void setLugarCompra(String lugarCompra) {
		this.lugarCompra = lugarCompra;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

}
