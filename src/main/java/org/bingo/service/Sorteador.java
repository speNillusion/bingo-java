package org.bingo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Responsável por sortear os números do Bingo de 0 a 99 sem repetição.
 */
public class Sorteador {
    private final List<Integer> numerosParaSortear;
    private final List<Integer> numerosJaSorteados;

    public Sorteador() {
        this.numerosParaSortear = new ArrayList<>();
        for (int i = 0; i <= 99; i++) {
            numerosParaSortear.add(i);
        }
        // Embaralha a lista para garantir uma ordem de sorteio aleatória
        Collections.shuffle(numerosParaSortear, new Random());
        this.numerosJaSorteados = new ArrayList<>();
    }

    /**
     * Sorteia o próximo número da lista.
     * @return O número sorteado, ou -1 se não houver mais números.
     */
    public int sortearProximo() {
        if (numerosParaSortear.isEmpty()) {
            return -1; // Indica que todos os números já foram sorteados
        }
        int numeroSorteado = numerosParaSortear.remove(0);
        numerosJaSorteados.add(numeroSorteado);
        return numeroSorteado;
    }

    public List<Integer> getNumerosJaSorteados() {
        return numerosJaSorteados;
    }
}
