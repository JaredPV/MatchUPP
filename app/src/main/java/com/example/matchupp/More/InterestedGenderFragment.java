package com.example.matchupp.More;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.matchupp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InterestedGenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterestedGenderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Button btn_interested_hombre, btn_interested_mujer, btn_interested_nobinario,btn_interested_indiferente;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InterestedGenderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InterestedGenderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InterestedGenderFragment newInstance(String param1, String param2) {
        InterestedGenderFragment fragment = new InterestedGenderFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_interested_gender, container, false);
        btn_interested_hombre = view.findViewById(R.id.btn_interested_hombre);
        btn_interested_mujer = view.findViewById(R.id.btn_interested_mujer);
        btn_interested_nobinario = view.findViewById(R.id.btn_interested_nobinario);
        btn_interested_indiferente = view.findViewById(R.id.btn_interested_indiferente);

        btn_interested_hombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFragment("1");
            }
        });
        btn_interested_mujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFragment("2");
            }
        });
        btn_interested_nobinario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFragment("3");
            }
        });
        btn_interested_indiferente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFragment("5");
            }
        });

        return view;
    }

    public void cambiarFragment(String seleccion){
        // Crear una instancia del siguiente fragmento ("MyBioFragment")
        InterestedRelationshipFragment interestedRelationshipFragment = new InterestedRelationshipFragment();

        // Recuperar los argumentos de los fragmentos anteriores
        Bundle arguments = getArguments();
        if (arguments != null) {
            String mygender = arguments.getString("mygender");
            // Guardar la información del botón seleccionado en este fragmento
            Bundle args = new Bundle();
            args.putString("mygender", mygender);
            args.putString("interestedgender", seleccion);
            interestedRelationshipFragment.setArguments(args);
        }

        // Reemplazar el fragmento actual con el siguiente fragmento
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_more, interestedRelationshipFragment)
                .addToBackStack(null)
                .commit();
    }
}