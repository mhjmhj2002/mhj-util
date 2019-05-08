package br.com.mhj.mega;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Mega {
	
	Integer qtde = 20 * 12 * 4;
	
	Integer[] numeros = new Integer[60];
	
	public void teste() {
		Random random = new Random();
		
		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = 0;
		}
		
		for (int i = 1; i < qtde; i++) {
			for (int j = 0; j < 6; j++) {
				int num = random.nextInt(60);
				numeros[num] = numeros[num] + 1;
			}
			if (i > 47 && i % 48 == 0) {
				exibirNumeros();
			}
		}
		
	}

	private void exibirNumeros() {
		List<Numero> nums = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		Integer soma = 0;
		for (int i = 0; i < numeros.length; i++) {
			soma = soma + numeros[i];
			Numero numero = new Numero(i+1, numeros[i]);
			nums.add(numero);
		}
		
		Collections.sort(nums, new SortByNumero());
		
		for (Numero numero : nums) {			
			sb.append(numero.getNumero());
			sb.append(":");
			sb.append(numero.getQtde());
			sb.append(",");
		}
		
		sb.append(soma);
		System.out.println(sb.toString());
	}

}
