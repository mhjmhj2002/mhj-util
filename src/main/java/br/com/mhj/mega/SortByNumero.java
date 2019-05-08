package br.com.mhj.mega;

import java.util.Comparator;

public class SortByNumero implements Comparator<Numero>{

	@Override
	public int compare(Numero num1, Numero num2) {
		return num1.getQtde() - num2.getQtde();
	}

}
