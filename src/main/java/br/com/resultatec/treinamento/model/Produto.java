package br.com.resultatec.treinamento.model;

import br.com.resultatec.treinamento.exception.EstoqueInsuficienteException;

public class Produto {

    private double valorVenda;
    private double estoque;
    private boolean permiteEstoqueNegativo;

    public Produto() {
        this.setValorVenda(0);
        this.setPermiteEstoqueNegativo(false);
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    public boolean isPermiteEstoqueNegativo() {
        return permiteEstoqueNegativo;
    }

    public void setPermiteEstoqueNegativo(boolean permiteEstoqueNegativo) {
        this.permiteEstoqueNegativo = permiteEstoqueNegativo;
    }

    public void saidaEstoque(double quantidadeParaDarBaixa) throws EstoqueInsuficienteException{
        if (!permiteEstoqueNegativo && estoque < quantidadeParaDarBaixa) {
            throw new EstoqueInsuficienteException("Produto não possui estoque o suficiente para compras");
        }
        estoque -=quantidadeParaDarBaixa;
    }

    public void entradaEstoque(Produto produto, double quantidadeParaDarEntrada) {
        estoque += quantidadeParaDarEntrada;
    }
}
