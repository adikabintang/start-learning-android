package com.mycompany.firstqrgenerator;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import net.glxn.qrgen.android.QRCode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = QRCode
                .from("www.mycompany.com")
                .withSize(300, 300)
                .bitmap();
        imageView.setImageBitmap(bitmap);
    }
}
