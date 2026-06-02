package com.ecommerce.checkout.observer;

import com.ecommerce.checkout.model.Item;
import com.ecommerce.checkout.model.Pedido;

/**
 * Observador Concreto: LogisticaService.
 * 
 * Responsável por preparar o pedido para envio (expedição, empacotamento, transportadora)
 * assim que o pagamento é aprovado.
 */
public class LogisticaService implements ObservadorPedido {

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("\n[Observer: LogisticaService] 🚚 Processando logística de envio...");
        System.out.println("[Observer: LogisticaService] Preparando a separação física dos itens:");
        for (Item item : pedido.getItens()) {
            System.out.println("  -> Separando " + item.getQuantidade() + "x de '" + item.getNome() + "'");
        }
        System.out.println("[Observer: LogisticaService] Solicitando coleta da transportadora parceira.");
        System.out.println("[Observer: LogisticaService] Status: Separação concluída e aguardando postagem.");
    }
}
