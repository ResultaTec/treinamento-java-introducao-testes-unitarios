package br.com.resultatec.treinamento.estoque;

import br.com.resultatec.treinamento.exception.ControleEstoqueException;
import br.com.resultatec.treinamento.exception.LimiteCreditoException;
import br.com.resultatec.treinamento.exception.VendaException;
import br.com.resultatec.treinamento.model.Cliente;
import br.com.resultatec.treinamento.model.Produto;
import br.com.resultatec.treinamento.model.Venda;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ControleEstoqueTest {


    Cliente cliente = new Cliente();
    Venda venda = new Venda(cliente);
    Produto produto = new Produto();

    @Test
    public void naoPermiteComprarProdutosMaiorQueOhEstoqueDisponivel() {

        cliente.setLimiteCredito(10);
        produto.setValorVenda(10);
        produto.setEstoque(1);

        ControleEstoqueException exception = assertThrows(ControleEstoqueException.class, () -> {
            venda.adicionarProdutoAoCarrinho(produto, 2);
        });

        String retorno = exception.getMessage();

        Assert.isTrue(retorno.equals("Produto não possui estoque o suficiente para compras"), "Esperado a mensagem: Produto não possui estoque o suficiente para compras porem retornou: "+retorno);
    }

    @Test
    public void naoPermiteCompraDeProdutoComEstoqueZerado() {

        cliente.setLimiteCredito(10);
        produto.setValorVenda(10);
        produto.setEstoque(0);

        ControleEstoqueException exception = assertThrows(ControleEstoqueException.class, () -> {
            venda.adicionarProdutoAoCarrinho(produto, 2);
        });

        String retorno = exception.getMessage();

        Assert.isTrue(retorno.equals("Produto com estoque zerado"), "Esperado a mensagem: Produto com estoque zerado : "+retorno);
    }

    @Test
    public void naoPermiteCompraDeProdutoComEstoqueNegativo() {
        cliente.setLimiteCredito(10);
        produto.setValorVenda(10);
        produto.setEstoque(-1);

        ControleEstoqueException exception = assertThrows(ControleEstoqueException.class, () -> {
            venda.adicionarProdutoAoCarrinho(produto, 2);
        });

        String retorno = exception.getMessage();

        Assert.isTrue(retorno.equals("Produto com estoque zerado"), "Esperado a mensagem: Produto com estoque zerado : "+retorno);
    }

    @Test
    public void atualizouEstoqueAposACompra() throws ControleEstoqueException, LimiteCreditoException, VendaException {
        cliente.setLimiteCredito(10);
        produto.setValorVenda(10);
        produto.setEstoque(4);

        venda.adicionarProdutoAoCarrinho(produto, 2);

        double estoqueEsperado = 2;

        Assert.isTrue(produto.getEstoque() == estoqueEsperado, "O estoque esperado apos as compras era de "+estoqueEsperado+" porem retornou "+produto.getEstoque());
    }

    @Test void aoRemoverProdutoDoCarrinhoDevolverOhEstoqueAoProduto() {
        Assert.isTrue(Boolean.FALSE, "");
    }

    @Test
    public void aoCancelarUmaVendaDevolverOhEstoqueoAoProduto() {
        Assert.isTrue(Boolean.FALSE,"");
    }
}
