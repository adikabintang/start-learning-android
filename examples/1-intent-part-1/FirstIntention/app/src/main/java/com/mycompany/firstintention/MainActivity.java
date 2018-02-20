package com.mycompany.firstintention;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static String KEY_MESSAGE = "com.mycompany.firstintention.what";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.send_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendName(view);
            }
        });
    }

    public void sendName(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText inputNameStr = (EditText) findViewById(R.id.name_input);
        String message = inputNameStr.getText().toString();

        // key:
        // It's a good practice to define keys for intent extras
        // using your app's package name as a prefix
        intent.putExtra(KEY_MESSAGE, message); // key, value
        startActivity(intent);
    }
}
