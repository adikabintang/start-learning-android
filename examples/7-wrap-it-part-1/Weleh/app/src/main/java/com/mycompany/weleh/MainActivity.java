package com.mycompany.weleh;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import net.glxn.qrgen.android.QRCode;

import java.io.IOException;

import static com.google.android.gms.vision.CameraSource.CAMERA_FACING_BACK;

public class MainActivity extends AppCompatActivity {
    public static String EMPLOYEE_KEY = "com.mycompany.weleh.employee_key";
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private EditText employeeIdET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // it is important to run this first to check permission
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 50);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCameraForQRCode();

        Button button = (Button) findViewById(R.id.main_activity_button_submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmployeeId(view);
            }
        });
    }

    public void sendEmployeeId(View view) {
        Intent intent = new Intent(this, CheckDataActivity.class);
        employeeIdET = (EditText) findViewById(R.id.employeeIdField);
        String employeeIdString = employeeIdET.getText().toString();

        intent.putExtra(EMPLOYEE_KEY, employeeIdString);
        startActivity(intent);
    }

    public void setupCameraForQRCode() {
        cameraView = (SurfaceView) findViewById(R.id.camera_see_qr_code);

        barcodeDetector = new BarcodeDetector
                .Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setFacing(CAMERA_FACING_BACK)
                .setAutoFocusEnabled(true)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    cameraSource.start(cameraView.getHolder());
                }
                catch (IOException e) {
                    Log.e("CAMERA SOURCE", e.getMessage());
                } catch (SecurityException se) {
                    Log.e("SECURITY EXCEPTION", se.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    employeeIdET = (EditText) findViewById(R.id.employeeIdField);
                    employeeIdET.post(new Runnable() {
                        @Override
                        public void run() {
                            employeeIdET.setText(barcodes.valueAt(0).displayValue);
                        }
                    });
                }
            }
        });
    }
}
