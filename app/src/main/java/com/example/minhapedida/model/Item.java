package com.example.minhapedida.model;

import java.io.Serializable;

public class Item implements Serializable {
    private Produto produto;
    private int quantidade;

    public Item() {
    }

    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setQuantidade(String quantidade) {
        try {
            this.quantidade = Integer.parseInt(quantidade);
        } catch (Exception e){
            this.quantidade = 0;
        }
    }

    public void addQuantidade(){
        this.quantidade++;
    }//não utilizado, no control está de outra forma

    public void removeQuantidade(){
        this.quantidade--;
    }

    public Double getSubtotal(){
        return produto.getValor() * quantidade;
    }

    @Override
    public String toString() {
        return produto.getNome() + " - R$ " + produto.getValor()
                + " - " + quantidade + " = R$ " + getSubtotal();
    }
}
