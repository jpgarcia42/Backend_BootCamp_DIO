package com.ecommerce.checkout.chain;

import com.ecommerce.checkout.model.MetodoPagamento;
import com.ecommerce.checkout.model.Pedido;

/**
 * Elo 3 da Cadeia de Validação: Aplicador de Desconto por Método de Pagamento.
 * 
 * Se o método de pagamento selecionado for PIX, aplica um desconto especial de 10%
 * acumulado com descontos de cupons anteriores.
 */
public class AplicadorDescontoPagamento extends ValidadorCheckout {

    @Override
    public void validar(Pedido pedido) {
        System.out.println("[Chain: AplicadorDescontoPagamento] Verificando descontos por método de pagamento...");

        if (pedido.getMetodoPagamento() == MetodoPagamento.PIX) {
            double valorAtual = pedido.getValorFinal();
            double descontoPix = valorAtual * 0.10; // 10% de desconto adicional para Pix
            
            pedido.setValorFinal(valorAtual - descontoPix);
            
            System.out.println("[Chain: AplicadorDescontoPagamento] Método de pagamento é PIX! Desconto de 10% aplicado.");
            System.out.format("[Chain: AplicadorDescontoPagamento] Valor atualizado de R$ %.2f para R$ %.2f\n", valorAtual, pedido.getValorFinal());
        } else {
            System.out.println("[Chain: AplicadorDescontoPagamento] Método de pagamento '" + pedido.getMetodoPagamento() + "' não elegível para descontos adicionais.");
        }

        // Passa para o próximo elo
        proximo(pedido);
    }
}
