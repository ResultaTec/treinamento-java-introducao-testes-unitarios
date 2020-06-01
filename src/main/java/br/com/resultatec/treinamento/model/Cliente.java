package br.com.resultatec.treinamento.model;

public class Cliente {

    private double limiteCredito;

    public Cliente() {
        this.setLimiteCredito(0);
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
}
