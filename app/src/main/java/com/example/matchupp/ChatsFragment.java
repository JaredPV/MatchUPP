package com.example.matchupp;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.concurrent.Executor;

public class ChatsFragment extends Fragment {

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    public ChatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configurar el executor para ejecutar las operaciones de la autenticación en el hilo principal
        executor = ContextCompat.getMainExecutor(requireContext());

        // Crear un objeto BiometricPrompt
        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // La autenticación ha sido exitosa, no hacemos nada aquí ya que el usuario permanecerá en el ChatsFragment
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // La autenticación ha fallado, cambiamos al HomeFragment
                Toast.makeText(requireContext(), "Error de autenticación", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                // La autenticación ha fallado, cambiamos al HomeFragment
                requireActivity().recreate();
            }
        });

        // Configurar las opciones para el cuadro de diálogo de autenticación
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticación de huella digital")
                .setDescription("Use su huella digital para acceder a los chats.")
                .setNegativeButtonText("Cancelar")
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Verificar si el dispositivo es compatible con la autenticación por huella digital y si el usuario ha otorgado los permisos necesarios
        if (FingerprintUtils.isFingerprintAuthAvailable(requireContext()) && FingerprintUtils.hasPermission(requireContext())) {
            // Muestra el cuadro de diálogo de autenticación cuando el fragmento es visible al usuario
            biometricPrompt.authenticate(promptInfo);
        } else {
            // El dispositivo no es compatible con la autenticación por huella digital o el usuario no ha otorgado los permisos necesarios, cambiamos al HomeFragment
            navigateToHomeFragment();
        }
    }

    private void navigateToHomeFragment() {
        // Reemplazar este fragmento con el HomeFragment
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new HomeFragment())
                .commit();
    }
}
