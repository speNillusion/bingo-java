package org.bingo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Sorteia números de 1 a 75, associando cada um à sua letra (B, I, N, G, O).
 */
public class Sorteador {
    private final List<Integer> numerosParaSortear;
    private final List<String> pedrasSorteadas;

    public Sorteador() {
        this.numerosParaSortear = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            numerosParaSortear.add(i);
        }
        Collections.shuffle(numerosParaSortear, new Random());
        this.pedrasSorteadas = new ArrayList<>();
    }

    /**
     * Sorteia o próximo número e retorna a "pedra" (letra + número).
     * @return Um array de String contendo [letra, número], ou null se acabar.
     */
    public String[] sortearProximo() {
        if (numerosParaSortear.isEmpty()) {
            return null; // Fim do sorteio
        }
        int numero = numerosParaSortear.remove(0);
        char letra = getLetraDoNumero(numero);

        String pedra = letra + "-" + String.format("%02d", numero);
        pedrasSorteadas.add(pedra);

        return new String[]{String.valueOf(letra), String.valueOf(numero)};
    }

    public static char getLetraDoNumero(int numero) {
        if (numero >= 1 && numero <= 15) return 'B';
        if (numero >= 16 && numero <= 30) return 'I';
        if (numero >= 31 && numero <= 45) return 'N';
        if (numero >= 46 && numero <= 60) return 'G';
        return 'O'; // 61 a 75
    }

    public static int getColunaDaLetra(char letra) {
        switch (letra) {
            case 'B': return 0;
            case 'I': return 1;
            case 'N': return 2;
            case 'G': return 3;
            case 'O': return 4;
            default: return -1;
        }
    }

    public List<String> getPedrasSorteadas() {
        return pedrasSorteadas;
    }
}

