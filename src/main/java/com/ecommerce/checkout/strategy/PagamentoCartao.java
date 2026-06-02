package com.ecommerce.checkout.strategy;

/**
 * Implementação Concreta da Estratégia de Pagamento: Cartão de Crédito.
 * 
 * Simula a integração com um gateway de pagamento (ex: Stripe, Adyen),
 * validando as credenciais do cartão de forma fictícia.
 */
public class PagamentoCartao implements EstrategiaPagamento {
    private final String numeroCartao;
    private final String titular;
    private final String cvv;

    // Construtor padrão simulado
    public PagamentoCartao() {
        this.numeroCartao = "**** **** **** 1234";
        this.titular = "CLIENTE EXEMPLO GITHUB";
        this.cvv = "***";
    }

    public PagamentoCartao(String numeroCartao, String titular, String cvv) {
        this.numeroCartao = numeroCartao;
        this.titular = titular;
        this.cvv = cvv;
    }

    @Override
    public boolean processar(double valor) {
        System.out.println("[Strategy: PagamentoCartao] Conectando ao Gateway de Pagamento...");
        System.out.println("[Strategy: PagamentoCartao] Enviando dados criptografados do cartão:");
        System.out.println("  💳 Titular: " + titular);
        System.out.println("  💳 Cartão: " + ocultarCartao(numeroCartao));
        
        System.out.format("[Strategy: PagamentoCartao] Solicitando autorização de R$ %.2f junto à operadora...\n", valor);
        
        // Simulação de regras simples para tornar interessante
        if (valor > 10000.0) {
            System.out.println("[Strategy: PagamentoCartao] FALHA: Transação negada pela operadora. Motivo: Limite excedido ou suspeita de fraude.");
            return false;
        }

        System.out.println("[Strategy: PagamentoCartao] Sucesso! Autorização de transação nº 857392 aprovada.");
        return true;
    }

    private String ocultarCartao(String cartao) {
        if (cartao.length() > 4) {
            return "**** **** **** " + cartao.substring(cartao.length() - 4);
        }
        return "****";
    }
}
