package com.example.matchupp;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.matchupp.Registro.Endpoints;
import com.example.matchupp.Registro.VolleySingleton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class PerfilFragment extends Fragment {

    private TextView profile_name, profile_lastname, profile_nickname, profile_email;
    private ImageView profile_photo, edit_name, edit_lastname, edit_nickname, edit_password, edit_profile_photo, headerImageView;
    private String imageFilePath, profileImageUrl;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private SharedPreferences sharedPreferences;
    private View headerView;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        profile_name = view.findViewById(R.id.profile_name);
        profile_lastname = view.findViewById(R.id.profile_lastname);
        profile_nickname = view.findViewById(R.id.profile_nickname);
        profile_email = view.findViewById(R.id.profile_email);
        profile_photo = view.findViewById(R.id.profile_photo);
        edit_name = view.findViewById(R.id.edit_name);
        edit_lastname = view.findViewById(R.id.edit_lastname);
        edit_nickname = view.findViewById(R.id.edit_nickname);
        edit_password = view.findViewById(R.id.edit_password);
        edit_profile_photo = view.findViewById(R.id.edit_profile_photo);
        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);


        // Get the header view of the NavigationView
        headerView = navigationView.getHeaderView(0);

        // Find the ImageView inside the header view
        headerImageView = headerView.findViewById(R.id.profile_photo_drawer);

        paintPhoto();

        profile_name.setText(sharedPreferences.getString("first_name", ""));
        profile_lastname.setText(sharedPreferences.getString("last_name",""));
        profile_nickname.setText(sharedPreferences.getString("nickname",""));
        profile_email.setText(sharedPreferences.getString("email",""));

        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_update_profile);

                EditText editText=dialog.findViewById(R.id.et_profile);
                Button btUpdate=dialog.findViewById(R.id.bt_update_profile);

                editText.setHint("Ingresa tu nombre");
                editText.setText(profile_name.getText());

                int width= WindowManager.LayoutParams.MATCH_PARENT;
                int height=WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width,height);
                dialog.show();

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editText.getText().toString().isEmpty()){
                            editText.setError("Campo vacio");
                            return;
                        }

                        dialog.dismiss();

                        sharedPreferences.edit().putString("first_name", editText.getText().toString()).apply();
                        profile_name.setText(editText.getText().toString());
                        updateField("first_name");
                    }
                });
            }
        });
        edit_lastname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_update_profile);

                EditText editText=dialog.findViewById(R.id.et_profile);
                Button btUpdate=dialog.findViewById(R.id.bt_update_profile);

                editText.setHint("Ingresa tus apellidos");
                editText.setText(profile_lastname.getText());

                int width= WindowManager.LayoutParams.MATCH_PARENT;
                int height=WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width,height);
                dialog.show();

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editText.getText().toString().isEmpty()){
                            editText.setError("Campo vacio");
                            return;
                        }

                        dialog.dismiss();

                        sharedPreferences.edit().putString("last_name", editText.getText().toString()).apply();
                        profile_lastname.setText(editText.getText().toString());
                        updateField("last_name");
                    }
                });
            }
        });

        edit_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_update_profile);

                EditText editText=dialog.findViewById(R.id.et_profile);
                Button btUpdate=dialog.findViewById(R.id.bt_update_profile);

                editText.setHint("Ingresa tu apodo");
                editText.setText(profile_nickname.getText());

                int width= WindowManager.LayoutParams.MATCH_PARENT;
                int height=WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width,height);
                dialog.show();

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editText.getText().toString().isEmpty()){
                            editText.setError("Campo vacio");
                            return;
                        }

                        dialog.dismiss();

                        sharedPreferences.edit().putString("nickname", editText.getText().toString()).apply();
                        profile_nickname.setText(editText.getText().toString());
                        updateField("nickname");

                    }
                });
            }
        });

        edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_update_password);

                EditText et_pass_actual=dialog.findViewById(R.id.et_pass_actual);
                EditText et_pass_nueva=dialog.findViewById(R.id.et_pass_nueva);
                EditText et_pass_nueva_conf=dialog.findViewById(R.id.et_pass_nueva_conf);
                Button btUpdate=dialog.findViewById(R.id.bt_update_profile);

                int width= WindowManager.LayoutParams.MATCH_PARENT;
                int height=WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width,height);
                dialog.show();

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!et_pass_actual.getText().toString().equals(sharedPreferences.getString("pass",""))){
                            et_pass_actual.setError("Contraseña incorrecta");
                            return;
                        }
                        if(et_pass_nueva.getText().toString().isEmpty()){
                            et_pass_nueva.setError("Campo vacio");
                            return;
                        }
                        if(et_pass_nueva.getText().toString().equals(sharedPreferences.getString("pass",""))){
                            et_pass_nueva.setError("La contraseña nueva no puede ser igual a la actual");
                            return;
                        }
                        if(!et_pass_nueva.getText().toString().equals(et_pass_nueva_conf.getText().toString())){
                            et_pass_nueva_conf.setError("Las contraseñas no coinciden");
                            return;
                        }

                        dialog.dismiss();

                        sharedPreferences.edit().putString("pass", et_pass_nueva_conf.getText().toString()).apply();
                        updateField("pass");
                    }
                });
            }
        });
        edit_profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePickerDialog();
            }

        });

        return view;
    }

    private void paintPhoto() {
        profileImageUrl = sharedPreferences.getString("photo", null);
        if(profileImageUrl != null){
            Glide.with(getActivity())
                    .load(profileImageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(profile_photo);
            Glide.with(getActivity())
                    .load(profileImageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(headerImageView);
        }
    }

    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[]{"Camera", "Gallery"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        openCamera();
                        break;
                    case 1:
                        openGallery();
                        break;
                }
            }
        });
        builder.show();

    }
    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/jpg");
        startActivityForResult(galleryIntent, REQUEST_IMAGE_PICK);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            File imageFile = null;
            Bitmap imageBitmap = null;
            if (requestCode == REQUEST_IMAGE_CAPTURE && data != null) {
                // Image captured from the camera
                imageBitmap = (Bitmap) data.getExtras().get("data");
                try {
                    imageFile = createImageFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (imageFile != null) {
                    try {
                        FileOutputStream outputStream = new FileOutputStream(imageFile);
                        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                // Image selected from the gallery
                Uri imageUri = data.getData();
                imageFilePath = getPathFromUri(imageUri);
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (imageBitmap != null) {
                String base64Image = encodeToBase64(imageBitmap);
                update_photo(base64Image);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    private String getPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = requireActivity().getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(columnIndex);
            cursor.close();
            return path;
        }
        return null;
    }
    public void updateField(String field){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.update_profile_url, response -> {
            if(response.equals("Success")){
                Toast.makeText(requireContext(), "Actualizado", Toast.LENGTH_SHORT).show();
            }else {
                new android.app.AlertDialog.Builder(getContext())
                        .setTitle("Error")
                        .setMessage(response)
                        .setPositiveButton("Aceptar", null)
                        .create().show();
            }
        }, error -> {
            Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
            Log.d("VOLLEY", error.getMessage());
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("field", field);
                params.put("data", sharedPreferences.getString(field, ""));
                params.put("id", sharedPreferences.getString("id", ""));
                return params;
            }

        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    public void update_photo(String imageEncoded) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.update_photo_url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                boolean success = jsonObject.getBoolean("success");
                if (success) {
                    sharedPreferences.edit().putString("photo", jsonObject.getString("url")).apply();
                    Toast.makeText(requireContext(), jsonObject.getString("url"), Toast.LENGTH_SHORT).show();
                    paintPhoto();
                }
            } catch (JSONException e) {
                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
            Log.d("VOLLEY", error.getMessage());
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", sharedPreferences.getString("id", ""));
                params.put("image", imageEncoded);
                return params;
            }

        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private String encodeToBase64(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

}



