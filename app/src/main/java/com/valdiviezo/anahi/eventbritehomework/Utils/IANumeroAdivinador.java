package com.valdiviezo.anahi.eventbritehomework.Utils;

import com.valdiviezo.anahi.eventbritehomework.model.CifrasAcertadas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IANumeroAdivinador {

    private static IANumeroAdivinador iaNumeroAdivinador;

    List numeroPropuestoList = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
    int numeroPropuesto;
    int evaluarIndice;
    int evaluarNumero;
    List<CifrasAcertadas> cifrasAcertadasList = new ArrayList<>();
    HomeWorkUtils homeWorkUtils = HomeWorkUtils.getInstance();
    CifrasAcertadas cifrasAcertadasEstadoPrevio = new CifrasAcertadas();
    CifrasAcertadas cifrasAcertadasActual = new CifrasAcertadas();

    private IANumeroAdivinador() {
        cifrasAcertadasEstadoPrevio.setCantidadNumCorrectos(0);
        cifrasAcertadasEstadoPrevio.setCantidadNumAciertos(0);
        cifrasAcertadasEstadoPrevio.setCantidadNumRegular(0);
        cifrasAcertadasActual.setCantidadNumCorrectos(0);
        cifrasAcertadasActual.setCantidadNumAciertos(0);
        cifrasAcertadasActual.setCantidadNumRegular(0);
        cifrasAcertadasActual.setList(new ArrayList<Integer>());
        evaluarIndice = 0;
        evaluarNumero = 4;
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

    public List<CifrasAcertadas> getCifrasAcertadasList() {
        return cifrasAcertadasList;
    }

    public void setCifrasAcertadasList(List<CifrasAcertadas> cifrasAcertadasList) {
        this.cifrasAcertadasList = cifrasAcertadasList;
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


    public List<Integer> evaluateCifrasAcertadas(List<Integer> listPensador) {
        int aciertosAnterior = cifrasAcertadasActual.getCantidadNumAciertos();
        cifrasAcertadasActual = homeWorkUtils.compareNumbers(listPensador, numeroPropuestoList);
        cifrasAcertadasList.add(cifrasAcertadasActual);
        int numeroAnterior = cifrasAcertadasActual.getList().get(evaluarIndice);

        if ((aciertosAnterior == cifrasAcertadasActual.getCantidadNumAciertos()) && (cifrasAcertadasActual.getCantidadNumAciertos() < 4)) {
            // cantidad de aciertos no varia, probamos otro numero en el mismo indice
            evaluarNumero = siguienteNumero(evaluarNumero);
            numeroPropuestoList.set(evaluarIndice, evaluarNumero);
        } else if (aciertosAnterior < cifrasAcertadasActual.getCantidadNumAciertos()) {
            //solucion aumenta, tenemos mejores resultados - indice aumenta, probamos otro numero
            evaluarIndice = siguienteIndice(evaluarIndice);
            evaluarNumero = siguienteNumero(evaluarNumero);
            numeroPropuestoList.set(evaluarIndice, evaluarNumero);
        } else if (aciertosAnterior > cifrasAcertadasActual.getCantidadNumAciertos()) {
            //solucion disminuye - vuelve al anterior numero propuesto - indice aumenta, valor aumenta
            numeroPropuestoList.set(evaluarIndice, numeroAnterior);
            evaluarIndice = siguienteIndice(evaluarIndice);
            evaluarNumero = siguienteNumero(evaluarNumero);
            numeroPropuestoList.set(evaluarIndice,evaluarNumero);
            //indice actual restarle 1 al numero y cambiar el nro del siguiente indice
        }

        return numeroPropuestoList;
    }


    public int siguienteIndice (int indice) {
        indice++;
        return indice % 4;
    }

    public int siguienteNumero (int num ) {
        num++;
        return num%10;
    }

}