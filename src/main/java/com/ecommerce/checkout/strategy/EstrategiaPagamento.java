package com.ecommerce.checkout.strategy;

/**
 * Padrão GoF: Strategy (Estratégia)
 * 
 * POR QUE USAR AQUI?
 * O e-commerce suporta várias formas de pagamento (Pix, Cartão de Crédito, Boleto). Cada forma de 
 * pagamento possui regras de negócio, APIs de integração, fluxos e tempos de resposta totalmente diferentes. 
 * Ao invés de usar condicionais acopladas na nossa classe principal (como um switch-case gigante), usamos o Strategy.
 * Nós criamos uma abstração (esta interface) e deixamos que cada método de pagamento implemente 
 * a sua própria lógica de forma independente. O cliente (GerenciadorDePedido) apenas interage com a interface 
 * EstrategiaPagamento, tornando o código facilmente extensível a novos métodos de pagamento (Open/Closed Principle do SOLID).
 */
public interface EstrategiaPagamento {
    /**
     * Processa o pagamento de um determinado valor.
     * 
     * @param valor O valor monetário a ser cobrado.
     * @return true se o pagamento foi autorizado/processado com sucesso, false caso contrário.
     */
    boolean processar(double valor);
}
