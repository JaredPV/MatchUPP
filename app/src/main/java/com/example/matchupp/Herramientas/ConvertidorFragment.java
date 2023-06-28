package com.example.matchupp.Herramientas;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.matchupp.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConvertidorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConvertidorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private Spinner sp_unidad1, sp_unidad2;
    private EditText tn_entrada1, tn_entrada2;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConvertidorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConvertidorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConvertidorFragment newInstance(String param1, String param2) {
        ConvertidorFragment fragment = new ConvertidorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convertidor, container, false);

        sp_unidad1 = view.findViewById(R.id.sp_unidad1);
        sp_unidad2 = view.findViewById(R.id.sp_unidad2);

        tn_entrada1 = view.findViewById(R.id.tn_entrada1);
        tn_entrada2 = view.findViewById(R.id.tn_entrada2);

        ArrayList<String> unidades_al = new ArrayList<>(Arrays.asList("Metro", "Centimetro", "Kilometro", "Pulgada", "Pie", "Milla"));
        ArrayAdapter unidades_aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,unidades_al);

        unidades_aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_unidad1.setAdapter(unidades_aa);
        sp_unidad2.setAdapter(unidades_aa);

        tn_entrada1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction()==KeyEvent.ACTION_UP){
                    if(tn_entrada1.getText().length()==0){
                        tn_entrada2.setText(null);
                        return false;
                    }
                    double resultado = calcularLongitud(Double.parseDouble(tn_entrada1.getText().toString()),sp_unidad1.getSelectedItem().toString(), sp_unidad2.getSelectedItem().toString());
                    tn_entrada2.setText(String.valueOf(resultado));
                }
                return false;
            }
        });
        tn_entrada2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction()==KeyEvent.ACTION_UP){
                    if(tn_entrada2.getText().length()==0){
                        tn_entrada1.setText(null);
                        return false;
                    }
                    double resultado = calcularLongitud(Double.parseDouble(tn_entrada2.getText().toString()),sp_unidad2.getSelectedItem().toString(), sp_unidad1.getSelectedItem().toString());
                    tn_entrada1.setText(String.valueOf(resultado));
                }
                return false;
            }
        });

        sp_unidad1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(tn_entrada1.getText().length()!= 0 || tn_entrada1.getText().length()!=0){
                    double resultado = calcularLongitud(Double.parseDouble(tn_entrada1.getText().toString()), sp_unidad1.getSelectedItem().toString(), sp_unidad2.getSelectedItem().toString());
                    tn_entrada2.setText(String.valueOf(resultado));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp_unidad2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(tn_entrada1.getText().length()!= 0 || tn_entrada1.getText().length()!=0){
                    double resultado = calcularLongitud(Double.parseDouble(tn_entrada1.getText().toString()), sp_unidad1.getSelectedItem().toString(), sp_unidad2.getSelectedItem().toString());
                    tn_entrada2.setText(String.valueOf(resultado));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public double calcularLongitud(double input, String entrada, String salida){
        double resultado;
        if(sp_unidad1.getSelectedItem()!=sp_unidad2.getSelectedItem()){
            if(entrada.equals("Metro")){
                if(salida.equals("Centimetro")){
                    resultado = input*100;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Kilometro")) {
                    resultado = input/1000;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Pulgada")) {
                    resultado = input*39.37;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Pie")) {
                    resultado = input*3.281;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }else {
                    resultado = input/1609;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }
            } else if (entrada.equals("Centimetro")) {
                if(salida.equals("Metro")){
                    resultado = input/100;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Kilometro")) {
                    resultado = input/100000;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Pulgada")) {
                    resultado = input*2.54;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Pie")) {
                    resultado = input*30.48;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }else {
                    resultado = input/160900;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }
            } else if (entrada.equals("Kilometro")) {
                if(salida.equals("Metro")){
                    resultado = input*1000;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Centimetro")) {
                    resultado = input*100000;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Pulgada")) {
                    resultado = input*39370;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Pie")) {
                    resultado = input*3281;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }else {
                    resultado = input/1.609;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }
            } else if (entrada.equals("Pulgada")) {
                if(salida.equals("Metro")){
                    resultado = input/39.37;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Centimetro")) {
                    resultado = input*2.54;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Kilometro")) {
                    resultado = input/39370;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Pie")) {
                    resultado = input/12;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }else {
                    resultado = input/63360;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }
            } else if (entrada.equals("Pie")) {
                if(salida.equals("Metro")){
                    resultado = input/3.281;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Centimetro")) {
                    resultado = input*30.48;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Kilometro")) {
                    resultado = input/3281;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Pulgada")) {
                    resultado = input*12;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }else {
                    resultado = input/5280;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }
            }else {
                if(salida.equals("Metro")){
                    resultado = input*1609;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Centimetro")) {
                    resultado = input*160900;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Kilometro")) {
                    resultado = input*1.609;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                } else if (salida.equals("Pie")) {
                    resultado = input*5280;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }else {
                    resultado = input*63360;
                    resultado = Math.round(resultado * 100.0)/100.0;
                    return  resultado;
                }
            }
        }
        return input;
    }
}