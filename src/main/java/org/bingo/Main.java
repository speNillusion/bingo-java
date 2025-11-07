package org.bingo;

import org.bingo.service.Cartela;
import org.bingo.service.Sorteador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade de cartelas para o jogo: ");
        int qtdCartelas = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha pendente

        // Gerar as cartelas
        List<Cartela> cartelas = new ArrayList<>();
        for (int i = 1; i <= qtdCartelas; i++) {
            cartelas.add(new Cartela(i));
        }

        System.out.println("\n--- Cartelas Geradas ---");
        for (Cartela cartela : cartelas) {
            System.out.println(cartela);
        }

        // Iniciar o sorteio
        Sorteador sorteador = new Sorteador();
        List<Cartela> cartelasVencedoras = new ArrayList<>();

        System.out.println("\n>>> Pressione ENTER para sortear o próximo número <<<");

        while (cartelasVencedoras.isEmpty()) {
            scanner.nextLine(); // Aguarda o usuário pressionar Enter

            String[] pedraSorteada = sorteador.sortearProximo();
            if (pedraSorteada == null) {
                System.out.println("Todos os números foram sorteados, mas não houve vencedor.");
                break;
            }

            char letra = pedraSorteada[0].charAt(0);
            int numero = Integer.parseInt(pedraSorteada[1]);
            int coluna = Sorteador.getColunaDaLetra(letra);

            System.out.println("\n*********************************");
            System.out.printf("*   Número sorteado: %c-%02d   *\n", letra, numero);
            System.out.println("*********************************\n");

            // Marcar número nas cartelas e verificar vencedores
            for (Cartela cartela : cartelas) {
                cartela.marcarNumero(coluna, numero);
                if (cartela.ehVencedora()) {
                    // Adiciona apenas se ainda não estiver na lista de vencedoras
                    if (!cartelasVencedoras.contains(cartela)) {
                        cartelasVencedoras.add(cartela);
                    }
                }
            }
        }

        // Mostrar resultado
        if (!cartelasVencedoras.isEmpty()) {
            System.out.println("\n================ BINGO! ================");
            System.out.println("Tivemos " + cartelasVencedoras.size() + " cartela(s) vencedora(s)!");
            for (Cartela vencedora : cartelasVencedoras) {
                System.out.println("\n--- Cartela Vencedora ---");
                System.out.println(vencedora);
            }
            System.out.println("\nTotal de pedras sorteadas: " + sorteador.getPedrasSorteadas().size());
            System.out.println("Pedras sorteadas: " + sorteador.getPedrasSorteadas());
            System.out.println("========================================");
        }

        scanner.close();
    }
}