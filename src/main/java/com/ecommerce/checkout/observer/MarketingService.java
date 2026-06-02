package com.ecommerce.checkout.observer;

import com.ecommerce.checkout.model.Pedido;

/**
 * Observador Concreto: MarketingService.
 * 
 * Responsável pelas comunicações pós-venda, como envio de e-mails de agradecimento
 * e cupons de incentivo para compras futuras.
 */
public class MarketingService implements ObservadorPedido {

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("\n[Observer: MarketingService] ✉️ Preparando disparo de e-mails de marketing...");
        System.out.println("[Observer: MarketingService] Enviando e-mail de agradecimento pela compra do pedido " + pedido.getId());
        System.out.println("[Observer: MarketingService] Enviando cupom de 5% de desconto para a próxima compra: 'OBRIGADO5'");
        System.out.println("[Observer: MarketingService] Status: E-mail enviado com sucesso.");
    }
}
