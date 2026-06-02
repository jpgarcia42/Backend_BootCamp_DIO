import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Leitura dos valores de entrada
            String saldoInput = scanner.nextLine();
            String saqueInput = scanner.nextLine();

            int saldo = Integer.parseInt(saldoInput.trim());
            int valorSaque = Integer.parseInt(saqueInput.trim());

            // Correção do TODO: Verifica se o valor do saque é inválido (menor ou igual a zero)
            if (valorSaque <= 0) {
                System.out.println("Valor invalido");
                return; // Interrompe a execução para não prosseguir para o cálculo do saldo
            }

            // Verifica se há saldo suficiente para o saque
            if (valorSaque > saldo) {
                System.out.println("Saldo insuficiente");
                return;
            }

            // Saque realizado com sucesso
            System.out.println(saldo - valorSaque);

        } catch (NumberFormatException e) {
            // Captura erros caso o usuário digite letras (como "abc") em vez de números inteiros
            System.out.println("Entrada invalida");
        } finally {
            scanner.close(); // Boa prática para fechar o recurso do Scanner
        }
    }
}