package com.mhj.jogos.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mhj.jogos.enums.TipoJogo;

public class Jogo {

	private Long id;
	private String nome;
	private BigDecimal valor;
	private TipoJogo tipoJogo;

	private List<Concurso> concursos;

	private List<Aposta> apostas;

	public Jogo() {
		concursos = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Concurso> getConcursos() {
		return concursos;
	}

	public void setConcursos(List<Concurso> concursos) {
		this.concursos = concursos;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoJogo getTipoJogo() {
		return tipoJogo;
	}

	public void setTipoJogo(TipoJogo tipoJogo) {
		this.tipoJogo = tipoJogo;
	}

	public List<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(List<Aposta> apostas) {
		this.apostas = apostas;
	}

	public void validaApostas() {
		boolean ac15 = false;
		BigDecimal v15 = new BigDecimal(0);
		BigDecimal v14 = new BigDecimal(0);
		BigDecimal v13 = new BigDecimal(0);
		BigDecimal v12 = new BigDecimal(0);
		BigDecimal v11 = new BigDecimal(0);
		BigDecimal vConc = new BigDecimal(0);
		for (Aposta aposta : apostas) {
			validarAposta(aposta);
		}
		for (Aposta aposta : apostas) {
			System.out.println("Acertos 15: " + aposta.getPremioAposta15().getAcertos());
			System.out.println(aposta.getPremioAposta15().getConcursos().size());
			v15 = soma(aposta.getPremioAposta15().getConcursos(), 15);
			ac15 = aposta.getPremioAposta15().getConcursos().size() > 0;

			System.out.println("Acertos 14: " + aposta.getPremioAposta14().getAcertos());
			System.out.println(aposta.getPremioAposta14().getConcursos().size());
			v14 = soma(aposta.getPremioAposta14().getConcursos(), 14);

			System.out.println("Acertos 13: " + aposta.getPremioAposta13().getAcertos());
			System.out.println(aposta.getPremioAposta13().getConcursos().size());
			v13 = soma(aposta.getPremioAposta13().getConcursos(), 13);

			System.out.println("Acertos 12: " + aposta.getPremioAposta12().getAcertos());
			System.out.println(aposta.getPremioAposta12().getConcursos().size());
			v12 = soma(aposta.getPremioAposta12().getConcursos(), 12);

			System.out.println("Acertos 11: " + aposta.getPremioAposta11().getAcertos());
			System.out.println(aposta.getPremioAposta11().getConcursos().size());
			v11 = soma(aposta.getPremioAposta11().getConcursos(), 11);
//			List<Concurso> concursos = aposta.getPremioAposta().getConcursos();
//			for (Concurso concurso : concursos) {
//				System.out.println("Concurso:" + concurso.getNumero());
//			}
		}
		System.out.println("Valor 15: " + v15);
		System.out.println("Valor 14: " + v14);
		System.out.println("Valor 13: " + v13);
		System.out.println("Valor 12: " + v12);
		System.out.println("Valor 11: " + v11);
		v15 = v15.add(v14);
		v15 = v15.add(v13);
		v15 = v15.add(v12);
		v15 = v15.add(v11);
		System.out.println("Total: " + v15);
		
		for (Concurso concurso : this.concursos) {
			vConc = vConc.add(concurso.getJogo().getValor());
		}
		
		System.out.println("Total J: " + vConc);

		if (ac15) {
			System.exit(0);
		}
	}

	private void validarAposta(Aposta aposta) {
		int count = 0;
		int a15 = 0;
		int a14 = 0;
		int a13 = 0;
		int a12 = 0;
		int a11 = 0;
		List<Dezena> dezenasA = aposta.getDezenas();
		List<Integer> numsA = getNums(dezenasA);
		for (Concurso concurso : this.concursos) {
			for (Integer numA : numsA) {
				List<Dezena> dezenasC = concurso.getDezenas();
				List<Integer> numsC = getNums(dezenasC);
				for (Integer numC : numsC) {
					if (numsC.contains(numA)) {
						count++;
						break;
					}
				}
				if (count == 15) {
					aposta.getPremioAposta15().setAcertos(a15++);
					aposta.getPremioAposta15().getConcursos().add(concurso);
				} else if (count == 14) {
					aposta.getPremioAposta14().setAcertos(a14++);
					aposta.getPremioAposta14().getConcursos().add(concurso);
				} else if (count == 13) {
					aposta.getPremioAposta13().setAcertos(a13++);
					aposta.getPremioAposta13().getConcursos().add(concurso);
				} else if (count == 12) {
					aposta.getPremioAposta12().setAcertos(a12++);
					aposta.getPremioAposta12().getConcursos().add(concurso);
				} else if (count == 11) {
					aposta.getPremioAposta11().setAcertos(a11++);
					aposta.getPremioAposta11().getConcursos().add(concurso);
				}
			}
//			System.out.println("count: " + count);
			count = 0;
		}
	}

	private List<Integer> getNums(List<Dezena> dezenasA) {
		List<Integer> ret = new ArrayList<Integer>();
		for (Dezena dezena : dezenasA) {
			ret.add(dezena.getNumero());
		}
		Collections.sort(ret);
		return ret;
	}

	private BigDecimal soma(List<Concurso> concursos, Integer acertos) {
		BigDecimal ret = new BigDecimal(0D);
		for (Concurso c : concursos) {
			List<Premio> premios = c.getPremios();
			for (Premio premio : premios) {
				if (premio.getQuantidadeAcertos().equals(acertos)) {
					ret = ret.add(premio.getValor());
				}
			}
		}
		return ret;
	}

}
