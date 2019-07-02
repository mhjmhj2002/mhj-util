package br.com.mhj.mig.main;

import br.com.mhj.gm.Game;

public class MainGm {

	public static void main(String[] args) {
		Game game = new Game();
		while(true) {
			game.teste(game.getApostas(1,18));
		}
	}

}
