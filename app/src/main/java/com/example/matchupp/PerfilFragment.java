package com.example.matchupp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.Manifest;
import androidx.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private TextView profile_name, profile_lastname, profile_nickname, profile_email;
    private ImageView profile_photo, edit_name, edit_lastname, edit_nickname, edit_password, edit_profile_photo;
    private String imageFilePath;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private SharedPreferences sharedPreferences;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUEST_IMAGE_PICK);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && data != null) {
                // Image captured from the camera
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                // Convert the bitmap to a file
                File imageFile = null;
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
                        // Set the image file path to the instance variable
                        imageFilePath = imageFile.getAbsolutePath();
                        // Display the captured image in the ImageView
                        profile_photo.setImageBitmap(imageBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                // Image selected from the gallery
                Uri imageUri = data.getData();
                // Convert the image URI to a file path
                imageFilePath = getPathFromUri(imageUri);
                if (imageFilePath != null) {
                    // Display the selected image in the ImageView
                    profile_photo.setImageURI(imageUri);
                }
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


}


