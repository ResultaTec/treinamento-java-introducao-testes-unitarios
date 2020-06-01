package br.com.resultatec.treinamento.estoque;

import br.com.resultatec.treinamento.exception.ControleEstoqueException;
import br.com.resultatec.treinamento.model.Produto;

import java.math.BigDecimal;

public class ControleEstoque {

    public void baixarEstoque(Produto produto, Double quantidadeParaDarBaixa) throws ControleEstoqueException {
        if (produtoTemEstoqueDisponivelParaConsumo(produto, quantidadeParaDarBaixa)){
            produto.setEstoque(produto.getEstoque() - quantidadeParaDarBaixa);
        }
    }

    private Boolean produtoTemEstoqueDisponivelParaConsumo(Produto produto, Double quantidadeParaDarBaixa) throws ControleEstoqueException {
        if (!produtoTemEstoqueDisponivel(produto))  throw new ControleEstoqueException("Produto com estoque zerado");
        if (produto.getEstoque() - quantidadeParaDarBaixa < BigDecimal.ZERO.doubleValue()) throw new ControleEstoqueException("Produto não possui estoque o suficiente para compras");
        else return Boolean.TRUE;
    }

    private Boolean produtoTemEstoqueDisponivel(Produto produto) throws ControleEstoqueException {
        if (produto.getEstoque() > BigDecimal.ZERO.doubleValue()) return Boolean.TRUE;
        else  throw new ControleEstoqueException("Produto com estoque zerado");
    }
}
