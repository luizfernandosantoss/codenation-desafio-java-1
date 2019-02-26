package br.com.codenation.dao;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

import br.com.codenation.model.Time;


import java.util.*;


public class TimeDao {

    private static final Map<Long, Time> listaTime = new HashMap<Long, Time>();


    public void adicionarTime(Time time) {
        if (listaTime.containsKey(time.getId())){
            throw new IdentificadorUtilizadoException("Time de id "+time.getId() + " Ja esta cadastrado");
        }
        listaTime.put(time.getId(),time);

    }

    public String buscaNomeTime(Long idTime) {
        String  nomeTime = null;
        try{
            nomeTime = buscarTimePorId(idTime).getNome();
        }catch (NullPointerException e){
            throw new TimeNaoEncontradoException("Time não encontrado");
        }

        return nomeTime;
    }
    public Time buscarTimePorId(Long idTime){
        Time time = null;
        time  = listaTime.get(idTime);
        if(time == null){
            throw new TimeNaoEncontradoException("Time não encontrado");
        }
        return time;
    }

    public void definirCapitao(Long idJogador,Long idTime) {
        Time time = buscarTimePorId(idTime);
        time.setCapitao(idJogador);
    }

    public Long buscarCapitaoDoTime(Long idTime) {
        if (buscarTimePorId(idTime).getCapitao() == null){
            throw new CapitaoNaoInformadoException("Time não tem capitão");
        }
        return buscarTimePorId(idTime).getCapitao();
    }
    public List<Time> buscarTimes(){
        List<Time> times = new ArrayList<Time>(getListaTime().values());
        return times;
    }

    public Map<Long, Time> getListaTime() {
        return listaTime;
    }


    public List<Long> buscarIdTimes() {
        List<Long> listId = new ArrayList<Long>();
        for (Time time:buscarTimes()){
            listId.add(time.getId());
        }
        Collections.sort(listId);
        return listId;
    }
    public  String buscarCorCamisaTimeDeFora(Long idTimeDaCasa,Long idTimeDeFora){
        Time timeDaCasa = buscarTimePorId(idTimeDaCasa);
        Time timeDeFora = buscarTimePorId(idTimeDeFora);
        if(timeDaCasa.getCorUniformePrincipal().toUpperCase().equals(timeDeFora.getCorUniformePrincipal().toUpperCase())){
            return timeDeFora.getCorUniformeSecundario();
        }
        else if(!timeDaCasa.getCorUniformePrincipal().toUpperCase().equals(timeDeFora.getCorUniformePrincipal().toUpperCase())){
            return timeDaCasa.getCorUniformePrincipal();
        }
        return null;
    }

}
