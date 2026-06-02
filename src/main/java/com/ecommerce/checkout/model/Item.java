package com.ecommerce.checkout.model;

/**
 * Representa um item contido no carrinho de compras.
 * 
 * Contém informações básicas do produto selecionado pelo cliente.
 */
public class Item {
    private final String nome;
    private final int quantidade;
    private final double precoUnitario;

    public Item(String nome, int quantidade, double precoUnitario) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getSubtotal() {
        return precoUnitario * quantidade;
    }

    @Override
    public String toString() {
        return String.format("%s (x%d) - R$ %.2f cada", nome, quantidade, precoUnitario);
    }
}
