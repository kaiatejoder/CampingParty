package com.isii.campingmvc.model;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Modelo {
    private final Parcela[] parcelas;
    private final boolean[] libres;

    public Modelo() {
        parcelas = new Parcela[16];
        for (int i = 0; i < 16; i++) {
            parcelas[i] = new Parcela(i + 1, 10 + i, i % 2 == 0, 15 + i * 2);
        }
        libres = new boolean[16];
        Arrays.fill(libres, true);
    }

    public boolean[] getParcelasLibres() {
        return libres.clone();
    }

    public Parcela getParcela(int index) { return parcelas[index]; }

    public boolean reservarParcela(int index) {
        if (index < 0 || index >= libres.length) return false;
        if (!libres[index]) return false;
        libres[index] = false;
        return true;
    }

    public int[] indicesLibres() {
        return IntStream.range(0, libres.length).filter(i -> libres[i]).toArray();
    }
}
