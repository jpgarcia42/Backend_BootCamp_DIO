package com.ecommerce.checkout.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o pedido (ou carrinho de compras consolidado).
 * 
 * Contém o estado do pedido que será modificado ao longo do checkout pela Cadeia de Validação/Desconto
 * e processado pela Estratégia de Pagamento escolhida.
 */
public class Pedido {
    private final String id;
    private final List<Item> itens;
    private double valorOriginal;
    private double valorFinal;
    private String cupom;
    private final MetodoPagamento metodoPagamento;
    private boolean pago;

    public Pedido(String id, MetodoPagamento metodoPagamento) {
        this.id = id;
        this.itens = new ArrayList<>();
        this.metodoPagamento = metodoPagamento;
        this.valorOriginal = 0.0;
        this.valorFinal = 0.0;
        this.pago = false;
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
        recalcularValores();
    }

    private void recalcularValores() {
        this.valorOriginal = itens.stream().mapToDouble(Item::getSubtotal).sum();
        // Inicialmente o valor final é igual ao original, até passar pela cadeia de descontos
        this.valorFinal = this.valorOriginal;
    }

    public String getId() {
        return id;
    }

    public List<Item> getItens() {
        return new ArrayList<>(itens);
    }

    public double getValorOriginal() {
        return valorOriginal;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        // Garante que o valor final nunca fique abaixo de zero
        this.valorFinal = Math.max(0.0, valorFinal);
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return String.format(
            "Pedido ID: %s | Itens: %d | Total Original: R$ %.2f | Total Final: R$ %.2f | Cupom: %s | Pagamento: %s | Pago: %s",
            id, itens.size(), valorOriginal, valorFinal, (cupom != null ? cupom : "Nenhum"), metodoPagamento, (pago ? "Sim" : "Não")
        );
    }
}
