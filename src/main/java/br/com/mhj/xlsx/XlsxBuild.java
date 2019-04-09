package br.com.mhj.xlsx;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import br.com.mhj.build.Build;
import br.com.mhj.data.MhjUtilDate;
import br.com.mhj.enums.EnumExtensao;
import br.com.mhj.enums.EnumMes;
import br.com.mhj.enums.EnumSeparador;
import br.com.mhj.enums.EnumTipoCartao;

public class XlsxBuild extends Build {

	public void buildSantander() throws IOException, ParseException {

		Calendar calendar = Calendar.getInstance();

		for (int ano = calendar.get(Calendar.YEAR) - 1; ano <= calendar.get(Calendar.YEAR); ano++) {
			for (int mes = EnumMes.JAN.getCodigo(); mes <= EnumMes.DEZ.getCodigo(); mes++) {
				tratarMesCC(ano, mes);
			}
		}

	}

	private void tratarMesCC(int ano, int mes) throws IOException, ParseException {

		EnumMes enumMes = EnumMes.getMesByCodigo(mes);

		String anoMesSeparador = MhjUtilDate.getAnoMesSeparador(ano, enumMes, EnumSeparador.TRACO);

		XlsxReaderSantander xlsxReader = new XlsxReaderSantander();

		String dir = EnumTipoCartao.CC.getNome() + "\\";

		String entrada = path + dir + EnumTipoCartao.CC.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador
				+ EnumExtensao.XLS.getExtensao();
		String saida = path + dir + EnumTipoCartao.CC.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador
				+ EnumExtensao.CSV.getExtensao();
		xlsxReader.read(entrada);

		xlsxReader.readResult();
//		CsvWriter csvWriter = new CsvWriter(pdfReader.getDados(), saida);
//		csvWriter.write();

	}

}
