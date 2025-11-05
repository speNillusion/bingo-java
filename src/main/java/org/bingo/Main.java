package org.bingo;

import org.bingo.service.Cartela;
import org.bingo.service.Sorteador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Determinar a quantidade de números por cartela
        int qtdNumerosPorCartela = 0;
        while (qtdNumerosPorCartela <= 0 || qtdNumerosPorCartela % 5 != 0) {
            System.out.print("Digite a quantidade de números por cartela (deve ser um múltiplo de 5): ");
            qtdNumerosPorCartela = scanner.nextInt();
            if (qtdNumerosPorCartela <= 0 || qtdNumerosPorCartela % 5 != 0) {
                System.out.println("Valor inválido. Por favor, insira um múltiplo de 5 positivo.");
            }
        }

        // 2. Escolher a quantidade de cartelas
        System.out.print("Digite a quantidade de cartelas para o jogo: ");
        int qtdCartelas = scanner.nextInt();

        // 3. Gerar as cartelas
        List<Cartela> cartelas = new ArrayList<>();
        for (int i = 1; i <= qtdCartelas; i++) {
            cartelas.add(new Cartela(i, qtdNumerosPorCartela));
        }

        System.out.println("\n--- Cartelas Geradas ---");
        for (Cartela cartela : cartelas) {
            System.out.println(cartela);
        }
        System.out.println("------------------------\n");

        // 4. Iniciar o sorteio
        Sorteador sorteador = new Sorteador();
        List<Cartela> cartelasVencedoras = new ArrayList<>();

        System.out.println(">>> Iniciando o sorteio! Boa sorte! <<<\n");

        while (cartelasVencedoras.isEmpty()) {
            int numeroSorteado = sorteador.sortearProximo();
            if (numeroSorteado == -1) {
                System.out.println("Todos os números foram sorteados, mas não houve vencedor.");
                break;
            }

            System.out.println("Número sorteado: " + numeroSorteado);

            // 5. Marcar número nas cartelas e verificar vencedores
            for (Cartela cartela : cartelas) {
                cartela.marcarNumero(numeroSorteado);
                if (cartela.ehVencedora()) {
                    cartelasVencedoras.add(cartela);
                }
            }

            // Pausa para o usuário poder acompanhar
            try {
                Thread.sleep(200); // Pausa de 200 milissegundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (!cartelasVencedoras.isEmpty()) {
            System.out.println("\n================ BINGO! ================");
            System.out.println("Tivemos " + cartelasVencedoras.size() + " cartela(s) vencedora(s)!");
            for (Cartela vencedora : cartelasVencedoras) {
                System.out.println("Vencedora -> " + vencedora);
            }
            System.out.println("\nTotal de números sorteados até o bingo: " + sorteador.getNumerosJaSorteados().size());
            System.out.println("Números sorteados: " + sorteador.getNumerosJaSorteados());
            System.out.println("========================================");
        }

        scanner.close();
    }
}