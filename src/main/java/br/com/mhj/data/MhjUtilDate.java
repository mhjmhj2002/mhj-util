package br.com.mhj.data;

import br.com.mhj.enums.EnumSeparador;

public class MhjUtilDate {
	
	public static String getAnoMesSeparador(Integer ano, EnumMes mes, EnumSeparador separador) {
		StringBuilder builder = new StringBuilder();
		builder.append(ano);
		builder.append(separador.getSimbolo());
		builder.append(mes.getNumeral());
		return builder.toString();
	}

}
