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
 * Use the {@link MyGenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyGenderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Button btn_mygender_hombre, btn_mygender_mujer, btn_mygender_otro, btn_mygender_nobinario;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyGenderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyGenderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyGenderFragment newInstance(String param1, String param2) {
        MyGenderFragment fragment = new MyGenderFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_gender, container, false);

        btn_mygender_hombre = view.findViewById(R.id.btn_mygender_hombre);
        btn_mygender_mujer = view.findViewById(R.id.btn_mygender_mujer);
        btn_mygender_otro = view.findViewById(R.id.btn_mygender_otro);
        btn_mygender_nobinario = view.findViewById(R.id.btn_mygender_nobinario);
        btn_mygender_hombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la información del botón seleccionado
                cambiarFragment("1");
            }
        });
        btn_mygender_mujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la información del botón seleccionado
                cambiarFragment("2");
            }
        });
        btn_mygender_nobinario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la información del botón seleccionado
                cambiarFragment("3");
            }
        });
        btn_mygender_otro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la información del botón seleccionado
                cambiarFragment("4");
            }
        });

        return view;
    }
    public void cambiarFragment(String seleccion){
            // Crear una instancia del siguiente fragmento ("InterestedGenderFragment")
            InterestedGenderFragment interestedGenderFragment = new InterestedGenderFragment();

            // Pasar la información del botón seleccionado como argumento del fragmento
            Bundle args = new Bundle();
            args.putString("mygender", seleccion);
            interestedGenderFragment.setArguments(args);

            // Reemplazar el fragmento actual con el siguiente fragmento
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_more, interestedGenderFragment)
                    .addToBackStack(null)
                    .commit();
    }
}