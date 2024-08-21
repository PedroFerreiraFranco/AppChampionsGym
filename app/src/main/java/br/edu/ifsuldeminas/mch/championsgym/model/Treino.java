package br.edu.ifsuldeminas.mch.championsgym.model;

import java.sql.Time;
import java.util.Date;

public class Treino {
    private int id;
    private String nomeExercicio;
    private Time duracao;
    private Date dataTreino;

    public Treino() {
    }

    public Treino(int id, String nomeExercicio, Time duracao, Date dataTreino) {
        this.id = id;
        this.nomeExercicio = nomeExercicio;
        this.duracao = duracao;
        this.dataTreino = dataTreino;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public Time getDuracao() {
        return duracao;
    }

    public void setDuracao(Time duracao) {
        this.duracao = duracao;
    }

    public Date getData() {
        return dataTreino;
    }

    public void setData(Date data) {
        this.dataTreino = data;
    }

    // Método para exibir informações do treino
    @Override
    public String toString() {
        return "Treino{" +
                "id=" + id +
                ", nomeExercicio='" + nomeExercicio + '\'' +
                ", duracao=" + duracao +
                ", data=" + dataTreino +
                '}';
    }
}
