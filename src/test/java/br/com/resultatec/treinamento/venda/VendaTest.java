package br.com.resultatec.treinamento.venda;

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
public class VendaTest {

    Cliente cliente = new Cliente();
    Venda venda = new Venda(cliente);
    Produto produto = new Produto();

    @Test
    public void naoPermiteAdicionarQuantidadeZeradaDeProdutoAoCarrinho() {
        cliente.setLimiteCredito(10);
        produto.setValorVenda(20);
        produto.setEstoque(1);

        VendaException exception = assertThrows(VendaException.class, () -> {
            venda.adicionarProdutoAoCarrinho(produto, 0);
        });

        String retorno = exception.getMessage();

        Assert.isTrue(retorno.equals("Não é permitido adicionar quantidade zerada ao carrinho"),"Mensagem esperada: Não é permitido adicionar quantidade zerada ao carrinho: "+retorno);

    }

    @Test
    public void obrigarPreencherProdutoAocarrinhoDeCompras() {
        cliente.setLimiteCredito(10);

        VendaException exception = assertThrows(VendaException.class, () -> {
            venda.adicionarProdutoAoCarrinho(null, 1);
        });

        String retorno = exception.getMessage();

        Assert.isTrue(retorno.equals("É obrigatório preencher um produto para adicioar ao carrinho"),"Mensagem esperada: É obrigatório preencher um produto para adicioar ao carrinho: "+retorno);

    }
}
