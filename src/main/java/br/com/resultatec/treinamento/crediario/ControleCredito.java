package br.com.resultatec.treinamento.crediario;

import br.com.resultatec.treinamento.exception.LimiteCreditoException;
import br.com.resultatec.treinamento.model.Cliente;
import br.com.resultatec.treinamento.model.Produto;

import java.math.BigDecimal;

public class ControleCredito {

    public void atualizaLimiteCreditoCliente(Cliente cliente, Produto produto) throws LimiteCreditoException {
        if (temLimiteDeCreditoDisponivelParaConsumo(cliente, produto)) {
            cliente.setLimiteCredito(cliente.getLimiteCredito() - produto.getValorVenda());
        }
    }

    private Boolean temLimiteDeCreditoDisponivelParaConsumo(Cliente cliente, Produto produto) throws LimiteCreditoException{
        if (!temLimiteDeCreditoDisponivel(cliente)) throw new LimiteCreditoException("Cliente não possui limite de credito para compras");
        if (cliente.getLimiteCredito() < produto.getValorVenda()) throw new LimiteCreditoException("Limite de Crédito não é o suficiente para realizar a compra deste produto");
        else return Boolean.TRUE;
    }

    private Boolean temLimiteDeCreditoDisponivel(Cliente cliente) throws LimiteCreditoException{
        if (cliente.getLimiteCredito() == BigDecimal.ZERO.doubleValue()) throw new LimiteCreditoException("Cliente não possui limite de credito para compras");
        else return Boolean.TRUE;
    }
}
