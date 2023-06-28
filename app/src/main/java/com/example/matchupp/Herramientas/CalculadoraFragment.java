package com.example.matchupp.Herramientas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;
import com.example.matchupp.R;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculadoraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculadoraFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tv_resultado, tv_operacion;
    private MaterialButton btn_parentesis_izq, btn_parentesis_der, btn_porcentaje, btn_division;
    private MaterialButton btn_7, btn_8, btn_9, btn_multiplicacion;
    private MaterialButton btn_4, btn_5, btn_6, btn_resta;
    private MaterialButton btn_1, btn_2, btn_3, btn_suma;
    private MaterialButton btn_punto, btn_0, btn_c, btn_igual;

    public CalculadoraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculadoraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculadoraFragment newInstance(String param1, String param2) {
        CalculadoraFragment fragment = new CalculadoraFragment();
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
        View view = inflater.inflate(R.layout.fragment_calculadora, container, false);

        tv_resultado = view.findViewById(R.id.tv_resultado);
        tv_operacion = view.findViewById(R.id.tv_operacion);

        btn_parentesis_izq = view.findViewById(R.id.btn_parentesis_izq);
        btn_parentesis_der = view.findViewById(R.id.btn_parentesis_der);
        btn_porcentaje = view.findViewById(R.id.btn_porcentaje);
        btn_division = view.findViewById(R.id.btn_division);

        btn_7 = view.findViewById(R.id.btn_7);
        btn_8 = view.findViewById(R.id.btn_8);
        btn_9 = view.findViewById(R.id.btn_9);
        btn_multiplicacion = view.findViewById(R.id.btn_multiplicacion);

        btn_4 = view.findViewById(R.id.btn_4);
        btn_5 = view.findViewById(R.id.btn_5);
        btn_6 = view.findViewById(R.id.btn_6);
        btn_resta = view.findViewById(R.id.btn_resta);

        btn_1 = view.findViewById(R.id.btn_1);
        btn_2 = view.findViewById(R.id.btn_2);
        btn_3 = view.findViewById(R.id.btn_3);
        btn_suma = view.findViewById(R.id.btn_suma);

        btn_punto = view.findViewById(R.id.btn_punto);
        btn_0 = view.findViewById(R.id.btn_0);
        btn_c = view.findViewById(R.id.btn_c);
        btn_igual = view.findViewById(R.id.btn_igual);

        asignarId(btn_parentesis_izq, view, R.id.btn_parentesis_izq);
        asignarId(btn_parentesis_der, view, R.id.btn_parentesis_der);
        asignarId(btn_porcentaje, view, R.id.btn_porcentaje);
        asignarId(btn_division, view, R.id.btn_division);

        asignarId(btn_7, view, R.id.btn_7);
        asignarId(btn_8, view, R.id.btn_8);
        asignarId(btn_9, view, R.id.btn_9);
        asignarId(btn_multiplicacion, view, R.id.btn_multiplicacion);

        asignarId(btn_4, view, R.id.btn_4);
        asignarId(btn_5, view, R.id.btn_5);
        asignarId(btn_6, view, R.id.btn_6);
        asignarId(btn_resta, view, R.id.btn_resta);

        asignarId(btn_1, view, R.id.btn_1);
        asignarId(btn_2, view, R.id.btn_2);
        asignarId(btn_3, view, R.id.btn_3);
        asignarId(btn_suma, view, R.id.btn_suma);

        asignarId(btn_punto, view, R.id.btn_punto);
        asignarId(btn_0, view, R.id.btn_0);
        asignarId(btn_c, view, R.id.btn_c);
        asignarId(btn_igual, view, R.id.btn_igual);

        return view;
    }

    private void asignarId(MaterialButton btn, View view, int id) {
        btn = view.findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        MaterialButton boton = (MaterialButton) v;
        String texto_boton = boton.getText().toString();
        String operacion = tv_operacion.getText().toString();
        if (texto_boton.equals("=")) {
            tv_operacion.setText(tv_resultado.getText());
            return;
        }
        if(texto_boton.equals("C")){
            if(operacion.isEmpty()) return;
            operacion = operacion.substring(0,operacion.length()-1);
        }else{
            operacion = operacion+texto_boton;
        }

        tv_operacion.setText(operacion);
        if (operacion.equals("")) operacion="0";
        operacion = operacion.replace("%","/100");
        operacion = operacion.replace(")(", ")*(");
        operacion = operacion.replace("0(","0*(");
        operacion = operacion.replace("1(","1*(");
        operacion = operacion.replace("2(","2*(");
        operacion = operacion.replace("3(","3*(");
        operacion = operacion.replace("4(","4*(");
        operacion = operacion.replace("5(","5*(");
        operacion = operacion.replace("6(","6*(");
        operacion = operacion.replace("7(","7*(");
        operacion = operacion.replace("8(","8*(");
        operacion = operacion.replace("9(","9*(");

        String resultado_final = calcular(operacion);

        if(!resultado_final.equals("Err")){
            tv_resultado.setText(resultado_final);
        }
    }

    String calcular(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String resultado_final = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(resultado_final.endsWith(".0")){
                resultado_final = resultado_final.replace(".0","");
            }
            return  resultado_final;
        }catch (Exception err){
            return "Err";
        }
    }
}