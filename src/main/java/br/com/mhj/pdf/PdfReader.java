package br.com.mhj.pdf;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import br.com.mhj.csv.EnumMes;

public class PdfReader {

	Dados dados;

	public PdfReader() {
		super();
		dados = new Dados();
	}
	
	public void read(String path, String password) throws IOException {
		
		String[] leitura = leituraPdf(path, password);
		
		read(leitura, path);
	}

	public void read(String path) throws IOException {

		String[] leitura = leituraPdf(path);
		
		read(leitura, path);

	}
	
	private void read (String[] leitura, String path) {
		String name = path.substring(path.lastIndexOf("\\") + 1, path.indexOf(".pdf"));

		EnumTipoCartao tipoCartao = EnumTipoCartao.getTipoCartao(name);

		switch (tipoCartao) {
		case FREE:
		case PLATINUM:
			tratarLinhaSantander(leitura);
			break;
		case NUBANK:
			tratarLinhaN(leitura);
			break;
		case RIACHUELO:
			tratarLinhaRiachuelo(leitura);
			break;
		default:
			break;
		}
		
	}

	private String[] leituraPdf(String path, String password) throws IOException {
		try (PDDocument document = PDDocument.load(new File(path), password)) {
			document.setAllSecurityToBeRemoved(true);
			return leituraPdf(document);
		}

	}

	private String[] leituraPdf(String path) throws IOException {
		try (PDDocument document = PDDocument.load(new File(path))) {
			return leituraPdf(document);
		}
	}

	private String[] leituraPdf(PDDocument document) throws IOException {
		document.getClass();

		// if (!document.isEncrypted()) {

		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		stripper.setSortByPosition(true);

		PDFTextStripper tStripper = new PDFTextStripper();

		String pdfFileInText = tStripper.getText(document);
		System.out.println("Text:" + pdfFileInText);

		// split by whitespace
		String lines[] = pdfFileInText.split("\\r?\\n");

		return lines;
		// }

		// return null;
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

	private void tratarLinhaSantander(String lines[]) {
		for (String line : lines) {
			// System.out.println(line);
			tratarLinhaSantander(line);
		}
	}

	private void tratarLinhaSantander(String line) {
		// System.out.println(line.substring(0, 10));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat();
		try {
			@SuppressWarnings("unused")
			Date parse = dateFormat.parse(line.substring(0, 10));
			Number parse2 = decimalFormat.parse(line.substring(line.indexOf("R$") + 3, line.length()));
			if (parse2.doubleValue() < 0) {
				return;
			}
			// System.out.println(line.substring(0, 10));
			// System.out.println(line.substring(11, line.indexOf("US$") - 1));
			// System.out.println(line.substring(line.indexOf("R$") + 3, line.length()));

			Dado dado = new Dado();
			dado.setType(parse2.doubleValue() > 0 ? EnumType.EXPENSIVE.id : EnumType.INCOME.id);
			dado.setDate(line.substring(0, 10));
			dado.setItem("Compra Cartao");
			dado.setAmount(line.substring(line.indexOf("R$") + 3, line.length()));
			dado.setParentCategory("Cartao");
			dado.setCategory("Free");
			dado.setAccType("Cartao");
			dado.setAccount("Santander");
			dado.setNotes(line.substring(11, line.indexOf("US$") - 1));
			dado.setLabel("Cartao");
			dado.setStatus("R");

			dados.addDado(dado);

		} catch (ParseException e) {
		}

	}

	public Dados getDados() {
		return dados;
	}

	private void tratarLinhaN(String lines[]) {
		for (String line : lines) {
			// System.out.println(line);
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

			EnumMes enumMes = EnumMes.getMes(mes);

			if (enumMes.equals(EnumMes.DESCONHECIDO)) {
				return;
			}

			System.out.println(line);

			System.out.println(line.substring(0, 2));
			System.out.println(line.substring(3, 6));

			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			System.out.println(year);

			c.set(year, enumMes.getCodigo(), day);

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

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// DecimalFormat decimalFormat = new DecimalFormat();
		// try {
		// @SuppressWarnings("unused")
		// Date parse = dateFormat.parse(line.substring(0, 10));
		// Number parse2 = decimalFormat.parse(line.substring(line.indexOf("R$") + 3,
		// line.length()));
		// if (parse2.doubleValue() < 0) {
		// return;
		// }
		//// System.out.println(line.substring(0, 10));
		//// System.out.println(line.substring(11, line.indexOf("US$") - 1));
		//// System.out.println(line.substring(line.indexOf("R$") + 3, line.length()));
		//
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
		//
		// } catch (ParseException e) {
		// }

	}

	private void tratarLinhaRiachuelo(String lines[]) {
		for (String line : lines) {
			// System.out.println(line);
			tratarLinhaRiachuelo(line);
		}
	}

	private void tratarLinhaRiachuelo(String line) {
		// System.out.println(line.substring(0, 10));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		DecimalFormat decimalFormat = new DecimalFormat();
		try {
			String[] split = line.split(" ");
			
			
			@SuppressWarnings("unused")
			Date parse = dateFormat.parse(split[0]);
			Number parse2 = decimalFormat.parse(split[6]);
			
			if ("C".equals(split[7])) {
				return;
			}
			// System.out.println(line.substring(0, 10));
			// System.out.println(line.substring(11, line.indexOf("US$") - 1));
			// System.out.println(line.substring(line.indexOf("R$") + 3, line.length()));

			Dado dado = new Dado();
			dado.setType(parse2.doubleValue() > 0 ? EnumType.EXPENSIVE.id : EnumType.INCOME.id);
			dado.setDate(split[0]);
			dado.setItem("Compra Cartao");
			dado.setAmount(parse2.toString());
			dado.setParentCategory("Cartao");
			dado.setCategory("Riachuelo");
			dado.setAccType("Cartao");
			dado.setAccount("Riachuelo");
			dado.setNotes(split[2] + " " + split[3]);
			dado.setLabel("Cartao");
			dado.setStatus("R");

			dados.addDado(dado);

		} catch (ParseException | ArrayIndexOutOfBoundsException e) {
		}

	}
}
