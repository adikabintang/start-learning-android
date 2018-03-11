package com.mycompany.sqliteoi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mycompany.sqliteoi.db.AppDatabase;
import com.mycompany.sqliteoi.db.entity.User;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private EditText editFirstName;
    private EditText editLastName;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editFirstName = (EditText) findViewById(R.id.inputFirstName);
        editLastName = (EditText) findViewById(R.id.inputLastName);

        Button saveButton = (Button) findViewById(R.id.buttonSave);
        Button viewButton = (Button) findViewById(R.id.buttonViewAll);

        appDatabase = AppDatabase.getDbInstance(getApplicationContext());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        appDatabase.userDaoInterface().insertUsers(
                                new User(1, "Wes", "Montgomery"));

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Log.i("INSERT", "YEY COMPLETE");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("INSERT_ERROR", e.getMessage());
                            }
                        });
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewTable.class);
                startActivity(intent);
            }
        });
    }
}
