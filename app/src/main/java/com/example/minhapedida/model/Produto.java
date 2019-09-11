package com.example.minhapedida.model;

import java.io.Serializable;

public class Produto implements Serializable {
    private String nome;
    private Double valor;

    public Produto() {
    }

    public Produto(String nome, Double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setValor(String valor) {
        try {
            this.valor = Double.parseDouble(valor);
        } catch (Exception e){
            this.valor = null;
        }
    }

    @Override
    public String toString() {
        return nome;
    }
}
