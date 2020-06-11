package br.com.resultatec.treinamento.model;

import br.com.resultatec.treinamento.crediario.ControleCredito;
import br.com.resultatec.treinamento.estoque.ControleEstoque;
import br.com.resultatec.treinamento.exception.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private ControleCredito controleCredito = new ControleCredito();
    private ControleEstoque controleEstoque = new ControleEstoque();

    private List<Produto> produtos;
    private Cliente cliente;

    public Venda(Cliente cliente) {
        this.setCliente(cliente);
        this.setProdutos(new ArrayList());
    }

    public void adicionarProdutoAoCarrinho(Produto produto, double quantidadeDeCompra) throws VendaException, LimiteCreditoException, EstoqueInsuficienteException, AtualizarEstoqueQuantidadeNegativaException {

        if (produto == null ) throw new VendaException("É obrigatório preencher um produto para adicioar ao carrinho");
        if (quantidadeDeCompra == BigDecimal.ZERO.doubleValue()) throw new VendaException("Não é permitido adicionar quantidade zerada ao carrinho");

        controleCredito.atualizaLimiteCreditoCliente(getCliente(), produto);
        controleEstoque.saidaEstoque(produto, quantidadeDeCompra);

        this.getProdutos().add(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
