package com.valdiviezo.anahi.eventbritehomework;

import com.valdiviezo.anahi.eventbritehomework.Utils.HomeWorkUtils;
import com.valdiviezo.anahi.eventbritehomework.model.CifrasAcertadas;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HomeWorkUtilsTests {

    @Test
    public void addNumberToList() {
        int number = 3568;
        List<Integer> list = HomeWorkUtils.getInstance().addNumbersToList(number);

        assertEquals(3L, list.get(0).longValue());
        assertEquals(5L, list.get(1).longValue());
        assertEquals(6L, list.get(2).longValue());
        assertEquals(8L, list.get(3).longValue());
    }

    @Test
    public void listToNumber() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        int number = HomeWorkUtils.getInstance().listToNumber(list);

        assertEquals(number, 1234);
    }

    @Test
    public void compareEqualNumbers() {
        List<Integer> listAdivinador = Arrays.asList(8, 5, 3, 4);
        List<Integer> listPensador = Arrays.asList(8, 5, 3, 4);

        CifrasAcertadas cifrasAcertadas = HomeWorkUtils.getInstance().compareNumbers(listPensador,listAdivinador);
        assertEquals(4, cifrasAcertadas.getCantidadNumCorrectos());
    }

    @Test
    public void compareDifferentNumbers() {
        List<Integer> listAdivinador = Arrays.asList(8, 5, 3, 4);
        List<Integer> listPensador = Arrays.asList(8, 5, 7, 1);

        CifrasAcertadas cifrasAcertadas = HomeWorkUtils.getInstance().compareNumbers(listPensador,listAdivinador);
        assertEquals(2, cifrasAcertadas.getCantidadNumCorrectos());
    }

    @Test
    public void compareEqualRegularNumbers() {
        List<Integer> listAdivinador = Arrays.asList(8, 5, 3, 4);
        List<Integer> listPensador = Arrays.asList(4,3, 5, 8 );

        CifrasAcertadas cifrasAcertadas = HomeWorkUtils.getInstance().compareNumbers(listPensador,listAdivinador);
        assertEquals(4, cifrasAcertadas.getCantidadNumRegular());
    }


}