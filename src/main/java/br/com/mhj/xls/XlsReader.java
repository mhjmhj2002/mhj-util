package br.com.mhj.xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import br.com.mhj.pdf.Dado;
import br.com.mhj.pdf.Dados;

public abstract class XlsReader {

	Dados dados;
	
	protected Workbook leituraXlsx(String fileLocation) throws IOException {
		try {
			FileInputStream file = new FileInputStream(new File(fileLocation));
			return new HSSFWorkbook(file);
		} catch(FileNotFoundException e) {
			return null;
		}
	}

	public void readResult() {
		for (Dado dado : dados.getDados()) {
			System.out.println(dado.getType());
			System.out.println(dado.getDate());
			System.out.println(dado.getItem());
			System.out.println(dado.getAmount());
			System.out.println(dado.getParentCategory());
			System.out.println(dado.getCategory());
			System.out.println(dado.getAccType());
			System.out.println(dado.getAccount());
			System.out.println(dado.getNotes());
			System.out.println(dado.getLabel());
			System.out.println(dado.getStatus());
		}
	}

	public Dados getDados() {
		return dados;
	}

	public void setDados(Dados dados) {
		this.dados = dados;
	}

}
