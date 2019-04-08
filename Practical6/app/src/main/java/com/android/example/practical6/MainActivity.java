package com.android.example.practical6;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private MaterialButton mToggleFlashlightButton;
    private AppCompatTextView mFlashStatusTextView;

    private static final int CAMERA_REQUEST = 50;
    private boolean mFlashLightStatus, mHasCameraFlash, mIsEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireUpWidgets();
        setUpFeatures();
        setListeners();
    }

    private void setUpFeatures() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);
        mHasCameraFlash = getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        mIsEnabled = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void setListeners() {
        mToggleFlashlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHasCameraFlash) {
                    if (mFlashLightStatus)
                        flashLightOff();
                    else
                        flashLightOn();
                } else {
                    Toast.makeText(MainActivity.this, "No flash available on your device",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            mFlashLightStatus = true;
            mFlashStatusTextView.setText("Status : ON");
        } catch (CameraAccessException e) {
            Log.e("Flash error", e.getMessage());
        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            mFlashLightStatus = false;
            mFlashStatusTextView.setText("Status : OFF");
        } catch (CameraAccessException e) {
            Log.e("Flash error", e.getMessage());
        }
    }

    private void wireUpWidgets() {
        mToggleFlashlightButton = findViewById(R.id.toggle_flashlight_button);
        mFlashStatusTextView = findViewById(R.id.flash_status_text_view);
        mFlashStatusTextView.setText("Status : OFF");
    }
}
