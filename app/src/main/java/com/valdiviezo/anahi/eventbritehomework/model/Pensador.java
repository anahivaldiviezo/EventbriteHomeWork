package com.valdiviezo.anahi.eventbritehomework.model;

import com.valdiviezo.anahi.eventbritehomework.Utils.HomeWorkUtils;

import java.util.ArrayList;
import java.util.List;

public class Pensador {

    private int numeroPensado;

    private List listNumeroPensado = new ArrayList(4);

    private static Pensador pensador = null;

    private Pensador() {

    }

    public static Pensador getInstance()
    {
        if (pensador == null) {
            pensador = new Pensador();

        }
        return pensador;
    }


    public int getNumeroPensado() {
        return numeroPensado;
    }

    public void setNumeroPensado(int numeroPensado) {
        this.numeroPensado = numeroPensado;
    }

    public List getListNumeroPensado() {
        return listNumeroPensado;
    }

    public void setListNumeroPensado(List listNumeroPensado) {
        this.listNumeroPensado = listNumeroPensado;
    }
}
