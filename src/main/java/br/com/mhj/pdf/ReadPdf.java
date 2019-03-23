package br.com.mhj.pdf;

import java.io.IOException;

import br.com.mhj.csv.CsvWriter;
import br.com.mhj.enums.EnumExtensao;

public class ReadPdf {

	public static void main(String[] args) throws IOException {
		PdfReader pdfReader = new PdfReader();
		
		String path = "C:\\Users\\mhjmh\\OneDrive\\Documentos\\02 MANUEL\\CONTAS\\2019\\cartoes\\";
		
		String entradaFree = path + EnumTipoCartao.FREE.getNome() + EnumExtensao.PDF.getExtensao();
		String saidaFree = path + EnumTipoCartao.FREE.getNome() + EnumExtensao.CSV.getExtensao();		
		pdfReader.read(entradaFree);
		pdfReader.readResult();	
		CsvWriter csvWriterFree = new CsvWriter(pdfReader.getDados(), saidaFree);
		csvWriterFree.write();

		pdfReader = new PdfReader();
		String entradaNubank = path + EnumTipoCartao.NUBANK.getNome() + EnumExtensao.PDF.getExtensao();
		String saidaNubank = path + EnumTipoCartao.NUBANK.getNome() + EnumExtensao.CSV.getExtensao();		
		pdfReader.read(entradaNubank);
		pdfReader.readResult();	
		CsvWriter csvWriterNubank = new CsvWriter(pdfReader.getDados(), saidaNubank);
		csvWriterNubank.write();
		
		pdfReader = new PdfReader();
		String entradaPlatinum = path + EnumTipoCartao.PLATINUM.getNome() + EnumExtensao.PDF.getExtensao();
		String saidaPlatinum = path + EnumTipoCartao.PLATINUM.getNome() + EnumExtensao.CSV.getExtensao();		
		pdfReader.read(entradaPlatinum);
		pdfReader.readResult();	
		CsvWriter csvWriterPlatinum = new CsvWriter(pdfReader.getDados(), saidaPlatinum);
		csvWriterPlatinum.write();
		
		pdfReader = new PdfReader();
		String entradaRiachuelo = path + EnumTipoCartao.RIACHUELO.getNome() + EnumExtensao.PDF.getExtensao();
		String saidaRiachuelo = path + EnumTipoCartao.RIACHUELO.getNome() + EnumExtensao.CSV.getExtensao();		
		pdfReader.read(entradaRiachuelo, "040477059838");
		pdfReader.readResult();	
		CsvWriter csvWriterRiachuelo = new CsvWriter(pdfReader.getDados(), saidaRiachuelo);
		csvWriterRiachuelo.write();
		
		
	}
}