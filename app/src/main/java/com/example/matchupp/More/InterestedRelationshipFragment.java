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
 * Use the {@link InterestedRelationshipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterestedRelationshipFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Button btn_relationship_abierta, btn_relationship_casual, btn_relationship_exclusiva,
            btn_relationship_formal,btn_relationship_amistad,btn_relationship_indiferente;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InterestedRelationshipFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InterestedRelationshipFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InterestedRelationshipFragment newInstance(String param1, String param2) {
        InterestedRelationshipFragment fragment = new InterestedRelationshipFragment();
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
        View view = inflater.inflate(R.layout.fragment_interested_relationship, container, false);
        btn_relationship_abierta = view.findViewById(R.id.btn_relationship_abierta);
        btn_relationship_casual = view.findViewById(R.id.btn_relationship_casual);
        btn_relationship_exclusiva = view.findViewById(R.id.btn_relationship_exclusiva);
        btn_relationship_formal = view.findViewById(R.id.btn_relationship_formal);
        btn_relationship_amistad = view.findViewById(R.id.btn_relationship_amistad);
        btn_relationship_indiferente = view.findViewById(R.id.btn_relationship_indiferente);

        btn_relationship_abierta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFragment("1");
            }
        });
        btn_relationship_casual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFragment("2");
            }
        });
        btn_relationship_exclusiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFragment("3");
            }
        });
        btn_relationship_formal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFragment("4");
            }
        });
        btn_relationship_amistad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFragment("5");
            }
        });
        btn_relationship_indiferente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFragment("6");
            }
        });
        return view;
    }

    public void cambiarFragment(String seleccion){
        // Crear una instancia del siguiente fragmento ("MyBioFragment")
        MyBioFragment myBioFragment = new MyBioFragment();

        // Recuperar los argumentos de los fragmentos anteriores
        Bundle arguments = getArguments();
        if (arguments != null) {
            String mygender = arguments.getString("mygender");
            String interestedgender = arguments.getString("interestedgender");
            // Guardar la información del botón seleccionado en este fragmento
            Bundle args = new Bundle();
            args.putString("mygender", mygender);
            args.putString("interestedgender", interestedgender);
            args.putString("relationship", seleccion);
            myBioFragment.setArguments(args);
        }

        // Reemplazar el fragmento actual con el siguiente fragmento
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_more, myBioFragment)
                .addToBackStack(null)
                .commit();
    }
}