package org.bingo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Representa uma cartela de Bingo.
 */
public class Cartela {
    private final int id;
    private final List<Integer> numeros;
    private final List<Integer> numerosMarcados;

    public Cartela(int id, int quantidadeNumeros) {
        this.id = id;
        this.numeros = gerarNumeros(quantidadeNumeros);
        this.numerosMarcados = new ArrayList<>();
    }

    /**
     * Gera uma lista de números aleatórios e únicos para a cartela.
     */
    private List<Integer> gerarNumeros(int quantidade) {
        List<Integer> todosNumerosPossiveis = new ArrayList<>();
        for (int i = 0; i <= 99; i++) {
            todosNumerosPossiveis.add(i);
        }
        Collections.shuffle(todosNumerosPossiveis, new Random());
        return new ArrayList<>(todosNumerosPossiveis.subList(0, quantidade));
    }

    /**
     * Marca um número na cartela se ele estiver presente.
     */
    public void marcarNumero(int numeroSorteado) {
        if (numeros.contains(numeroSorteado) && !numerosMarcados.contains(numeroSorteado)) {
            numerosMarcados.add(numeroSorteado);
        }
    }

    /**
     * Verifica se a cartela é vencedora (todos os seus números foram marcados).
     */
    public boolean ehVencedora() {
        return numerosMarcados.size() == numeros.size();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cartela " + id + ": " + numeros;
    }
}

