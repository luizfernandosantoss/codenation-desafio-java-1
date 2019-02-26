package br.com.codenation.dao;

import br.com.codenation.dao.TimeDao;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.model.Jogador;

import java.math.BigDecimal;
import java.util.*;


public class JogadorDao {

    private TimeDao timeDao = new TimeDao();
    private static final Map<Long, Jogador> listaJogadores = new HashMap<Long, Jogador>();


    public  void adicionarJogador(Jogador jogador) {
        timeDao.buscarTimePorId(jogador.getIdTime());
        if(listaJogadores.containsKey(jogador.getId())){
            throw new IdentificadorUtilizadoException("Jogador com id = "+jogador.getId() + " Ja esta cadastrado");
        }
        listaJogadores.put(jogador.getId(),jogador);

    }



    public Jogador buscaJogadorPorId(Long idJogador) {

        Jogador jogador  = listaJogadores.get(idJogador);
        if(jogador == null){
            throw new JogadorNaoEncontradoException("Jogador não encontrado");
        }


        return jogador;
    }


    public Map<Long, Jogador> getListaJogadores() {
        return listaJogadores;
    }

    public List<Jogador> buscarJogadoresDoTime(Long idTime){

        timeDao.buscarTimePorId(idTime);

        List<Jogador> jogadoresList = new ArrayList<Jogador>(getListaJogadores().values());
        List<Jogador> jogadoresTime = new ArrayList<Jogador>();
        for(Jogador jogador:jogadoresList){
            if(jogador.getIdTime().equals(idTime)){
                jogadoresTime.add(jogador);
            }
        }

        return jogadoresTime;
    }

    public List<Long> buscarIdJogadoresDoTime(Long idTime){
        List<Long> idJogadores = new ArrayList<Long>();

        for (Jogador jogador:buscarJogadoresDoTime(idTime)){
            idJogadores.add(jogador.getId());
        }
        Collections.sort(idJogadores);
        return idJogadores;
    }
    public Long buscarJogadorMaisVelho(Long idTime){
        List<Jogador> jogadores = buscarJogadoresDoTime(idTime);
        Collections.sort(jogadores,Comparator.comparing(Jogador::getIdade).reversed());
        return jogadores.get(0).getId();
    }
    public Long buscarMelhorJogadorDoTime(Long idTime){
        List<Jogador> jogadores = buscarJogadoresDoTime(idTime);
        Collections.sort(jogadores,Comparator.comparing(Jogador::getNivelHabilidade).reversed());
        return jogadores.get(0).getId();
    }

    public Long buscarJogadorMaiorSalario(Long idTime){
        List<Jogador> jogadores = buscarJogadoresDoTime(idTime);
        Collections.sort(jogadores,Comparator.comparing(Jogador::getSalario).reversed());
        return jogadores.get(0).getId();
    }
    public BigDecimal buscarSalarioDoJogador(Long idJogador){
        return buscaJogadorPorId(idJogador).getSalario();
    }


    public List<Long> buscarTopJogadores(Integer top) {
        List<Jogador> jogadoresList = new ArrayList<Jogador>(getListaJogadores().values());
        Collections.sort(jogadoresList,Comparator.comparing(Jogador::getNivelHabilidade).reversed());
        List<Long> idJogadoresTop = new ArrayList<Long>();
        Integer i = 0;

        for (Jogador jogador:jogadoresList) {
            if (i < top) {
                idJogadoresTop.add(jogador.getId());
                i++;
            }
        }
        return idJogadoresTop;
    }
}
