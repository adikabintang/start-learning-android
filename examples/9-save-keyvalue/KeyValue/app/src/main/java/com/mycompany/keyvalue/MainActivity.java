package com.mycompany.keyvalue;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String preferenceFileKey = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        Button buttonGet = (Button) findViewById(R.id.buttonGet);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences sharedPreferences = getApplicationContext()
//                        .getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
//
//                SharedPreferences.Editor editor =  sharedPreferences.edit();
//                editor.putInt(preferenceFileKey, 12);
//                editor.commit();
                SharedPreferences sharedPreferences = MainActivity.this
                        .getPreferences(Context.MODE_PRIVATE);

                SharedPreferences.Editor editor =  sharedPreferences.edit();
                editor.putInt(preferenceFileKey, 12);
                editor.commit();
                Log.d("KEY_VALUE", "Saving completed");
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = MainActivity.this
                        .getPreferences(Context.MODE_PRIVATE);
                int defaultValue = 1;
                int value = sharedPreferences.getInt(preferenceFileKey, defaultValue);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(String.valueOf(value));
            }
        });
    }
}
