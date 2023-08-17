package com.example.matchupp.More;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.matchupp.MenuActivity;
import com.example.matchupp.R;
import com.example.matchupp.Registro.Endpoints;
import com.example.matchupp.Registro.VolleySingleton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyBioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyBioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private EditText et_biografia;
    private Button btn_more_terminado;
    private TextView textViewDate;
    private SharedPreferences preferences;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyBioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyBioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyBioFragment newInstance(String param1, String param2) {
        MyBioFragment fragment = new MyBioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getActivity().getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(com.example.matchupp.R.layout.fragment_my_bio, container, false);
        et_biografia = view.findViewById(R.id.et_biografia);
        btn_more_terminado = view.findViewById(R.id.btn_more_terminado);

        Bundle arguments = getArguments();
        String mygender = arguments.getString("mygender");
        String interestedgender = arguments.getString("interestedgender");
        String relationship = arguments.getString("relationship");


        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Aquí puedes hacer algo con la fecha seleccionada
                // Por ejemplo, mostrarla en un TextView
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, month);
                selectedDate.set(Calendar.DAY_OF_MONTH, day);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = dateFormat.format(selectedDate.getTime());

                // textViewDate es un TextView en tu layout donde deseas mostrar la fecha seleccionada
                textViewDate.setText(formattedDate);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        // Abre el DatePickerDialog cuando se hace clic en el elemento DatePicker
        view.findViewById(R.id.datePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        btn_more_terminado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post(mygender, interestedgender, relationship, et_biografia.getText().toString(),
                        calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1)
                                + "-" + calendar.get(Calendar.DAY_OF_MONTH));

            }
        });
        return view;
    }
    private void post(String gender, String interested, String relationship, String bio, String birthday){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.more_url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                boolean success = jsonObject.optBoolean("success");
                if (success) {
                    preferences.edit().putString("gender_id", gender).apply();
                    preferences.edit().putString("birthday", birthday).apply();
                    preferences.edit().putString("details", bio).apply();
                    Intent intent = new Intent(requireContext(), MenuActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                } else {
                    new android.app.AlertDialog.Builder(getContext())
                            .setTitle("Error")
                            .setMessage(jsonObject.getString("message"))
                            .setPositiveButton("Aceptar", null)
                            .create().show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Snackbar.make(getView(), "Error: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        }, error -> {
            Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
            Log.d("VOLLEY", error.getMessage());
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("gender", gender);
                params.put("interested", interested);
                params.put("relationship", relationship);
                params.put("bio", bio);
                params.put("birthday", birthday);
                params.put("id", preferences.getString("id", ""));
                return params;
            }

        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }
}