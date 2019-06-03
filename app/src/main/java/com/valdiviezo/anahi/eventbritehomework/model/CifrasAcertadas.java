package com.valdiviezo.anahi.eventbritehomework.model;

import java.util.ArrayList;
import java.util.List;

public class CifrasAcertadas {

    private int cantidadNumAciertos = 0;
    private int cantidadNumRegular =0;
    private int cantidadNumCorrectos =0;


    private int numero;
    private List<Integer> list = new ArrayList<>();

    public int getCantidadNumCorrectos() {
        return cantidadNumCorrectos;
    }

    public void setCantidadNumCorrectos(int cantidadNumCorrectos) {
        this.cantidadNumCorrectos = cantidadNumCorrectos;
    }

    public int getCantidadNumAciertos() {
        return cantidadNumAciertos;
    }

    public void setCantidadNumAciertos(int cantidadNumAciertos) {
        this.cantidadNumAciertos = cantidadNumAciertos;
    }

    public int getCantidadNumRegular() {
        return cantidadNumRegular;
    }

    public void setCantidadNumRegular(int cantidadNumRegular) {
        this.cantidadNumRegular = cantidadNumRegular;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


}
