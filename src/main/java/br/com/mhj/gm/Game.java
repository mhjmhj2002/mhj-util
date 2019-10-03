package br.com.mhj.gm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.mhj.jogos.domain.Aposta;
import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Dezena;
import com.mhj.jogos.domain.Jogo;
import com.mhj.jogos.domain.Premio;
import com.mhj.jogos.enums.TipoJogo;

public class Game {

	private Jogo processFile(InputStream inputStream) throws IOException, ParseException {
		Reader reader = null;
		BufferedReader bufferedReader = null;
		Jogo jogo = new Jogo();
		jogo.setNome(TipoJogo.LOTOFACIL.name());
		jogo.setTipoJogo(TipoJogo.LOTOFACIL);
		jogo.setValor(new BigDecimal(2));

		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			reader = new BufferedReader(new InputStreamReader(inputStream));
			bufferedReader = new BufferedReader(reader);
			String linha = "";
			int numeroRegistro = 1;
			try {
				Concurso concurso = new Concurso();
				Premio premio;
				while ((linha = bufferedReader.readLine()) != null) {
					if (linha.startsWith("<td ") && !linha.contains("&nbsp")) {
						// System.out.println("linha: "+linha+"registro:
						// "+numeroRegistro);
						linha = limpaRegistro(linha);
						// numeroRegistro = new Long(linha);
						switch (numeroRegistro) {
						case 1:
							concurso = new Concurso();
							concurso.setNumero(new Long(linha));
							// //System.out.println(linha);
							break;
						case 2:
							concurso.setData(format.parse(linha));
							// //System.out.println(linha);
							break;
						case 3:
						case 4:
						case 5:
						case 6:
						case 7:
						case 8:
						case 9:
						case 10:
						case 11:
						case 12:
						case 13:
						case 14:
						case 15:
						case 16:
						case 17:
							Dezena dezena = new Dezena();
							dezena.setConcurso(concurso);
							dezena.setNumero(new Integer(linha));
							concurso.getDezenas().add(dezena);
							// System.out.println(linha);
							break;
						case 18:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.setArrecadacao(new BigDecimal(linha));
							break;
						case 19:
							// System.out.println(linha);
							premio = new Premio();
							premio.setConcurso(concurso);
							premio.setQuantidadeAcertos(15);
							concurso.getPremios().add(premio);
							break;
						case 20:
							// System.out.println(linha);
							premio = new Premio();
							premio.setConcurso(concurso);
							premio.setQuantidadeAcertos(14);
							concurso.getPremios().add(premio);
							break;
						case 21:
							// System.out.println(linha);
							premio = new Premio();
							premio.setConcurso(concurso);
							premio.setQuantidadeAcertos(13);
							concurso.getPremios().add(premio);
							break;
						case 22:
							// System.out.println(linha);
							premio = new Premio();
							premio.setConcurso(concurso);
							premio.setQuantidadeAcertos(12);
							concurso.getPremios().add(premio);
							break;
						case 23:
							// System.out.println(linha);
							premio = new Premio();
							premio.setConcurso(concurso);
							premio.setQuantidadeAcertos(11);
							concurso.getPremios().add(premio);
							break;
						case 24:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(0).setValor(new BigDecimal(linha));
							break;
						case 25:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(1).setValor(new BigDecimal(linha));
							break;
						case 26:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(2).setValor(new BigDecimal(linha));
							break;
						case 27:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(3).setValor(new BigDecimal(linha));
							break;
						case 28:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(4).setValor(new BigDecimal(linha));
							break;
						case 29:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(0).setValorAcumulado(new BigDecimal(linha));
							break;
						case 30:
							// System.out.println(linha);
							break;
						case 31:
							// System.out.println(linha);
							numeroRegistro = 0;
							concurso.setJogo(jogo);
							jogo.getConcursos().add(concurso);
							break;
						default:
							numeroRegistro = 0;
							break;
						}
						numeroRegistro++;
					}

				}
			} catch (IOException e) {
				throw e;
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			throw e;
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}

		System.out.println("Concursos: " + jogo.getConcursos().size());
		return jogo;

	}

	private String limpaRegistro(String linha) {

		for (int i = 1; i < 101; i++) {
			if (linha.contains("<td rowspan=\"" + i + "\">")) {
				linha = linha.replaceAll("<td rowspan=\"" + i + "\">", "");
				break;
			}
		}
		return linha.replaceAll("</td>", "");

	}
	
	public boolean teste(List<Aposta> apostas) {
		return teste(apostas, null);
	}

	public boolean teste(List<Aposta> apostas, Integer qtdConcursos) {
		Jogo jogo = leitura();
		jogo.setApostas(apostas);
		if (qtdConcursos == null || qtdConcursos > jogo.getConcursos().size()) {
			return jogo.validaApostas(jogo.getConcursos());
		}
		List<Concurso> concursos = jogo.getConcursos().subList(jogo.getConcursos().size() - qtdConcursos, jogo.getConcursos().size());
		return jogo.validaApostas(concursos); 
	}

	public Jogo leitura() {
		File file = new File("C:\\Users\\t0404mnl\\Downloads\\d_lotfac.htm");
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
			return processFile(stream);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Aposta> getApostas(int qtAp, int qtNum, List<Integer> base) {
		List<Aposta> apostas = new ArrayList<Aposta>();
		List<List<Integer>> numeros = new ArrayList<List<Integer>>();
		for (int i = 0; i < qtAp; i++) {
			List<Integer> list = getList(qtNum, 25, base);
			numeros.add(list);
		}
//		List<Integer> list = Arrays.asList(
//				1,2,3,4,5,
//				6,7,8,9,10,
//				11,12,13,14,15
//				);
		for (List<Integer> nums : numeros) {
			List<Dezena> dezenas = new ArrayList<Dezena>();
			for (Integer integer : nums) {
				Dezena dezena = new Dezena();
				dezena.setNumero(integer);
				dezenas.add(dezena);
			}
			Aposta aposta = new Aposta();
			aposta.setDezenas(dezenas);
			apostas.add(aposta);
		}
		StringBuilder print = new StringBuilder();
		print.append("Numeros: ");
		print.append(System.lineSeparator());
		for (Aposta aposta1 : apostas) {
			List<Dezena> dezenas2 = aposta1.getDezenas();
			for (Dezena dez : dezenas2) {
				print.append(dez.getNumero());
				print.append(",");
			}
			print.append(System.lineSeparator());
		}
		System.out.println(print.toString());
		return apostas;
	}

	private List<Integer> getList(int numbers, int totNumbers, List<Integer> base) {
		Random random = new Random();
		List<Integer> list = new ArrayList<Integer>(base);
		while (list.size() < numbers) {
			try {
				Integer number = random.nextInt(totNumbers) + 1;
				if (!list.contains(number)) {
					list.add(number);
				}
			} catch (Exception e) {
				System.out.println("erro: " + e);
			}
		}
		Collections.sort(list);
		return list;
	}

	private List<Integer> getListTentativaErro(int numbers, List<Integer> base) {
		Random random = new Random();
		List<Integer> list = new ArrayList<Integer>(base);
		int size = base.size();
		while (list.size() > numbers) {
			try {
				Integer index = random.nextInt(size);
				list.remove(list.get(index));
				size--;
			} catch (Exception e) {
				System.out.println("erro: " + e);
				throw e;
			}
		}
		Collections.sort(list);
		return list;
	}

	private List<Integer> getListAleatorio(int numbers, int totNumbers, List<Integer> base) {
		Random random = new Random();
		List<Integer> list = new ArrayList<Integer>(base);
		while (list.size() > numbers) {
			try {
				Integer index = random.nextInt(25) + 1;
				list.remove(index);
			} catch (Exception e) {
				System.out.println("erro: " + e);
			}
		}
		Collections.sort(list);
		return list;
	}
	
	public void teste2() {
		Jogo jogo = leitura();
		Estat est = new Estat(jogo);
		est.estat();
	}

	public List<Aposta> getApostasTentativaErro(int qtAp, int qtNum) {
		Integer[] base1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,19,20,21,22,23,24};//
		Integer[] base2 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,22,23,24};
		Integer[] base3 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,19,20,21,22,23,24};//
		Integer[] base4 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
		Integer[] base5 = {1,2,3,4,5,6,7,8,9,13,14,15,16,17,18,19,20,21,22,23,24};
		Integer[] base6 = {1,2,3,4,5,6,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
		Integer[] base7 = {4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
		Integer[] base8 = {1,2,3,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
		
		List<List<Integer>> bases = new ArrayList<List<Integer>>();
		bases.add(Arrays.asList(base1));
		bases.add(Arrays.asList(base2));
		bases.add(Arrays.asList(base3));
		bases.add(Arrays.asList(base4));
		bases.add(Arrays.asList(base5));
		bases.add(Arrays.asList(base6));
		bases.add(Arrays.asList(base7));
		bases.add(Arrays.asList(base8));
		
		
		List<Aposta> apostas = new ArrayList<Aposta>();
		List<List<Integer>> numeros = new ArrayList<List<Integer>>();
		int posBase = 0;
		int maxPosBase = 7;
		
		for (int i = 0; i < qtAp; i++) {
			List<Integer> list = getListTentativaErro(qtNum, bases.get(posBase));
			numeros.add(list);
			posBase++;
			if (posBase > maxPosBase) {
				posBase = 0;
			}
		}
		
//		List<Integer> list = Arrays.asList(
//				1,2,3,4,5,
//				6,7,8,9,10,
//				11,12,13,14,15
//				);
		for (List<Integer> nums : numeros) {
			List<Dezena> dezenas = new ArrayList<Dezena>();
			for (Integer integer : nums) {
				Dezena dezena = new Dezena();
				dezena.setNumero(integer);
				dezenas.add(dezena);
			}
			Aposta aposta = new Aposta();
			aposta.setDezenas(dezenas);
			apostas.add(aposta);
		}
		StringBuilder print = new StringBuilder();
		print.append("Numeros: ");
		print.append(System.lineSeparator());
		for (Aposta aposta1 : apostas) {
			List<Dezena> dezenas2 = aposta1.getDezenas();
			for (Dezena dez : dezenas2) {
				print.append(dez.getNumero());
				print.append(",");
			}
			print.append(System.lineSeparator());
		}
		System.out.println(print.toString());
		return apostas;
	}

	public List<Aposta> getApostasAleatorio(int qtAp, int qtNum, List<Integer> base) {
		List<Aposta> apostas = new ArrayList<Aposta>();
		List<List<Integer>> numeros = new ArrayList<List<Integer>>();
		for (int i = 0; i < qtAp; i++) {
			List<Integer> list = getListAleatorio(qtNum, 25, base);
			if (numeros.contains(list)) {
//				System.out.println("repetido");
				i--;
			} else {
				numeros.add(list);
			}
		}
		System.out.println("Apostas: " + numeros.size());
		System.out.println("");
//		List<Integer> list = Arrays.asList(
//				1,2,3,4,5,
//				6,7,8,9,10,
//				11,12,13,14,15
//				);
		for (List<Integer> nums : numeros) {
			List<Dezena> dezenas = new ArrayList<Dezena>();
			for (Integer integer : nums) {
				Dezena dezena = new Dezena();
				dezena.setNumero(integer);
				dezenas.add(dezena);
			}
			Aposta aposta = new Aposta();
			aposta.setDezenas(dezenas);
			apostas.add(aposta);
		}
		StringBuilder print = new StringBuilder();
		print.append("Numeros: ");
		print.append(System.lineSeparator());
		for (Aposta aposta1 : apostas) {
			List<Dezena> dezenas2 = aposta1.getDezenas();
			for (Dezena dez : dezenas2) {
				print.append(dez.getNumero());
				print.append(",");
			}
			print.append(System.lineSeparator());
		}
		System.out.println(print.toString());
		return apostas;
	}

}
