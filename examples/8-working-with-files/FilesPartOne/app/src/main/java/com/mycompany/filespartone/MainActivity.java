package com.mycompany.filespartone;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private String filename = "testing_filename";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonWriteFile = (Button) findViewById(R.id.buttonWriteFile);

        buttonWriteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileContent = "weleh weleh";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(fileContent.getBytes());
                    outputStream.close();
                    Log.d("test", "bisa");
                } catch (Exception e) {
                    Log.e("FILE", "ERROR BRAY");
                    Log.e("FILE", e.getMessage());
                }
            }
        });

        Button buttonReadFile = (Button) findViewById(R.id.buttonReadFile);

        buttonReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    File myFile = new File(getApplicationContext().getFilesDir(), filename);
                    FileInputStream fileInputStream =
                            new FileInputStream(myFile);
                    DataInputStream dataInputStream =
                            new DataInputStream(fileInputStream);
                    BufferedReader bufferedReader =
                            new BufferedReader(new InputStreamReader(dataInputStream));
                    String strLine;
                    StringBuilder sb = new StringBuilder();

                    while ((strLine = bufferedReader.readLine()) != null) {
                        sb.append(strLine);
                    }
                    String myData = sb.toString();

                    dataInputStream.close();
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(myData);

                } catch (Exception e) {
                    Log.e("OPEN_FILE", e.getMessage());
                }
            }
        });
    }
}
