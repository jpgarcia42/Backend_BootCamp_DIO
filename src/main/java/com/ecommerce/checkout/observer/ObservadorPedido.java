package com.ecommerce.checkout.observer;

import com.ecommerce.checkout.model.Pedido;

/**
 * Padrão GoF: Observer (Observador)
 * 
 * POR QUE USAR AQUI?
 * Após a aprovação do pagamento de um pedido, diversas ações secundárias precisam acontecer de forma automática:
 * emitir nota fiscal, separar estoque para expedição, enviar e-mail de confirmação ao cliente, etc.
 * Se colocarmos toda essa lógica dentro do fluxo de checkout do GerenciadorDePedido, teremos um acoplamento altíssimo 
 * e feriremos o Princípio de Responsabilidade Única (SRP).
 * O Observer permite que o GerenciadorDePedido funcione apenas como o "Sujeito" (Subject) que notifica uma lista de 
 * "Observadores" (Listeners/Observers) genéricos que se registraram para receber novidades sobre a aprovação.
 * Dessa forma, adicionar ou remover integrações pós-venda (ex: um novo serviço de integração com transportadora) 
 * é trivial e não exige modificação na classe de checkout.
 */
public interface ObservadorPedido {
    /**
     * Método acionado pelo Sujeito quando o estado do pedido muda (neste caso, quando o pagamento é aprovado).
     * 
     * @param pedido O pedido que teve o pagamento confirmado.
     */
    void atualizar(Pedido pedido);
}
