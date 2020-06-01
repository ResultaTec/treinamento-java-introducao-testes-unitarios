package br.com.resultatec.treinamento.crediario;

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
public class CrediarioTest {

    Cliente cliente = new Cliente();
    Venda venda = new Venda(cliente);
    Produto produto = new Produto();

    @Test
    public void ultrapassouLimiteDeCreditoDisponivelParaComprasTest()  {

        cliente.setLimiteCredito(10);
        produto.setValorVenda(20);
        produto.setEstoque(1);

        LimiteCreditoException exception = assertThrows(LimiteCreditoException.class, () -> {
            venda.adicionarProdutoAoCarrinho(produto, 1);
        });

        String retorno = exception.getMessage();

        Assert.isTrue(retorno.equals("Limite de Crédito não é o suficiente para realizar a compra deste produto"),"Mensagem esperada: Limite de Crédito não é o suficiente para realizar a compra deste produto porem retornou: "+ retorno);
     }

    @Test
     public void clienteSemCreditoParaComprasTest() {
        cliente.setLimiteCredito(0);
        produto.setValorVenda(20);
        produto.setEstoque(1);

         LimiteCreditoException exception = assertThrows(LimiteCreditoException.class, () -> {
             venda.adicionarProdutoAoCarrinho(produto, 1);
         });

         String retorno = exception.getMessage();

         Assert.isTrue(retorno.equals("Cliente não possui limite de credito para compras"),"Mensagem esperada: Cliente não possui limite de credito para compras: "+retorno);
    }

    @Test
    public void verificaSeAtualizouCreditoDoClienteAposCompras() throws LimiteCreditoException, ControleEstoqueException, VendaException {
        cliente.setLimiteCredito(10);
        produto.setValorVenda(5);
        produto.setEstoque(1);

        venda.adicionarProdutoAoCarrinho(produto, 1);

        double limiteEsperado = 5;

        Assert.isTrue(cliente.getLimiteCredito() == limiteEsperado, "O limite de credito esperado é de: "+limiteEsperado+" porem retornou "+cliente.getLimiteCredito());
    }


    @Test
    public void aoCancelarUmaVendaDevolverOhCreditoAoCliente() {
        Assert.isTrue(Boolean.FALSE,"");
    }
}
