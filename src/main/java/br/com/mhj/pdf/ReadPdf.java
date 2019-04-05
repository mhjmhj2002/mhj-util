package br.com.mhj.pdf;

import java.io.IOException;
import java.text.ParseException;

public class ReadPdf {

	public static void main(String[] args) throws IOException, ParseException {
		
		PdfBuild pdfBuild = new PdfBuild();
		
		pdfBuild.buildSantander();
		
		
	}
}