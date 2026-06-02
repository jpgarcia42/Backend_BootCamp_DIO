package com.ecommerce.checkout;

import com.ecommerce.checkout.model.Item;
import com.ecommerce.checkout.model.MetodoPagamento;
import com.ecommerce.checkout.model.Pedido;
import com.ecommerce.checkout.observer.FaturamentoService;
import com.ecommerce.checkout.observer.LogisticaService;
import com.ecommerce.checkout.observer.MarketingService;
import com.ecommerce.checkout.service.GerenciadorDePedido;

/**
 * Classe principal para executar e simular os cenários do Checkout de E-commerce.
 * 
 * Demonstra a integração de todas as partes do sistema, exercitando os padrões:
 * - Chain of Responsibility (Validação e Descontos)
 * - Strategy (Formas de Pagamento)
 * - Observer (Ações Pós-Pagamento)
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("   🛍️  SISTEMA DE CHECKOUT DE E-COMMERCE - PADRÕES DE PROJETO GOF  🛍️");
        System.out.println("======================================================================");

        // 1. Inicializa o serviço coordenador (GerenciadorDePedido)
        GerenciadorDePedido gerenciador = new GerenciadorDePedido();

        // 2. Cria e registra os observadores de eventos pós-venda
        System.out.println("\n[Main] Inicializando e registrando serviços pós-venda (Observadores)...");
        LogisticaService logistica = new LogisticaService();
        MarketingService marketing = new MarketingService();
        FaturamentoService faturamento = new FaturamentoService();

        gerenciador.inscrever(logistica);
        gerenciador.inscrever(marketing);
        gerenciador.inscrever(faturamento);

        esperar(1000);

        // ====================================================================
        // CENÁRIO 1: Compra Bem-Sucedida via PIX com Cupom Válido (Acúmulo de Descontos)
        // ====================================================================
        System.out.println("\n\n>>> CENÁRIO 1: PIX + CUPOM VÁLIDO (QUERO10) <<<");
        Pedido pedido1 = new Pedido("PED-2026-001", MetodoPagamento.PIX);
        pedido1.adicionarItem(new Item("Notebook Dell Inspiron", 1, 4500.00));
        pedido1.adicionarItem(new Item("Mouse Sem Fio Logitech", 1, 150.00));
        pedido1.setCupom("QUERO10"); // Cupom de 10% de desconto

        gerenciador.realizarCheckout(pedido1);
        esperar(2000);

        // ====================================================================
        // CENÁRIO 2: Compra Bem-Sucedida via Cartão com Cupom Diferente
        // ====================================================================
        System.out.println("\n\n>>> CENÁRIO 2: CARTÃO DE CRÉDITO + CUPOM (DESCONTO20) <<<");
        Pedido pedido2 = new Pedido("PED-2026-002", MetodoPagamento.CARTAO_CREDITO);
        pedido2.adicionarItem(new Item("Smartphone Samsung Galaxy S24", 1, 5200.00));
        pedido2.adicionarItem(new Item("Fone Bluetooth JBL", 2, 350.00));
        pedido2.setCupom("DESCONTO20"); // Cupom de 20% de desconto

        gerenciador.realizarCheckout(pedido2);
        esperar(2000);

        // ====================================================================
        // CENÁRIO 3: Falha no Checkout devido à Falha de Estoque (Chain Interrompe)
        // ====================================================================
        System.out.println("\n\n>>> CENÁRIO 3: FALHA DE ESTOQUE (Cadeia interrompida) <<<");
        Pedido pedido3 = new Pedido("PED-2026-003", MetodoPagamento.BOLETO);
        pedido3.adicionarItem(new Item("Monitor Gamer Ultrawide [Sem Estoque]", 1, 1800.00));
        pedido3.adicionarItem(new Item("Teclado Mecânico Redragon", 1, 280.00));

        gerenciador.realizarCheckout(pedido3);
        esperar(2000);

        // ====================================================================
        // CENÁRIO 4: Falha no Pagamento via Cartão (Recusado pelo Gateway/Valor excessivo)
        // ====================================================================
        System.out.println("\n\n>>> CENÁRIO 4: PAGAMENTO RECUSADO (Estratégia de cartão negada) <<<");
        Pedido pedido4 = new Pedido("PED-2026-004", MetodoPagamento.CARTAO_CREDITO);
        pedido4.adicionarItem(new Item("Servidor Dedicado Enterprise", 1, 12000.00)); // Acima de 10k simula rejeição no cartão

        gerenciador.realizarCheckout(pedido4);

        System.out.println("\n======================================================================");
        System.out.println("       🏁  FIM DA SIMULAÇÃO DO CHECKOUT DE E-COMMERCE  🏁");
        System.out.println("======================================================================");
    }

    private static void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
