package com.ecommerce.checkout.chain;

import com.ecommerce.checkout.model.Item;
import com.ecommerce.checkout.model.Pedido;

/**
 * Elo 1 da Cadeia de Validação: Validador de Estoque.
 * 
 * Simula a verificação de disponibilidade dos produtos no estoque físico.
 * Se algum item não estiver disponível, a cadeia é interrompida lançando uma exceção.
 */
public class ValidadorEstoque extends ValidadorCheckout {

    @Override
    public void validar(Pedido pedido) {
        System.out.println("[Chain: ValidadorEstoque] Iniciando verificação de estoque para o pedido " + pedido.getId() + "...");

        for (Item item : pedido.getItens()) {
            // Regra simulada: se o produto contiver "Sem Estoque" no nome, a compra é recusada.
            if (item.getNome().toLowerCase().contains("sem estoque")) {
                System.out.println("[Chain: ValidadorEstoque] FAIHA: Item '" + item.getNome() + "' está indisponível!");
                throw new IllegalStateException("Falha no checkout: O produto '" + item.getNome() + "' não possui estoque suficiente.");
            }
            
            // Regra simulada de quantidade máxima permitida
            if (item.getQuantidade() > 50) {
                System.out.println("[Chain: ValidadorEstoque] FALHA: Quantidade do item '" + item.getNome() + "' excede o limite permitido por compra!");
                throw new IllegalArgumentException("Falha no checkout: Limite de quantidade excedido para '" + item.getNome() + "' (Máx: 50).");
            }
        }

        System.out.println("[Chain: ValidadorEstoque] Sucesso: Todos os itens estão disponíveis em estoque.");
        
        // Passa para o próximo elo
        proximo(pedido);
    }
}
