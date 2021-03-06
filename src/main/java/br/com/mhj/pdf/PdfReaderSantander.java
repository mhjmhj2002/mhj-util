package br.com.mhj.pdf;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import br.com.mhj.enums.EnumPalavraChave;
import br.com.mhj.enums.EnumSeparador;
import br.com.mhj.enums.EnumTipoCartao;
import br.com.mhj.enums.EnumType;

public class PdfReaderSantander extends PdfReader {

	public PdfReaderSantander() {
		super();
		dados = new Dados();
		linhaSeparada = new StringBuilder();
	}

	public void read(String path, EnumTipoCartao tipoCartao) throws IOException, ParseException {

		String[] leitura = super.leituraPdf(path);

		read(leitura, path, tipoCartao);

	}

	public void readCC(String path) throws IOException, ParseException {

		String[] leitura = super.leituraPdf(path);

		readCC(leitura, path);

	}

	public void read(String path, String password, EnumTipoCartao tipoCartao) throws IOException, ParseException {

		String[] leitura = leituraPdf(path, password);

		read(leitura, path, tipoCartao);
	}

	private void read(String[] leitura, String path, EnumTipoCartao tipoCartao) throws ParseException {
		if (leitura == null) {
			return;
		}

		tratarLinha(leitura, tipoCartao);

//		String name = path.substring(path.lastIndexOf("\\") - 11, path.indexOf(".pdf"));
//		
//		System.out.println(name);
//
//		EnumTipoCartao tipoCartao = EnumTipoCartao.getTipoCartao(name);
//
//		switch (tipoCartao) {
//		case FREE:
//		case PLATINUM:
//			tratarLinhaSantander(leitura);
//			break;
//		case NUBANK:
//			tratarLinhaN(leitura);
//			break;
//		case RIACHUELO:
//			tratarLinhaRiachuelo(leitura);
//			break;
//		default:
//			break;
//		}

	}

	private void readCC(String[] leitura, String path) throws ParseException {
		if (leitura == null) {
			return;
		}

		tratarLinhaCC(leitura);

	}

//	private String[] leituraPdf(String path, String password) throws IOException {
//		try (PDDocument document = PDDocument.load(new File(path), password)) {
//			document.setAllSecurityToBeRemoved(true);
//			return leituraPdf(document);
//		}
//
//	}

//	private String[] leituraPdf(String path) throws IOException {
//		try (PDDocument document = PDDocument.load(new File(path))) {
//			return leituraPdf(document);
//		}catch(FileNotFoundException e) {
//			return null;
//		}
//	}

	private String[] leituraPdf(PDDocument document) throws IOException {
		document.getClass();

		// if (!document.isEncrypted()) {

		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		stripper.setSortByPosition(true);

		PDFTextStripper tStripper = new PDFTextStripper();

		String pdfFileInText = tStripper.getText(document);
//		System.out.println("Text:" + pdfFileInText);

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

	private void tratarLinha(String lines[], EnumTipoCartao tipoCartao) throws ParseException {
		for (String line : lines) {
			if (trataLinhaVencimento(line)) {
				continue;
			}
			if (trataLinhaSeparada(line)) {
				continue;
			} else {
				if (nuBlocoLinha > 0) {
					line = linhaSeparada.toString();
					nuBlocoLinha = 0;
					linhaSeparada = new StringBuilder();
				}
			}
//			 System.out.println(line);
			if (line.contains(" PARC ")) {
				tratarLinhaParcSantander(line, tipoCartao);
			}
			tratarLinhaVistaSantander(line, tipoCartao);
		}
	}

	private boolean trataLinhaVencimento(String line) throws ParseException {
		if (line.startsWith("Vencimento")) {
			nuLinhaVencimento++;
			return true;
		} else if (nuLinhaVencimento == 1) {
			addDataVencimento(line);
			nuLinhaVencimento = 0;
			return true;
		}
		return false;
	}

	private void addDataVencimento(String line) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date parse = dateFormat.parse(line);
		dtVenc = Calendar.getInstance();
		dtVenc.setTime(parse);
	}

	private boolean trataLinhaSeparada(String line) {
		if (nuBlocoLinha == 0) {
			return trataData(line);
		}
		if (nuBlocoLinha == 1 || nuBlocoLinha == 2) {
			nuBlocoLinha++;
			return true;
		}
		if (nuBlocoLinha == 3) {
			linhaSeparada.append(line);
			nuBlocoLinha++;
			return true;
		}
		if (nuBlocoLinha == 4) {
			nuBlocoLinha++;
			return true;
		}
		if (nuBlocoLinha == 5) {
			nuBlocoLinha = 0;
			linhaSeparada.append(line);
			return false;
		}

		return false;
	}

	private boolean trataData(String line) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (line.length() > 5) {
				return false;
			}
			String dataString = line.substring(0, 5);
			@SuppressWarnings("unused")
			Date parse = dateFormat.parse(dataString);
			linhaSeparada.append(dataString);
			linhaSeparada.append(" ");
			nuBlocoLinha++;
		} catch (ParseException | StringIndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	private void tratarLinhaParcSantander(String line, EnumTipoCartao tipoCartao) {
//		System.out.println(line);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM");
		DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat();

		try {
			String dataString = line.substring(0, 5);
			String descricao = line.substring(6, line.indexOf(" PARC "));
			String valorString = line.substring(line.lastIndexOf(" ") + 1, line.length());
			String parcelas = line.substring(line.indexOf(" PARC ") + 6, line.lastIndexOf(" "));

//			System.out.println(parcelas);
//			System.out.println(descricao);

//			valorString = valorString.replaceAll(",", ".");

			Date data = dateFormat.parse(dataString);
			Number valor = decimalFormat.parse(valorString);
			if (valor.doubleValue() < 0) {
				return;
			}

			String[] split2 = parcelas.split("/");
			Integer numParc = Integer.valueOf(split2[0]);
			Integer totParc = Integer.valueOf(split2[1]);

			Calendar dtCompra = Calendar.getInstance();
			dtCompra.setTime(dtVenc.getTime());

			dtCompra.add(Calendar.MONTH, numParc * -1);

			dataString += "/" + dtCompra.get(Calendar.YEAR);
			Date parse = dateFormat.parse(dataString);

			Dado dado = new Dado();
			dado.setType(
					valor.doubleValue() > 0 ? EnumType.EXPENSIVE.getDescription() : EnumType.INCOME.getDescription());
			dado.setDate(dateFormat2.format(parse));
			dado.setItem("Compra Cartao");
			dado.setAmount(valor.toString());
			dado.setParentCategory("Cartao");
			dado.setCategory(tipoCartao.getNome());
			dado.setAccType("Cart�o de Cr�dito");
			dado.setAccount(tipoCartao.getNome());
			dado.setNotes(descricao + " PARC " + parcelas);
			dado.setLabel("Cartao");
			dado.setStatus("R");

			dados.addDado(dado);

//			System.out.println(dataString);
//			System.out.println(valorString);

		} catch (ParseException | StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	private void tratarLinhaVistaSantander(String line, EnumTipoCartao tipoCartao) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM");
		DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat();
		try {
//			System.out.println(line);
			String dataString = line.substring(0, 5);
			String valorString = line.substring(line.lastIndexOf(" "), line.length());
			String descricao = line.substring(line.indexOf(" "), line.lastIndexOf(" "));

//			valorString = valorString.replaceAll(",", ".");

			Date data = dateFormat.parse(dataString);

			Number valor = decimalFormat.parse(valorString);
			if (valor.doubleValue() < 0) {
				return;
			}

			String[] split2 = dataString.split("/");
			int mesCompra = Integer.valueOf(split2[1]);

			if (mesCompra > dtVenc.get(Calendar.MONTH)) {
				dataString += "/" + (dtVenc.get(Calendar.YEAR) - 1);
			} else {
				dataString += "/" + dtVenc.get(Calendar.YEAR);
			}

			Date parse = dateFormat.parse(dataString);

			Dado dado = new Dado();
			dado.setType(
					valor.doubleValue() > 0 ? EnumType.EXPENSIVE.getDescription() : EnumType.INCOME.getDescription());
			dado.setDate(dateFormat2.format(parse));
			dado.setItem("Compra Cartao");
			dado.setAmount(valor.toString());
			dado.setParentCategory("Cartao");
			dado.setCategory(tipoCartao.getNome());
			dado.setAccType("Cart�o de Cr�dito");
			dado.setAccount(tipoCartao.getNome());
			dado.setNotes(descricao);
			dado.setLabel("Cartao");
			dado.setStatus("R");

			dados.addDado(dado);

//			System.out.println(dataString);
//			System.out.println(valorString);
//			System.out.println(descricao);

		} catch (ParseException | StringIndexOutOfBoundsException e) {
			// e.printStackTrace();
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
		DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
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
			dado.setType(
					parse2.doubleValue() > 0 ? EnumType.EXPENSIVE.getDescription() : EnumType.INCOME.getDescription());
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

	private void tratarLinhaCC(String lines[]) throws ParseException {
		for (String line : lines) {
			tratarLinhaCC(line);
		}
	}

	private void tratarLinhaCC(String line) throws ParseException {
		validarInicio(line);
		validarDetalhe(line);
		validarFim(line);

		System.out.println(line);
	}

	private void validarInicio(String line) throws ParseException {
		try {
			if (dadosExtrato.isFimValidado()
					|| dadosExtrato.isInicioValidado()
					|| !EnumPalavraChave.SALDO_EM.getDescricao().equals(line.substring(0, 8))) {
				return;
			}
		} catch (StringIndexOutOfBoundsException e) {
			return;
		}

		String dataString = line.substring(9, 14) + EnumSeparador.BARRA.getSimbolo() + super.getAno();

		String[] split = line.split(" ");
		String valorString = split[split.length - 1];
		valorString = valorString.replaceAll("\\.", "");
		valorString = valorString.replaceAll(",", ".");
		BigDecimal valor = new BigDecimal(valorString);

		Saldo saldo = new Saldo();
		saldo.setData(dateFormat.parse(dataString));
		saldo.setValor(valor);
		dadosExtrato.setSaldoInicio(saldo);
		dadosExtrato.setInicioValidado(true);
	}

	private void validarDetalhe(String line) {
		if (!dadosExtrato.isInicioValidado()
				|| dadosExtrato.isFimValidado()) {
			return;
		}
		
		Date dataCompra;

		
		DadoExtratoCC dadoExtratoCC = new DadoExtratoCC();
		
//		dadoExtratoCC.setDataCompra(dataCompra);
//		dadoExtratoCC.setDataLancamento(dataLancamento);
//		dadoExtratoCC.setLugarCompra(lugarCompra);
//		dadoExtratoCC.setNumeroDocumento(numeroDocumento);
//		dadoExtratoCC.setTipoCompra(tipoCompra);
//		dadoExtratoCC.setValorCompra(valorCompra);
		
		dadosExtrato.getDados().add(dadoExtratoCC);

	}

	private void validarFim(String line) throws ParseException {
		try {
			if (dadosExtrato.getDados().size() == 0
					|| !EnumPalavraChave.SALDO_EM.getDescricao().equals(line.substring(0, 8))) {
				return;
			}
		} catch (StringIndexOutOfBoundsException e) {
			return;
		}

		String dataString = line.substring(9, 14) + EnumSeparador.BARRA.getSimbolo() + super.getAno();

		String[] split = line.split(" ");
		String valorString = split[split.length - 1];
		valorString = valorString.replaceAll("\\.", "");
		valorString = valorString.replaceAll(",", ".");
		BigDecimal valor = new BigDecimal(valorString);

		Saldo saldo = new Saldo();
		saldo.setData(dateFormat.parse(dataString));
		saldo.setValor(valor);
		dadosExtrato.setSaldoFim(saldo);
		dadosExtrato.setFimValidado(true);

	}
}
