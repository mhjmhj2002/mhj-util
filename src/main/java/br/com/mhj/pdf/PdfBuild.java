package br.com.mhj.pdf;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import br.com.mhj.build.Build;
import br.com.mhj.csv.CsvWriter;
import br.com.mhj.data.MhjUtilDate;
import br.com.mhj.enums.EnumExtensao;
import br.com.mhj.enums.EnumMes;
import br.com.mhj.enums.EnumSeparador;
import br.com.mhj.enums.EnumTipoCartao;

public class PdfBuild extends Build {
	
	public void buildSantander() throws IOException, ParseException {

		Calendar calendar = Calendar.getInstance();

		for (int ano = calendar.get(Calendar.YEAR) - 1; ano <= calendar.get(Calendar.YEAR); ano++) {
			for (int mes = EnumMes.JAN.getCodigo(); mes <= EnumMes.DEZ.getCodigo(); mes++) {
//				tratarMesFree(ano, mes);
//				tratarMesPlatinum(ano, mes);
				tratarMesCC(ano, mes);
			}
		}

	}

	public void buildNubank() throws IOException, ParseException {
		Calendar calendar = Calendar.getInstance();

		for (int ano = calendar.get(Calendar.YEAR) - 1; ano <= calendar.get(Calendar.YEAR); ano++) {
			for (int mes = EnumMes.JAN.getCodigo(); mes <= EnumMes.DEZ.getCodigo(); mes++) {
				tratarMesNubank(ano, mes);
			}
		}
	}

	private void tratarMesFree(int ano, int mes) throws IOException, ParseException {

		EnumMes enumMes = EnumMes.getMesByCodigo(mes);

		String anoMesSeparador = MhjUtilDate.getAnoMesSeparador(ano, enumMes, EnumSeparador.TRACO);

		PdfReaderSantander pdfReader = new PdfReaderSantander();

		String dir = EnumTipoCartao.FREE.getNome() + "\\";
		String dirSaida = "saida\\";

		String entrada = path + dir + EnumTipoCartao.FREE.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador
				+ EnumExtensao.PDF.getExtensao();
		String saida = path + dir + dirSaida + EnumTipoCartao.FREE.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador
				+ EnumExtensao.CSV.getExtensao();
		pdfReader.read(entrada, EnumTipoCartao.FREE);
		
		pdfReader.readResult();
		CsvWriter csvWriter = new CsvWriter(pdfReader.getDados(), saida);
		csvWriter.write();

//		pdfReader = new PdfReader();
//		String entradaNubank = path + EnumTipoCartao.NUBANK.getNome() + EnumExtensao.PDF.getExtensao();
//		String saidaNubank = path + EnumTipoCartao.NUBANK.getNome() + EnumExtensao.CSV.getExtensao();		
//		pdfReader.read(entradaNubank);
//		pdfReader.readResult();	
//		CsvWriter csvWriterNubank = new CsvWriter(pdfReader.getDados(), saidaNubank);
//		csvWriterNubank.write();
//		
//		pdfReader = new PdfReaderSantander();
//		String entradaPlatinum = path + EnumTipoCartao.PLATINUM.getNome() + EnumExtensao.PDF.getExtensao();
//		String saidaPlatinum = path + EnumTipoCartao.PLATINUM.getNome() + EnumExtensao.CSV.getExtensao();		
//		pdfReader.read(entradaPlatinum);
//		pdfReader.readResult();	
//		CsvWriter csvWriterPlatinum = new CsvWriter(pdfReader.getDados(), saidaPlatinum);
//		csvWriterPlatinum.write();
//		
//		pdfReader = new PdfReader();
//		String entradaRiachuelo = path + EnumTipoCartao.RIACHUELO.getNome() + EnumExtensao.PDF.getExtensao();
//		String saidaRiachuelo = path + EnumTipoCartao.RIACHUELO.getNome() + EnumExtensao.CSV.getExtensao();		
//		pdfReader.read(entradaRiachuelo, "040477059838");
//		pdfReader.readResult();	
//		CsvWriter csvWriterRiachuelo = new CsvWriter(pdfReader.getDados(), saidaRiachuelo);
//		csvWriterRiachuelo.write();
	}

	private void tratarMesPlatinum(int ano, int mes) throws IOException, ParseException {

		EnumMes enumMes = EnumMes.getMesByCodigo(mes);

		String anoMesSeparador = MhjUtilDate.getAnoMesSeparador(ano, enumMes, EnumSeparador.TRACO);

		PdfReaderSantander pdfReader = new PdfReaderSantander();

		String dir = EnumTipoCartao.PLATINUM.getNome() + "\\";
		String dirSaida = "saida\\";

		String entrada = path + dir + EnumTipoCartao.PLATINUM.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador
				+ EnumExtensao.PDF.getExtensao();
		String saida = path + dir + dirSaida + EnumTipoCartao.PLATINUM.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador
				+ EnumExtensao.CSV.getExtensao();
		pdfReader.read(entrada, EnumTipoCartao.PLATINUM);
		
		pdfReader.readResult();
		CsvWriter csvWriter = new CsvWriter(pdfReader.getDados(), saida);
		csvWriter.write();

	}

	private void tratarMesCC(int ano, int mes) throws IOException, ParseException {

		String anoMesSeparador = MhjUtilDate.getAnoMesSeparador(ano, mes, EnumSeparador.UNDERLINE);

		PdfReaderSantander pdfReader = new PdfReaderSantander();
		
		pdfReader.setAno(ano);

		String dir = EnumTipoCartao.CC.getNome() + "\\";
		String dirSaida = "saida\\";

		String entrada = path + dir + EnumTipoCartao.EXTRATO_CC.getNome() + EnumSeparador.UNDERLINE.getSimbolo() + anoMesSeparador
				+ EnumExtensao.PDF.getExtensao();
		String saida = path + dir + dirSaida + EnumTipoCartao.CC.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador
				+ EnumExtensao.CSV.getExtensao();
		pdfReader.readCC(entrada);
		
		pdfReader.readResult();
		CsvWriter csvWriter = new CsvWriter(pdfReader.getDados(), saida);
		csvWriter.write();

	}
	
	private void tratarMesNubank(int ano, int mes) throws IOException, ParseException {

		EnumMes enumMes = EnumMes.getMesByCodigo(mes);

		String anoMesSeparador = MhjUtilDate.getAnoMesSeparador(ano, enumMes, EnumSeparador.TRACO);

		PdfReaderNubank pdfReader = new PdfReaderNubank();

		String dir = EnumTipoCartao.NUBANK.getNome() + "\\";
		String dirSaida = "saida\\";

		String entrada = path + dir + EnumTipoCartao.NUBANK.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador
				+ EnumExtensao.PDF.getExtensao();
		String saida = dir + dirSaida + EnumTipoCartao.NUBANK.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador
				+ EnumExtensao.CSV.getExtensao();
		pdfReader.read(entrada);
		pdfReader.readResult();
		CsvWriter csvWriterFree = new CsvWriter(pdfReader.getDados(), saida);
		csvWriterFree.write();
		
	}

}
