package br.com.mhj.csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.com.mhj.pdf.Dado;
import br.com.mhj.pdf.Dados;

public class CsvWriter {

	Dados dados;
	String path;

	public CsvWriter(Dados dados, String path) {
		super();
		this.dados = dados;
		this.path = path;
	}

	public void write() throws IOException {
		StringBuilder builder = new StringBuilder();
		for (Dado dado : dados.getDados()) {
			builder.append(dado.getCsvLine());
			builder.append(System.lineSeparator());
		}
		Files.write(Paths.get(path), builder.toString().getBytes());
	}

}
