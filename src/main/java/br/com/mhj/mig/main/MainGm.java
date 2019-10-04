package br.com.mhj.mig.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.mhj.gm.Game;

public class MainGm {

	public static void main(String[] args) {
		int tent = 0;
		boolean fim = false;
		List<Integer> base ;
		Game game = new Game();
//		while(!fim) {
//		base = Arrays.asList(10,11,12,13,16,19);
//		base = Arrays.asList(10,12,15,16,17,18,19);
//		base = Arrays.asList(10,11,12,13,16,19);
//		base = Arrays.asList(10,11,12,13,16,19);
//		base = new ArrayList<Integer>();
//		fim = game.teste(game.getApostas(1,15,base));
//		tent++;
//		}
		
		//teste sorteio 1872
//		base = Arrays.asList(1,2,6,8,9,13,14,15,17,19,20,21,22,23,25);
//		game.teste(game.getApostasAleatorio(1,15,base));
		
		//aleatorio 15 jogos ruim
//		base = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25);
//		game.teste(game.getApostasAleatorio(15,15,base));
		
		//aleatorio 1 jogo 16 numeros
//		base = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25);
//		game.teste(game.getApostasAleatorio(1,16,base));
		
		//aleatorio 1 jogo 17 numeros
//		base = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25);
//		game.teste(game.getApostasAleatorio(1,17,base));
		
		//aleatorio 1 jogo 18 numeros
//		base = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25);
//		game.teste(game.getApostasAleatorio(1,18,base));
		
		//aleatorio 816 jogo 15 numeros (equivale a 1 aposta de 18)
//		base = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25);
//		game.teste(game.getApostasAleatorio(816,15,base));
		
		//base de 7 numeros ruim
//		base = Arrays.asList(10,12,15,16,17,18,19);
//		game.teste(game.getApostas(15,15,base));
		
		//8jogos ruim
//		game.teste(game.getApostasTentativaErro(8,15));
		
		//teste apostas
//		base = Arrays.asList(2,4,10,11,12,14,15,17,18,19,21,22,23,24,25);
//		game.teste(game.getApostasAleatorio(1,15,base));
//		base = Arrays.asList(1,2,4,5,7,8,9,12,13,14,16,17,19,20,25);
//		game.teste(game.getApostasAleatorio(1,15,base));
		
		//Testes contando qtde
		
		//teste sorteio 1872
//		base = Arrays.asList(1,2,6,8,9,13,14,15,17,19,20,21,22,23,25);
//		game.teste(game.getApostasAleatorio(1,15,base),10);
		
		//base de 7 numeros ruim
//		base = Arrays.asList(10,12,15,16,17,18,19);
//		game.teste(game.getApostas(15,15,base), 1);
		
		//8jogos ruim
//		game.teste(game.getApostasTentativaErro(15,15), 1);
		
		//aleatorio 15 jogos ruim
//		base = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25);
//		game.teste(game.getApostasAleatorio(15,15,base), 10);
		
		//1 jogo ruim
//		base = Arrays.asList(2,3,4,7,10,12,13,15,16,17,18,19,20,22,23);
//		game.teste(game.getApostasAleatorio(1,15,base));
		
		//aleatorio 15 jogos ruim
//		base = Arrays.asList(5,6,7,12,13,14,19,20,21);
//		game.teste(game.getApostas(15,15,base),50);
		
		//aleatorio 1 jogo 18 numeros
		base = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25);
		game.teste(game.getApostasAleatorio(1,18,base),50);
		
		System.out.println("Tent: " + tent);
//		game.teste2();
	}

	private static List<List<Integer>> buscarBases() {
		// TODO Auto-generated method stub
		return null;
	}

}
