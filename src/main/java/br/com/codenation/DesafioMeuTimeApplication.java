package br.com.codenation;

import br.com.codenation.dao.JogadorDao;
import br.com.codenation.dao.TimeDao;
import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import br.com.codenation.model.Jogador;

import br.com.codenation.model.Time;


public class DesafioMeuTimeApplication implements MeuTimeInterface {


	private TimeDao timeDao = new TimeDao();
	private JogadorDao jogadorDao = new JogadorDao();


	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		timeDao.adicionarTime(new Time(id,nome,dataCriacao,corUniformePrincipal,corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		jogadorDao.adicionarJogador(new Jogador(id,idTime,nome,dataNascimento,nivelHabilidade,salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogadorlocal = jogadorDao.buscaJogadorPorId(idJogador);
		timeDao.definirCapitao(jogadorlocal.getId(),jogadorlocal.getIdTime());
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		return timeDao.buscarCapitaoDoTime(idTime);
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return jogadorDao.buscaJogadorPorId(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return timeDao.buscaNomeTime(idTime);
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return jogadorDao.buscarIdJogadoresDoTime(idTime);
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		return jogadorDao.buscarMelhorJogadorDoTime(idTime);
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		return jogadorDao.buscarJogadorMaisVelho(idTime);
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return timeDao.buscarIdTimes();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		return jogadorDao.buscarJogadorMaiorSalario(idTime);
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return jogadorDao.buscarSalarioDoJogador(idJogador);
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return jogadorDao.buscarTopJogadores(top);

	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		return timeDao.buscarCorCamisaTimeDeFora(timeDaCasa,timeDeFora);
	}

}
