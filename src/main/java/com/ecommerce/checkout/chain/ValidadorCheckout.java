package com.ecommerce.checkout.chain;

import com.ecommerce.checkout.model.Pedido;

/**
 * Padrão GoF: Chain of Responsibility (Cadeia de Responsabilidade)
 * 
 * POR QUE USAR AQUI?
 * No fluxo de checkout, precisamos executar várias validações e regras de negócio antes de processar 
 * o pagamento (ex: validar estoque, aplicar cupons, aplicar descontos de pagamento). 
 * Ao invés de criar um único método centralizado gigante com múltiplos "if-else", usamos o Chain of Responsibility.
 * Isso nos permite desacoplar o remetente do receptor, onde cada classe (elo) tem uma única responsabilidade 
 * (Clean Code / Single Responsibility) e pode escolher passar a requisição adiante ou interromper o fluxo 
 * se alguma regra falhar. É fácil adicionar novas etapas ou reordenar a cadeia sem afetar o restante do código.
 */
public abstract class ValidadorCheckout {
    protected ValidadorCheckout proximo;

    /**
     * Define o próximo elo na cadeia de validações.
     */
    public ValidadorCheckout definirProximo(ValidadorCheckout proximo) {
        this.proximo = proximo;
        return proximo; // Retorna o próximo elo para permitir encadeamento fluido (Fluent API)
    }

    /**
     * Método principal que executa a validação do elo atual e propaga para o próximo elo, se houver.
     */
    public abstract void validar(Pedido pedido);

    /**
     * Auxiliar para propagar a requisição para o próximo elo da cadeia.
     */
    protected void proximo(Pedido pedido) {
        if (proximo != null) {
            proximo.validar(pedido);
        }
    }
}
