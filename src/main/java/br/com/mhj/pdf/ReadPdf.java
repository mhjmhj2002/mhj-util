package br.com.mhj.pdf;

import java.io.IOException;

import br.com.mhj.csv.CsvWriter;

public class ReadPdf {

	public static void main(String[] args) throws IOException {
		PdfReader pdfReader = new PdfReader();
		String pathEntrada = "C:\\Users\\t0404mnl\\Downloads\\old\\free.pdf";
		String pathSaida = "C:\\Users\\t0404mnl\\Downloads\\old\\free.csv";

		String pathEntrada2 = "C:\\Users\\t0404mnl\\Downloads\\old\\nubank.pdf";
		String pathSaida2 = "C:\\Users\\t0404mnl\\Downloads\\old\\nubank.csv";
		
//		pdfReader.read(pathEntrada);
//		pdfReader.readResult();	
		
		pdfReader.read(pathEntrada2);
		pdfReader.readResult();	
		
//		CsvWriter csvWriter = new CsvWriter(pdfReader.getDados(), pathSaida);
//		csvWriter.write();
		
		CsvWriter csvWriter2 = new CsvWriter(pdfReader.getDados(), pathSaida2);
		csvWriter2.write();
	}
}