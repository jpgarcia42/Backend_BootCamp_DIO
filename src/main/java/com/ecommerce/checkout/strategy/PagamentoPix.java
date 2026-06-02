package com.ecommerce.checkout.strategy;

import java.util.UUID;

/**
 * Implementação Concreta da Estratégia de Pagamento: PIX.
 * 
 * Simula o fluxo de geração de chave dinâmica e simulação de pagamento instantâneo.
 */
public class PagamentoPix implements EstrategiaPagamento {

    @Override
    public boolean processar(double valor) {
        System.out.println("[Strategy: PagamentoPix] Iniciando processamento de pagamento via PIX...");
        System.out.format("[Strategy: PagamentoPix] Valor a ser pago: R$ %.2f\n", valor);
        
        // Simulação de geração de chave de pagamento
        String copiaECola = "00020126360014BR.GOV.BCB.PIX0114+55119999999995204000053039865405" + String.format("%.2f", valor) + "5802BR5925Ecommerce Checkout6009Sao Paulo62070503***6304";
        System.out.println("[Strategy: PagamentoPix] Copia e Cola gerado com sucesso:");
        System.out.println("  👉 " + copiaECola);
        System.out.println("[Strategy: PagamentoPix] Aguardando confirmação do Banco Central...");
        
        // Simulação de transação bem-sucedida
        String transactionId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        System.out.println("[Strategy: PagamentoPix] Sucesso! Pagamento PIX confirmado instantaneamente. ID Transação: TX-" + transactionId);
        
        return true;
    }
}
