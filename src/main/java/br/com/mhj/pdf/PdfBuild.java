package br.com.mhj.pdf;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import br.com.mhj.csv.CsvWriter;
import br.com.mhj.data.EnumMes;
import br.com.mhj.data.MhjUtilDate;
import br.com.mhj.enums.EnumExtensao;
import br.com.mhj.enums.EnumSeparador;

public class PdfBuild {

	public void buildSantander() throws IOException, ParseException {
		
		Calendar calendar = Calendar.getInstance();
		
		for (int ano = calendar.get(Calendar.YEAR) - 1; ano <= calendar.get(Calendar.YEAR); ano++) {
			for (int mes = EnumMes.JAN.getCodigo(); mes <= EnumMes.DEZ.getCodigo(); mes++) {
					tratarMes(ano, mes);
			}
		}
		
	}
	
	private void tratarMes(int ano, int mes) throws IOException, ParseException {
		
		EnumMes enumMes = EnumMes.getMesByCodigo(mes);
		
		String anoMesSeparador = MhjUtilDate.getAnoMesSeparador(ano, enumMes, EnumSeparador.TRACO);
		
		PdfReaderSantander pdfReader = new PdfReaderSantander();
		
		String path = "C:\\Users\\t0404mnl\\Documents\\Manuel\\Pessoal\\contas\\";		
		
		String entradaFree = path + EnumTipoCartao.FREE.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador + EnumExtensao.PDF.getExtensao();
		String saidaFree = path + EnumTipoCartao.FREE.getNome() + EnumSeparador.TRACO.getSimbolo() + anoMesSeparador + EnumExtensao.CSV.getExtensao();		
		pdfReader.read(entradaFree);
		pdfReader.readResult();	
		CsvWriter csvWriterFree = new CsvWriter(pdfReader.getDados(), saidaFree);
		csvWriterFree.write();

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

}
