package com.valdiviezo.anahi.eventbritehomework.Utils;

import android.util.Log;

import com.valdiviezo.anahi.eventbritehomework.model.CifrasAcertadas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IANumeroAdivinador {

    private static IANumeroAdivinador iaNumeroAdivinador;

    List numeroPropuestoList = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
    int evaluarIndice;
    int evaluarNumero;
    int numeroAnterior;
    int aciertosAnterior;
    HomeWorkUtils homeWorkUtils = HomeWorkUtils.getInstance();
    CifrasAcertadas cifrasAcertadasActual = new CifrasAcertadas();

    private IANumeroAdivinador() {}

    public void initAdivinador() {
        cifrasAcertadasActual.setCantidadNumCorrectos(0);
        cifrasAcertadasActual.setCantidadNumAciertos(0);
        cifrasAcertadasActual.setCantidadNumRegular(0);
        cifrasAcertadasActual.setList(new ArrayList<Integer>());
        evaluarIndice = 0;
        evaluarNumero = 3;
        numeroAnterior =0;
    }

    public static IANumeroAdivinador getInstance() {
        if (iaNumeroAdivinador == null) {
            iaNumeroAdivinador = new IANumeroAdivinador();
        }
        return iaNumeroAdivinador;
    }

    public void setEvaluarIndice(int evaluarIndice) {
        this.evaluarIndice = evaluarIndice;
    }

    public int getEvaluarIndice() {
        return evaluarIndice;
    }

    public List<Integer> getNumeroPropuestoList() {
        return numeroPropuestoList;
    }

    public void setNumeroPropuestoList(List<Integer> numeroPropuestoList) {
        this.numeroPropuestoList = numeroPropuestoList;
    }

    public CifrasAcertadas getCifrasAcertadasActual() {
        return cifrasAcertadasActual;
    }

    public void setCifrasAcertadasActual(CifrasAcertadas cifrasAcertadasActual) {
        this.cifrasAcertadasActual = cifrasAcertadasActual;
    }

    /**
     * Retorna una lista que contiene 4 numeros regulares.
     * @param listPensador
     * @return
     */
    public List<Integer> evaluateCifrasAcertadas(List<Integer> listPensador) {
        aciertosAnterior = cifrasAcertadasActual.getCantidadNumAciertos();
        cifrasAcertadasActual = homeWorkUtils.compareNumbers(listPensador, numeroPropuestoList);

        if ((cifrasAcertadasActual.getCantidadNumAciertos() != 4)) {
            if ((aciertosAnterior == cifrasAcertadasActual.getCantidadNumAciertos())) {
                // cantidad de aciertos no varia, probamos otro numero en el mismo indice
                evaluarNumero = siguienteNumero(evaluarNumero);
                numeroAnterior = cifrasAcertadasActual.getList().get(evaluarIndice);
                numeroPropuestoList.set(evaluarIndice, evaluarNumero);
            } else if (aciertosAnterior < cifrasAcertadasActual.getCantidadNumAciertos()) {
                //solucion aumenta, tenemos mejores resultados - indice aumenta, probamos otro numero
                evaluarIndice = siguienteIndice(evaluarIndice);
                evaluarNumero = siguienteNumero(evaluarNumero);
                numeroAnterior = cifrasAcertadasActual.getList().get(evaluarIndice);
                numeroPropuestoList.set(evaluarIndice, evaluarNumero);
            } else if (aciertosAnterior > cifrasAcertadasActual.getCantidadNumAciertos()) {
                //solucion disminuye - vuelve al anterior numero propuesto - indice aumenta, valor aumenta
                numeroPropuestoList.set(evaluarIndice, numeroAnterior);
                cifrasAcertadasActual = homeWorkUtils.compareNumbers(listPensador,numeroPropuestoList);
                evaluarIndice = siguienteIndice(evaluarIndice);
                evaluarNumero = siguienteNumero(evaluarNumero);
                numeroAnterior = cifrasAcertadasActual.getList().get(evaluarIndice);
                numeroPropuestoList.set(evaluarIndice,evaluarNumero);
                //indice actual restarle 1 al numero y cambiar el nro del siguiente indice
            }

        } else {
            numeroPropuestoList = permutarNumberList(numeroPropuestoList,listPensador);
        }
        Log.d("Lista", numeroPropuestoList.toString()  +  "   Aciertos : " + cifrasAcertadasActual.getCantidadNumAciertos());
        return numeroPropuestoList;
    }

    public void executeInitialComparation (List<Integer> list) {
        cifrasAcertadasActual = homeWorkUtils.compareNumbers(list, numeroPropuestoList);
        numeroAnterior = cifrasAcertadasActual.getList().get(evaluarIndice);

    }

    public int siguienteIndice (int indice) {
        indice++;
        return indice % 4;
    }

    public int siguienteNumero (int num ) {
        do {
            num++;
            num = num%10;
        } while (numeroPropuestoList.contains(num));

        return num;
    }

    /**
     * Permuta una lista hasta hallar los 4 numeros en el orden correcto.
     * @param adivinadorList
     * @param pensadorList
     * @return
     */
    public List<Integer> permutarNumberList (List <Integer> adivinadorList, List pensadorList) {
        List <Integer> lista = adivinadorList;
        while (cifrasAcertadasActual.getCantidadNumCorrectos() != 4) {
            if (doSwap(pensadorList, lista, 0, 1)) break;
            if (doSwap(pensadorList, lista, 2, 3)) break;
            if (doSwap(pensadorList, lista, 1, 2)) break;

        }
        return lista;
    }

    private boolean doSwap(List pensadorList, List<Integer> lista, int primerElemento, int segundoElemento) {
        int cantidadCorrectosAnterior = cifrasAcertadasActual.getCantidadNumCorrectos() ;
        Collections.swap(lista,primerElemento,segundoElemento);
        cifrasAcertadasActual = homeWorkUtils.compareNumbers(lista,pensadorList);
        if (cifrasAcertadasActual.getCantidadNumCorrectos() < cantidadCorrectosAnterior && primerElemento != 1 && segundoElemento != 2) {
            Collections.swap(lista,primerElemento,segundoElemento);
            cifrasAcertadasActual = homeWorkUtils.compareNumbers(lista,pensadorList);
        }
        if (isCorrectSolution()) {
            return true;
        }
        return false;
    }

    public boolean isCorrectSolution () {
        if (cifrasAcertadasActual.getCantidadNumCorrectos() == 4) {
            return true;
        }
        return false;
    }
}