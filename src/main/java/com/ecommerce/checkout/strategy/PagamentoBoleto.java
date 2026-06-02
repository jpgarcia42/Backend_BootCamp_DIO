package com.ecommerce.checkout.strategy;

/**
 * Implementação Concreta da Estratégia de Pagamento: Boleto Bancário.
 * 
 * Simula a emissão de boleto bancário com registro e linha digitável.
 */
public class PagamentoBoleto implements EstrategiaPagamento {

    @Override
    public boolean processar(double valor) {
        System.out.println("[Strategy: PagamentoBoleto] Iniciando geração do boleto bancário...");
        System.out.format("[Strategy: PagamentoBoleto] Valor do boleto: R$ %.2f\n", valor);
        
        // Simulação da linha digitável
        String linhaDigitavel = "34191.79001 01043.513184 91020.150008 7 934500000" + String.format("%06d", (int)(valor * 100));
        System.out.println("[Strategy: PagamentoBoleto] Registro efetuado no banco emissor com sucesso!");
        System.out.println("[Strategy: PagamentoBoleto] Linha Digitável gerada:");
        System.out.println("  📄 " + linhaDigitavel);
        System.out.println("[Strategy: PagamentoBoleto] Info: Boleto emitido com vencimento para D+3.");
        
        // No caso real de boleto, o pagamento é assíncrono. Mas para a simulação de checkout da API,
        // consideramos a emissão bem-sucedida do documento de cobrança como sucesso do fluxo inicial.
        return true;
    }
}
