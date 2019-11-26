package br.com.mhj.encode;

import java.math.BigDecimal;

public class Main {

	public static void main(String[] args) {
		BigDecimal valorDisputa =  new BigDecimal(9);
		BigDecimal valorTransacao = null;
		
		if (valorDisputa.compareTo(valorTransacao) > 0) {
			System.out.println("teste");
		}
//		String fileIn = "mhj.7z";
//		String fileOut = "saida3.txt";
//		String dirIn = "C:\\encode\\" + fileIn;
//		String dirOut = "C:\\encode\\decoded\\" + fileOut;
//		String fileIn2 = dirOut;
//		String dirOut2 = "C:\\encode\\encoded\\" + fileIn;
//		
//		File fileI = new File(dirIn);
//		File fileO = new File(dirOut);
//		
//		Encode encode = new Encode(fileI, fileO);
//		try {
//			encode.encode();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		fileI = new File(fileIn2);
//		fileO = new File(dirOut2);
//		
//		Decode decode = new Decode(fileI, fileO);
//		try {
//			decode.decode();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
