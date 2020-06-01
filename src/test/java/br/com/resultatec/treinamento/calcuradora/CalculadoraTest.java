package br.com.resultatec.treinamento.calcuradora;

import br.com.resultatec.treinamento.calculadora.Calculadora;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.util.Assert.isTrue;

@SpringBootTest
public class CalculadoraTest  {

    public Calculadora calculadora = new Calculadora();

    @Test
    public void soma() {
        int primeiroNumero = 2;
        int segundoNumero = 2;

        int resultado = calculadora.soma(primeiroNumero, segundoNumero);

        isTrue(resultado == 4, "Não foi possível obter o resultado esperado pela soma de dois valores.");
    }

    @Test
    public void divisao() {
        int primeiroNumeroDiviacao = 4;
        int segundoNumeroDivisao = 2;

        int resultado = calculadora.divisao(primeiroNumeroDiviacao, segundoNumeroDivisao);

        isTrue(resultado == 2, "Não foi possível obeter o resultado esperado pela divisão de dois valores.");
    }

    @Test
    public void divisaoPorZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.divisao(1,0);
        });
    }
}
