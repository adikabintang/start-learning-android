package com.mycompany.sqliteoi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mycompany.sqliteoi.db.AppDatabase;
import com.mycompany.sqliteoi.db.entity.User;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ViewTable extends AppCompatActivity {
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_table);
        final TextView textView = (TextView) findViewById(R.id.viewDataTable);
        appDatabase = AppDatabase.getDbInstance(getApplicationContext());
        appDatabase.userDaoInterface().loadAllUsersWithRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        StringBuilder sb = new StringBuilder();
                        for (User u : users) {
                            sb.append(u);
                        }
                        String allData = sb.toString();
                        textView.setText(allData);
                    }
                });
    }
}
