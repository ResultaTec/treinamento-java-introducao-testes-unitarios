package br.com.resultatec.treinamento.estoque;

import br.com.resultatec.treinamento.exception.AtualizarEstoqueQuantidadeNegativaException;
import br.com.resultatec.treinamento.exception.EstoqueInsuficienteException;
import br.com.resultatec.treinamento.model.Produto;


public class ControleEstoque {

    public void saidaEstoque(Produto produto, Double quantidadeParaDarBaixa) throws EstoqueInsuficienteException, AtualizarEstoqueQuantidadeNegativaException {
        if (quantidadeParaDarBaixa < 0) throw new AtualizarEstoqueQuantidadeNegativaException("Não é permitido atualizar o estoque do produto com valor negativo.");

        produto.saidaEstoque(quantidadeParaDarBaixa);
    }

    public void entradaEstoque(Produto produto, Double quantidadeParaDarEntrada) throws AtualizarEstoqueQuantidadeNegativaException {
        if (quantidadeParaDarEntrada < 0) throw new AtualizarEstoqueQuantidadeNegativaException("Não é permitido atualizar o estoque do produto com valor negativo.");

        produto.entradaEstoque(produto, quantidadeParaDarEntrada);
    }
}
