package com.mycompany.mygetapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mycompany.mygetapplication.data.model.Working;
import com.mycompany.mygetapplication.data.remote.ApiUtils;
import com.mycompany.mygetapplication.data.remote.WorkingService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private WorkingService workingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workingService = ApiUtils.getWorkingService();
        Button buttonGet = (Button) findViewById(R.id.button_get);

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMyJob();
            }
        });
    }

    private void getMyJob() {
        final TextView jobName = (TextView) findViewById(R.id.job_name);
        final TextView jobDesc = (TextView) findViewById(R.id.job_desc);

        workingService.getMyJob()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Working>() {
                    @Override
                    public void onNext(@NonNull Working working) {
                        jobName.setText("job name: " + working.getJobName());
                        jobDesc.setText("job desc: " + working.getJobDesc());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
