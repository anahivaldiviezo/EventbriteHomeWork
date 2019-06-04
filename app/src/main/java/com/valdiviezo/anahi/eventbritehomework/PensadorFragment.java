package com.valdiviezo.anahi.eventbritehomework;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.valdiviezo.anahi.eventbritehomework.Utils.HomeWorkUtils;
import com.valdiviezo.anahi.eventbritehomework.Utils.IANumeroAdivinador;
import com.valdiviezo.anahi.eventbritehomework.model.Adivinador;
import com.valdiviezo.anahi.eventbritehomework.model.Pensador;


public class PensadorFragment extends Fragment {

    private EditText pensadorNumber;
    private TextView iaNumber;
    private TextView txtMachineWins;
    private Button sendButton;
    private Pensador pensador = Pensador.getInstance();
    private Adivinador adivinador = Adivinador.getInstance();
    private HomeWorkUtils homeworkUtils = HomeWorkUtils.getInstance();
    private IANumeroAdivinador iaNumeroAdivinador = IANumeroAdivinador.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pensador, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pensadorNumber = getView().findViewById(R.id.pensadorNumber);
        iaNumber = getView().findViewById(R.id.ia_number);
        txtMachineWins = getView().findViewById(R.id.txt_machinewins);
        sendButton = getView().findViewById(R.id.sendbutton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PENSADOR ES LA PERSONA
                //ADIVINADOR LA COMPUTADORA
                iaNumeroAdivinador.initAdivinador();
                pensador.setNumeroPensado(Integer.parseInt(pensadorNumber.getText().toString()));
                pensador.setListNumeroPensado(homeworkUtils.addNumbersToList(pensador.getNumeroPensado()));
                iaNumber.setText("Anahi Rules");
                iaNumeroAdivinador.executeInitialComparation(pensador.getListNumeroPensado());
                while (iaNumeroAdivinador.getCifrasAcertadasActual().getCantidadNumAciertos() != 4) {
                    adivinador.setListNumberAdivinador(iaNumeroAdivinador.getNumeroPropuestoList());
                    adivinador.setNumeroSeleccionado(homeworkUtils.listToNumber(adivinador.getListNumberAdivinador()));
                    adivinador.setListNumberAdivinador(iaNumeroAdivinador.evaluateCifrasAcertadas(pensador.getListNumeroPensado()));
                }
                iaNumber.setText("Numero en el que pensaste : " + iaNumeroAdivinador.getCifrasAcertadasActual().getNumero());
                txtMachineWins.setVisibility(View.VISIBLE);
                String ganamos = "Anahi Rules";

            }
        });
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if ((source != "")) {
                    String currentChar = String.valueOf(source.toString().charAt(source.length() - 1));
                    if(dest.toString().contains(currentChar) || dest.length() == 4) {
                        return "";
                    }
                }
                return null;
            }
        };
        pensadorNumber.setFilters(new InputFilter[]{filter});
    }


    /**
     * Crea un diálogo de alerta sencillo
     * @return Nuevo diálogo
     */
    public AlertDialog createDialog(String title, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title)
                .setMessage(mensaje)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

        return builder.create();
    }
}
