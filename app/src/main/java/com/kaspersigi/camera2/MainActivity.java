package com.kaspersigi.camera2;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "CameraDemo";
    private String mCameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtons();
    }

    private void setButtons() {
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click " + getString(R.string.button1));
                // 请求相机权限
                requestPermissions();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click " + getString(R.string.button2));
                openCamera();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click " + getString(R.string.button3));
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click " + getString(R.string.button4));
            }
        });

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click " + getString(R.string.button5));
            }
        });

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click " + getString(R.string.button6));
            }
        });

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click " + getString(R.string.button7));
            }
        });

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click " + getString(R.string.button8));
            }
        });
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getBaseContext(), "Permission has been granted", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{ android.Manifest.permission.CAMERA }, 100);
            }
        }
    }

    private void openCamera() {
        CameraManager cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        String[] cameraList;
        try {
            cameraList = cameraManager.getCameraIdList();
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        mCameraId = null;
        for (String cameraId : cameraList) {
            CameraCharacteristics cameraCharacteristics;
            try {
                cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }
            if (CameraCharacteristics.LENS_FACING_BACK == cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)) {
                mCameraId = cameraId;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Open CameraId " + mCameraId, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}