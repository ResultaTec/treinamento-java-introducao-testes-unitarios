package br.com.resultatec.treinamento.model;

public class Produto {

    private double valorVenda;
    private double estoque;

    public Produto() {
        this.setValorVenda(0);
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
}
