package br.com.mhj.xlsx;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import br.com.mhj.enums.EnumType;
import br.com.mhj.pdf.Dado;
import br.com.mhj.pdf.Dados;

public class XlsxReaderSantander extends XlsxReader {

	public XlsxReaderSantander() {
		super();
		dados = new Dados();
	}

	public void read(String path) throws IOException, ParseException {
		Workbook workbook = leituraXlsx(path);
		read(workbook, path);
	}

	private void read(Workbook workbook, String path) throws ParseException {
		if (workbook == null) {
			return;
		}
		tratarLinhas(workbook);
	}

	private void tratarLinhas(Workbook workbook) throws ParseException {
		Sheet sheet = workbook.getSheetAt(0);

		for (Row row : sheet) {
			tratarLinha(row);
		}
	}

	private void tratarLinha(Row row) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat();

		try {
			short cellNum = row.getFirstCellNum();
			Cell cell = row.getCell(cellNum);
			String dataString = cell.getStringCellValue();
			if (dataString.isEmpty()) {
				return;
			}
			@SuppressWarnings("unused")
			Date parse = dateFormat.parse(dataString);

			cellNum++;

			cell = row.getCell(cellNum);
			String descricao = cell.getStringCellValue();
			if (descricao.isEmpty()) {
				return;
			}
			cellNum += 3;

			cell = row.getCell(cellNum);
			String valorCreditoString = cell.getStringCellValue();
			cellNum++;

			cell = row.getCell(cellNum);
			String valorDebitoString = cell.getStringCellValue();

			if (valorCreditoString.isEmpty() || valorDebitoString.isEmpty()) {
				return;
			}

			Number valor;
			if (valorCreditoString.isEmpty()) {
				valor = decimalFormat.parse(valorCreditoString);
			} else {
				valor = decimalFormat.parse(valorDebitoString);
			}
			
			Dado dado = new Dado();
			dado.setType(valor.doubleValue() > 0 ? EnumType.EXPENSIVE.getId() : EnumType.INCOME.getId());
			dado.setDate(dataString);
			dado.setItem("Compra Cartao");
			dado.setAmount(valor.toString());
			dado.setParentCategory("CC");
			dado.setCategory("Van Gogh");
			dado.setAccType("Conta Corrente");
			dado.setAccount("Santander");
			dado.setNotes(descricao);
			dado.setLabel("Conta Corrente");
			dado.setStatus("R");

			dados.addDado(dado);

		} catch (ParseException e) {
			return;
		}

	}

}
