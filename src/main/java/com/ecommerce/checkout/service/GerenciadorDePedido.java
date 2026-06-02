package com.ecommerce.checkout.service;

import com.ecommerce.checkout.chain.AplicadorCupom;
import com.ecommerce.checkout.chain.AplicadorDescontoPagamento;
import com.ecommerce.checkout.chain.ValidadorCheckout;
import com.ecommerce.checkout.chain.ValidadorEstoque;
import com.ecommerce.checkout.model.MetodoPagamento;
import com.ecommerce.checkout.model.Pedido;
import com.ecommerce.checkout.observer.ObservadorPedido;
import com.ecommerce.checkout.strategy.EstrategiaPagamento;
import com.ecommerce.checkout.strategy.PagamentoBoleto;
import com.ecommerce.checkout.strategy.PagamentoCartao;
import com.ecommerce.checkout.strategy.PagamentoPix;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordenador central do fluxo de checkout do E-commerce.
 * 
 * Atua como o "Sujeito" (Subject) no padrão Observer, mantendo uma lista de observadores 
 * interessados no estado do pedido.
 * Além disso, executa e gerencia a cadeia de validação (Chain of Responsibility) 
 * e delega o processamento de pagamento para a estratégia correspondente (Strategy).
 */
public class GerenciadorDePedido {
    private final List<ObservadorPedido> observadores = new ArrayList<>();
    private final ValidadorCheckout cadeiaValidacao;

    public GerenciadorDePedido() {
        // Inicializa e monta a Cadeia de Responsabilidade:
        // ValidadorEstoque -> AplicadorCupom -> AplicadorDescontoPagamento
        ValidadorCheckout eloEstoque = new ValidadorEstoque();
        ValidadorCheckout eloCupom = new AplicadorCupom();
        ValidadorCheckout eloDescontoPagamento = new AplicadorDescontoPagamento();

        eloEstoque.definirProximo(eloCupom).definirProximo(eloDescontoPagamento);
        this.cadeiaValidacao = eloEstoque; // Head da cadeia
    }

    // ==========================================
    // MÉTODOS DO PADRÃO OBSERVER (SUBJECT)
    // ==========================================
    
    public void inscrever(ObservadorPedido observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
            System.out.println("[Subject: GerenciadorDePedido] Novo observador inscrito: " + observador.getClass().getSimpleName());
        }
    }

    public void desinscrever(ObservadorPedido observador) {
        observadores.remove(observador);
        System.out.println("[Subject: GerenciadorDePedido] Observador removido: " + observador.getClass().getSimpleName());
    }

    private void notificarObservadores(Pedido pedido) {
        System.out.println("\n[Subject: GerenciadorDePedido] Notificando observadores sobre o pagamento aprovado do pedido " + pedido.getId() + "...");
        for (ObservadorPedido observador : observadores) {
            observador.atualizar(pedido);
        }
    }

    // ==========================================
    // FLUXO PRINCIPAL DE CHECKOUT
    // ==========================================

    public void realizarCheckout(Pedido pedido) {
        System.out.println("\n=======================================================");
        System.out.println("🚀 INICIANDO CHECKOUT DO PEDIDO: " + pedido.getId());
        System.out.println("=======================================================");
        System.out.println("Resumo Inicial: " + pedido);

        try {
            // Passo 1: Executar a cadeia de responsabilidade (Validação e Descontos)
            System.out.println("\n--- [Passo 1] Cadeia de Validação e Descontos ---");
            cadeiaValidacao.validar(pedido);

            // Passo 2: Obter a estratégia correta de pagamento baseada no pedido e processar
            System.out.println("\n--- [Passo 2] Processamento de Pagamento ---");
            EstrategiaPagamento estrategia = obterEstrategiaPagamento(pedido.getMetodoPagamento());
            boolean pagamentoAprovado = estrategia.processar(pedido.getValorFinal());

            if (pagamentoAprovado) {
                pedido.setPago(true);
                System.out.println("✔ Pagamento confirmado com sucesso!");

                // Passo 3: Notificar observadores registrados (ações pós-venda)
                System.out.println("\n--- [Passo 3] Disparando Eventos Pós-Venda (Observer) ---");
                notificarObservadores(pedido);
                
                System.out.println("\n=======================================================");
                System.out.println("🎉 CHECKOUT CONCLUÍDO COM SUCESSO PARA O PEDIDO " + pedido.getId());
                System.out.println("=======================================================");
            } else {
                System.out.println("❌ Falha no checkout: O pagamento foi recusado.");
                System.out.println("=======================================================");
            }

        } catch (Exception e) {
            System.err.println("\n❌ ERRO DURANTE O FLUXO DE CHECKOUT: " + e.getMessage());
            System.out.println("=======================================================");
        }
    }

    /**
     * Factory Method Simples para selecionar a estratégia com base no MetodoPagamento.
     * Auxilia no desacoplamento entre o enum e as classes de estratégia de pagamento.
     */
    private EstrategiaPagamento obterEstrategiaPagamento(MetodoPagamento metodo) {
        return switch (metodo) {
            case PIX -> new PagamentoPix();
            case CARTAO_CREDITO -> new PagamentoCartao();
            case BOLETO -> new PagamentoBoleto();
            default -> throw new IllegalArgumentException("Método de pagamento não suportado: " + metodo);
        };
    }
}
