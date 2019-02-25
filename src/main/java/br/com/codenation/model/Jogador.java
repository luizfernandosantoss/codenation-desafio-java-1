package br.com.codenation.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;


public class Jogador {


    private Long id;
    private Long idTime;
    private String nome;
    private Integer idade;
    private LocalDate dataNascimento;
    private BigDecimal salario;
    private Integer nivelHabilidade;


    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        if (id == null || nome.isEmpty() || nome == null){
            throw new IllegalArgumentException("Atributos id e nome não pode ser nulo ");
        }
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento =  dataNascimento;
        validarNivel(nivelHabilidade);
        this.salario = salario;
        Period period = Period.between(dataNascimento,LocalDate.now());
        this.idade =  period.getYears();
    }
    private void validarNivel(Integer nivelHabilidade){
        if(nivelHabilidade >= 0 && nivelHabilidade <=100){
            this.nivelHabilidade = nivelHabilidade;
        }else{
            throw new IllegalArgumentException("Nivel de habilidade invalido digite um numero de 0 a 100");
        }
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", idTime=" + idTime +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", dataNascimento=" + dataNascimento +
                ", salario=" + salario +
                ", nivelHabilidade=" + nivelHabilidade +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }
}
