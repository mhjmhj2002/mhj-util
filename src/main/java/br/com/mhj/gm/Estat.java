package br.com.mhj.gm;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Dezena;
import com.mhj.jogos.domain.Jogo;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

public class Estat {
	private Jogo jogo;
	Map<Integer,Long> mapTot;
	Map<Integer,Long> map10;
	Map<Integer,Long> map50;
	Map<Integer,Long> map100;

	public Estat(Jogo jogo) {
		this.jogo = jogo;
		mapTot = initMap();
		map10 = initMap();
		map50 = initMap();
		map100 = initMap();
	}
	
	private Map<Integer,Long> initMap() {
		Map<Integer,Long> map = new HashMap<Integer, Long>();
		for (int i = 1; i < 26; i++) {
			map.put(i, 0L);
		}
		return map;
	}

	private void numsQtd(Map<Integer, Long> map, int qtde) {
		List<Concurso> concursos = jogo.getConcursos();
		List<Concurso> subList = concursos.subList(concursos.size() - qtde, concursos.size());
		for (Concurso concurso : subList) {
			List<Dezena> dezenas = concurso.getDezenas();
			for (Dezena dezena : dezenas) {
				map.put(dezena.getNumero(), map.get(dezena.getNumero()) + 1 );
			}
		}
	}
	
	private void printQtd(Map<Integer, Long> map) {
		Long soma = 0L;
		for (Map.Entry<Integer,Long> m : map.entrySet()) {
			System.out.println(m.getKey() + ": " + m.getValue());
			soma = soma + m.getValue();
		}
		System.out.println("Total: " + soma);
	}
	
	private void printDesc(Map<Integer, Long> map) {
		Map<Integer,Long> sorted = map
		        .entrySet()
		        .stream()
		        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
		        .collect(
		            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
		                LinkedHashMap::new));
		 
		   printQtd(sorted);
	}
	
	public void estat() {
//		numsQtd(mapTot,0);
//		printQtd(mapTot);
		
		numsQtd(map10,10);
		printQtd(map10);
		
//		numsQtd(map50,0);
//		printQtd(map50);
//		
//		numsQtd(map100,0);
//		printQtd(map100);
		
		printDesc(map10);
	}

}
