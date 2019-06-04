package com.valdiviezo.anahi.eventbritehomework;
import com.valdiviezo.anahi.eventbritehomework.Utils.IANumeroAdivinador;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class IANumeroAdivinadorTests {

    @Test
    public void permutarToGetCorrectNumberList() {

        List<Integer> listPensador = Arrays.asList(2, 0, 1, 9);
        List<Integer> listAdivinador = Arrays.asList(1, 2, 9, 0);
        List<Integer> listaPermutada;

        listaPermutada = IANumeroAdivinador.getInstance().permutarNumberList(listAdivinador,listPensador);

        assertEquals(listPensador.get(0), listaPermutada.get(0));
        assertEquals(listPensador.get(1), listaPermutada.get(1));
        assertEquals(listPensador.get(2), listaPermutada.get(2));
        assertEquals(listPensador.get(3), listaPermutada.get(3));

    }

}
