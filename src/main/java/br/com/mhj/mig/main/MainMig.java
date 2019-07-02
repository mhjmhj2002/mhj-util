package br.com.mhj.mig.main;

import java.io.IOException;
import java.text.ParseException;

import br.com.mhj.pdf.PdfBuild;
import br.com.mhj.xls.XlsBuild;

public class MainMig {

	public static void main(String[] args) throws IOException, ParseException {

		PdfBuild pdfBuild = new PdfBuild();

		pdfBuild.buildSantander();

//		pdfBuild.buildNubank();

//		XlsBuild xlsBuild = new XlsBuild();

//		xlsBuild.buildSantander();

	}
}