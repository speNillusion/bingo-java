package org.bingo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Representa uma cartela de Bingo 5x5 com colunas B-I-N-G-O.
 * O espaço central (N) é livre e representado por 0.
 */
public class Cartela {
    private final int id;
    private final Integer[][] numeros; // Matriz 5x5 para a cartela
    private final boolean[][] marcados; // Matriz 5x5 para controlar a marcação

    public Cartela(int id) {
        this.id = id;
        this.numeros = new Integer[5][5];
        this.marcados = new boolean[5][5];
        gerarNumeros();
    }

    /**
     * Gera os números da cartela, respeitando as faixas de cada coluna B-I-N-G-O.
     */
    private void gerarNumeros() {
        Random rand = new Random();
        // Colunas: 0=B, 1=I, 2=N, 3=G, 4=O
        for (int coluna = 0; coluna < 5; coluna++) {
            List<Integer> numerosDaColuna = new ArrayList<>();
            int inicioFaixa = (coluna * 15) + 1;
            int fimFaixa = inicioFaixa + 14;

            // Gera 5 números únicos para a coluna dentro da faixa correta
            while (numerosDaColuna.size() < 5) {
                int numero = rand.nextInt(fimFaixa - inicioFaixa + 1) + inicioFaixa;
                if (!numerosDaColuna.contains(numero)) {
                    numerosDaColuna.add(numero);
                }
            }
            Collections.sort(numerosDaColuna); // Opcional: para visualização ordenada

            for (int linha = 0; linha < 5; linha++) {
                this.numeros[linha][coluna] = numerosDaColuna.get(linha);
            }
        }

        // Define o espaço central como livre (marcado desde o início)
        this.numeros[2][2] = 0; // 0 representa o espaço livre
        this.marcados[2][2] = true;
    }

    /**
     * Marca um número na cartela se ele estiver na coluna correta.
     * @param coluna A coluna do sorteio (0-4)
     * @param numeroSorteado O número sorteado
     */
    public void marcarNumero(int coluna, int numeroSorteado) {
        for (int linha = 0; linha < 5; linha++) {
            if (numeros[linha][coluna].equals(numeroSorteado)) {
                marcados[linha][coluna] = true;
                return; // Encontrou e marcou, pode sair
            }
        }
    }
     /*
     * Verifica se a cartela é vencedora (qualquer linha, coluna ou diagonal completa).
     */

     // Assume que a linha está completa
    public boolean ehVencedora() {
        // 1. Verifica cada linha
        boolean linhaCompleta = true;
        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {
                if (!marcados[i][j]) {
                    linhaCompleta = false; // Encontrou um número não marcado, então não está completa
                    break; // Otimização: não precisa checar o resto da linha
                }
            }
        }
        boolean colunaCompleta = true;
        // 2. Verifica cada coluna
        for (int i = 0; i < 5; i++) {
            // Assume que a coluna está completa
            for (int j = 0; j < 5; j++) {
                if (!marcados[j][i]) {
                    colunaCompleta = false; // Encontrou um número não marcado, então não está completa
                    break; // Otimização: não precisa checar o resto da coluna
                }
            }
        }

        // 3. Verifica a diagonal principal (do canto superior esquerdo ao inferior direito)
        boolean diagonalPrincipal = true;
        for (int i = 0; i < 5; i++) {
            if (!marcados[i][i]) {
                diagonalPrincipal = false;
                break;
            }
        }

        // 4. Verifica a diagonal secundária (do canto superior direito ao inferior esquerdo)
        boolean diagonalSecundaria = true;
        for (int i = 0; i < 5; i++) {
            if (!marcados[i][4 - i]) {
                diagonalSecundaria = false;
                break;
            }
        }


        if (diagonalSecundaria && diagonalPrincipal && linhaCompleta && colunaCompleta) {
            return true;
        } else {
            return false;
        }
    }


    public int getId() {
        return id;
    }

    /**
     * Retorna uma representação visual da cartela, mostrando números marcados.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------ Cartela ").append(id).append(" ------\n");
        sb.append("   B    I    N    G    O\n");
        sb.append("--------------------------\n");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int numero = numeros[i][j];
                boolean isMarcado = marcados[i][j];

                // Formata o número para ter 2 dígitos e adiciona [X] se marcado
                String numStr = String.format("%02d", numero);
                sb.append(isMarcado ? "[" + numStr + "]" : " " + numStr + " ");
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append("--------------------------\n");
        return sb.toString();
    }
}

