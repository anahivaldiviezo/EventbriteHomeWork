package com.valdiviezo.anahi.eventbritehomework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.valdiviezo.anahi.eventbritehomework.Utils.HomeWorkUtils;
import com.valdiviezo.anahi.eventbritehomework.model.Adivinador;
import com.valdiviezo.anahi.eventbritehomework.model.CifrasAcertadas;
import com.valdiviezo.anahi.eventbritehomework.model.Pensador;

import java.util.ArrayList;
import java.util.List;

public class AdivinadorFragment extends Fragment {

    private EditText adivinadorNumber;
    private Button checkButton;
    private TextView cantidadNumerosCorrectos;
    private TextView cantidadNumerosRegulares;
    private TextView ganasteTxt;
    private List listNumbers = new ArrayList();
    private Adivinador adivinador = Adivinador.getInstance();
    private Pensador pensador = Pensador.getInstance();
    private HomeWorkUtils homeworkUtils = HomeWorkUtils.getInstance();
    private CifrasAcertadas cifrasAcertadas = new CifrasAcertadas();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adivinador, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adivinadorNumber = getView().findViewById(R.id.adivinadorNumber);

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
        adivinadorNumber.setFilters(new InputFilter[]{filter});
        cantidadNumerosCorrectos = getView().findViewById(R.id.correct_digits);
        cantidadNumerosRegulares = getView().findViewById(R.id.regular_digits);
        ganasteTxt = getView().findViewById(R.id.txt_win);
        checkButton =  getView().findViewById(R.id.checkbutton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adivinadorNumber.onEditorAction(EditorInfo.IME_ACTION_DONE);
                adivinador.setNumeroSeleccionado(Integer.parseInt(adivinadorNumber.getText().toString()));
                adivinador.setListNumberAdivinador(homeworkUtils.addNumbersToList(adivinador.getNumeroSeleccionado()));
                if (adivinador.getListNumberAdivinador().size() == 4) {
                    cifrasAcertadas.setList(adivinador.getListNumberAdivinador());
                    cifrasAcertadas = homeworkUtils.compareNumbers(pensador.getListNumeroPensado(), adivinador.getListNumberAdivinador());
                    cantidadNumerosCorrectos.setText(getResources().getString(R.string.cantidad_correctos) + String.valueOf(cifrasAcertadas.getCantidadNumCorrectos()));
                    cantidadNumerosRegulares.setText(getResources().getString(R.string.cantidad_regulares) + String.valueOf(cifrasAcertadas.getCantidadNumRegular()));
                    if (cifrasAcertadas.getCantidadNumCorrectos() == 4) {
                        ganasteTxt.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        generatePensadorNumber();
    }

    private void generatePensadorNumber() {
        pensador.setListNumeroPensado( HomeWorkUtils.getInstance().generateRandomNumberList());
    }



}
