package br.com.mhj.pdf;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import br.com.mhj.enums.EnumMes;

public class PdfReaderNubank extends PdfReader{
	
	public void read(String path) throws IOException, ParseException {

		String[] leitura = super.leituraPdf(path);
		
		read(leitura, path);

	}
	
	private void read (String[] leitura, String path) throws ParseException {
		if (leitura == null) {
			return;
		}
	}

	private void tratarLinha(String lines[]) throws ParseException {
		for (String line : lines) {
			tratarLinhaN(line);
		}
		
	}

	private void tratarLinhaN(String line) {
		try {
			String dia = line.substring(0, 2);
			String mes = line.substring(3, 6);

			Integer day = Integer.valueOf(dia);

			if (day < 1 || day > 31) {
				return;
			}

//			EnumMes enumMes = EnumMes.getMes(mes);
//
//			if (enumMes.equals(EnumMes.DESCONHECIDO)) {
//				return;
//			}

			System.out.println(line);

			System.out.println(line.substring(0, 2));
			System.out.println(line.substring(3, 6));

			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			System.out.println(year);

//			c.set(year, enumMes.getCodigo(), day);

			System.out.println(line.substring(7, line.lastIndexOf(" ")));
			System.out.println(line.substring(line.lastIndexOf(" ") + 1, line.length()));

			// System.out.println(c);

			// Dado dado = new Dado();
			// dado.setType(parse2.doubleValue() > 0 ? EnumType.EXPENSIVE.id :
			// EnumType.INCOME.id);
			// dado.setDate(line.substring(0, 10));
			// dado.setItem("Compra Cartao");
			// dado.setAmount(line.substring(line.indexOf("R$") + 3, line.length()));
			// dado.setParentCategory("Cartao");
			// dado.setCategory("Free");
			// dado.setAccType("Cartao");
			// dado.setAccount("Santander");
			// dado.setNotes(line.substring(11, line.indexOf("US$") - 1));
			// dado.setLabel("Cartao");
			// dado.setStatus("R");
			//
			// dados.addDado(dado);

		} catch (StringIndexOutOfBoundsException | NumberFormatException e) {
		}


	}


}
