package com.ecommerce.checkout.observer;

import com.ecommerce.checkout.model.Pedido;

/**
 * Observador Concreto: FaturamentoService.
 * 
 * Responsável por emitir a Nota Fiscal Eletrônica (NF-e) assim que o pagamento do pedido é confirmado.
 */
public class FaturamentoService implements ObservadorPedido {

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("\n[Observer: FaturamentoService] 🧾 Iniciando processo de faturamento...");
        System.out.println("[Observer: FaturamentoService] Emitindo Nota Fiscal Eletrônica (NF-e) para o Pedido: " + pedido.getId());
        System.out.format("[Observer: FaturamentoService] NF-e gerada no valor total de R$ %.2f\n", pedido.getValorFinal());
        System.out.println("[Observer: FaturamentoService] XML da NF-e transmitido à SEFAZ municipal com sucesso.");
        System.out.println("[Observer: FaturamentoService] Status: Nota fiscal emitida.");
    }
}
