package com.example.matchupp;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.content.Context;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

public class FingerprintUtils {

    public static boolean isFingerprintAuthAvailable(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false; // La autenticación por huella digital no está disponible en versiones anteriores a Android 6.0
        }

        FingerprintManager fingerprintManager = context.getSystemService(FingerprintManager.class);
        return fingerprintManager != null && fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints();
    }

    public static boolean hasPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED;
    }
}
