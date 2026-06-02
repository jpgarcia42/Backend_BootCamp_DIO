package com.ecommerce.checkout.chain;

import com.ecommerce.checkout.model.Pedido;

/**
 * Elo 2 da Cadeia de Validação: Aplicador de Cupom de Desconto.
 * 
 * Verifica se há um cupom associado ao pedido e, se for um cupom conhecido/válido,
 * aplica o respectivo desconto sobre o valor final do pedido.
 */
public class AplicadorCupom extends ValidadorCheckout {

    @Override
    public void validar(Pedido pedido) {
        System.out.println("[Chain: AplicadorCupom] Verificando cupons de desconto...");

        String cupom = pedido.getCupom();
        if (cupom != null && !cupom.trim().isEmpty()) {
            double valorOriginal = pedido.getValorFinal();
            double desconto = 0.0;

            // Simulação de regras de cupons
            if ("QUERO10".equalsIgnoreCase(cupom)) {
                desconto = valorOriginal * 0.10; // 10% de desconto
                System.out.println("[Chain: AplicadorCupom] Cupom 'QUERO10' aplicado! 10% de desconto concedido.");
            } else if ("DESCONTO20".equalsIgnoreCase(cupom)) {
                desconto = valorOriginal * 0.20; // 20% de desconto
                System.out.println("[Chain: AplicadorCupom] Cupom 'DESCONTO20' aplicado! 20% de desconto concedido.");
            } else {
                System.out.println("[Chain: AplicadorCupom] Cupom '" + cupom + "' é inválido ou expirou. Nenhum desconto aplicado.");
            }

            if (desconto > 0) {
                pedido.setValorFinal(valorOriginal - desconto);
                System.out.format("[Chain: AplicadorCupom] Valor atualizado de R$ %.2f para R$ %.2f\n", valorOriginal, pedido.getValorFinal());
            }
        } else {
            System.out.println("[Chain: AplicadorCupom] Nenhum cupom foi informado para este pedido.");
        }

        // Passa para o próximo elo
        proximo(pedido);
    }
}
