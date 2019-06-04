package com.valdiviezo.anahi.eventbritehomework.Utils;

import com.valdiviezo.anahi.eventbritehomework.model.CifrasAcertadas;

import java.util.ArrayList;
import java.util.List;

 public class HomeWorkUtils {

     public static int CANT_DIGITOS = 4;

     private static HomeWorkUtils utils = null;

     private HomeWorkUtils() {

     }

     public static HomeWorkUtils getInstance()
     {
         if (utils == null) {
             utils = new HomeWorkUtils();
         }
         return utils;
     }

     /**
      * Genera numeros random del 0 al 9
      * @return un numero del 0 al 9
      */
    public int generateRandomNumber() {
        int number = (int) (Math.random() * 9);
        return number;
    }

     /**
      * Genera una lista con numeros random que no se repiten
      * @return lista de 4 numeros
      */
    public List<Integer> generateRandomNumberList() {
        List<Integer> listOfNumbers = new ArrayList<>();
        while (listOfNumbers.size() < CANT_DIGITOS) {
            int posibleNumber = generateRandomNumber();
            if (!listOfNumbers.contains(posibleNumber)) {
                listOfNumbers.add(posibleNumber);
            }
        }
        return listOfNumbers;
    }

     /**
      * Recibe un numero de 4 digitos que no se repiten
      * @param nums
      * @return lista de 4 numeros
      */
    public List<Integer> addNumbersToList (int nums) {
         List<Integer> list = new ArrayList<>(4);
        String numeros = String.valueOf(nums);
        int length = numeros.length();
        if (length == 3) {
            numeros = "0"+numeros;
            length = numeros.length();
        }
        if (length == CANT_DIGITOS) {
            for (int i = 0 ; i <=CANT_DIGITOS-1; i++) {
                list.add(i,Integer.parseInt(numeros.substring(i,i+1)));
            }
        }
        return list;
    }


    public int listToNumber (List<Integer> list) {
        int num;
        String numero = "";
        for (int i= 0 ; i< list.size();i++) {
            numero += list.get(i).toString();
        }
        num = Integer.parseInt(numero);
        return num;
    }

    public CifrasAcertadas compareNumbers (List<Integer> listPensador, List<Integer> listAdivinador) {
        int cantidadRegular = 0;
        int cantidadCorrectos = 0 ;
        int cantidadAciertos = 0;
        for (int i = 0; i <= CANT_DIGITOS-1; i = i + 1) {
            if (listPensador.contains(listAdivinador.get(i))) {
                cantidadAciertos += 1;
            }
            if (listPensador.contains(listAdivinador.get(i)) && listAdivinador.get(i) == listPensador.get(i)) {
                cantidadCorrectos += 1;
            }
        }

        cantidadRegular = cantidadAciertos - cantidadCorrectos;

        CifrasAcertadas respuesta = new CifrasAcertadas();
        respuesta.setCantidadNumAciertos(cantidadAciertos);
        respuesta.setCantidadNumRegular(cantidadRegular);
        respuesta.setCantidadNumCorrectos(cantidadCorrectos);
        respuesta.setNumero(listToNumber(listAdivinador));
        respuesta.setList(listAdivinador);
        return respuesta;
    }

 }
