package com.valdiviezo.anahi.eventbritehomework.model;

import java.util.ArrayList;
import java.util.List;

public class Adivinador {
    private static Adivinador adivinador = null;

    private int numeroSeleccionado;

    private List listNumberAdivinador = new ArrayList(4);


    public static Adivinador getAdivinador() {
        return adivinador;
    }

    public static void setAdivinador(Adivinador adivinador) {
        Adivinador.adivinador = adivinador;
    }

    private Adivinador() {

    }

    public static Adivinador getInstance()
    {
        if (adivinador == null) {
            adivinador = new Adivinador();
        }
        return adivinador;
    }

    public int getNumeroSeleccionado() {
        return numeroSeleccionado;
    }

    public void setNumeroSeleccionado(int numeroSeleccionado) {
        this.numeroSeleccionado = numeroSeleccionado;
    }
    public List getListNumberAdivinador() {
        return listNumberAdivinador;
    }

    public void setListNumberAdivinador(List listNumberAdivinador) {
        this.listNumberAdivinador = listNumberAdivinador;
    }



}
